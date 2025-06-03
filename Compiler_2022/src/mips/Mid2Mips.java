package mips;

import mcode.Arithmetic;
import mcode.ArrayLoad;
import mcode.ArrayStore;
import mcode.Branch;
import mcode.Declare;
import mcode.FuncCall;
import mcode.FuncDeclare;
import mcode.Jump;
import mcode.Label;
import mcode.MidCode;
import mcode.None;
import mcode.Output;
import mcode.ParaDeclare;
import mcode.ParaPush;
import mcode.Return;
import mcode.operand.CharOperand;
import mcode.operand.GetIntOperand;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import mcode.operand.RegisterOperand;
import mcode.operand.RetValueOperand;
import mcode.operand.TempOperand;
import symboltabel.FuncSymbol;
import symboltabel.SymbolTable;
import symboltabel.VarSymbol;
import tools.MyIO;

import java.util.ArrayList;
import java.util.HashMap;

public class Mid2Mips {
    private final ArrayList<MidCode> midCodes;
    private final HashMap<String, Integer> lastIndexOf = new HashMap<>();
    private final HashMap<String, ArrayList<Integer>> getVal = new HashMap<>();
    private final HashMap<String, ArrayList<Integer>> setVal = new HashMap<>();
    private int index = 0;
    private int preOffset;
    private boolean jumpMain = false;
    private int loopDepth = 0;

    public Mid2Mips() {
        midCodes = MyIO.getMidCodes();
        // TODO: 2022/11/25 将未使用的数组变量删除
        ArrayList<String> constArrays = new ArrayList<>();
        for (int i = 0; i < midCodes.size(); i++) {
            MidCode midCode = midCodes.get(i);
            if (midCode instanceof ArrayStore) {
                VarSymbol varSymbol = ((ArrayStore) midCode).getArrayTarget();
                if (varSymbol.isConst() && varSymbol.getDimension() > 0) {
                    constArrays.add(varSymbol.getMark());
                }
            }
            ArrayList<String> gets = midCode.getSourceIds();
            for (String id : gets) {
                if (getVal.containsKey(id)) {
                    getVal.get(id).add(i);
                }
                else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(i);
                    getVal.put(id, arrayList);
                }
                lastIndexOf.put(id, i);
            }
            ArrayList<String> sets = midCode.getTargetIds();
            for (String id : sets) {
                if (setVal.containsKey(id)) {
                    setVal.get(id).add(i);
                }
                else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(i);
                    setVal.put(id, arrayList);
                }
                lastIndexOf.put(id, i);
            }
        }
        for (String mark : constArrays) {
            if (!getVal.containsKey(mark)) {
                for (int index : setVal.get(mark)) {
                    midCodes.set(index, new None());
                }
            }
        }
    }

    public void convert() {
        for (; index < midCodes.size(); index++) {
            MidCode midCode = midCodes.get(index);
            // System.out.println(midCode.getClass().getSimpleName());
            if (midCode instanceof Arithmetic) {
                handleArithmetic((Arithmetic) midCode);
            }
            else if (midCode instanceof ArrayStore) {
                handleArrayStore((ArrayStore) midCode);
            }
            else if (midCode instanceof ArrayLoad) {
                handleArrayLoad((ArrayLoad) midCode);
            }
            else if (midCode instanceof Branch) {
                handleBranch((Branch) midCode);
            }
            else if (midCode instanceof Declare) {
                handleDeclare((Declare) midCode);
            }
            else if (midCode instanceof FuncCall) {
                handleFuncCall((FuncCall) midCode);
            }
            else if (midCode instanceof FuncDeclare) {
                if (!jumpMain) {
                    print("j main");
                    jumpMain = true;
                }
                handleFuncDeclare((FuncDeclare) midCode);
            }
            else if (midCode instanceof Jump) {
                handleJump((Jump) midCode);
            }
            else if (midCode instanceof Label) {
                handleLabel((Label) midCode);
            }
            else if (midCode instanceof Output) {
                handleOutput((Output) midCode);
            }
            else if (midCode instanceof ParaDeclare) {
                handleParaDeclare();
                index--;
            }
            else if (midCode instanceof ParaPush) {
                handleParaPush();
                index--;
            }
            else if (midCode instanceof Return) {
                handleReturn();
            }
        }
        new PeepholeOptimize().optimize();
    }

    private void handleArithmetic(Arithmetic arithmetic) {
        if (arriveEnd(arithmetic.getOperandTarget())) {
            free(arithmetic.getOperandA());
            free(arithmetic.getOperandB());
            free(arithmetic.getOperandTarget());
            return;
        }
        Operand operandA = loadFromSp(arithmetic.getOperandA());
        Operand operandB = loadFromSp(arithmetic.getOperandB());
        free(operandA);
        free(operandB);
        Operand target = arithmetic.getOperandTarget();
        alloc(arithmetic.getOperandTarget());
        boolean sto = false;
        if (arithmetic.getOperandTarget() instanceof TempOperand &&
                !((TempOperand) arithmetic.getOperandTarget()).isReg()) {
            target = RegisterOperand.allocT01();
            sto = true;
        }
        assert target != null;
        if (arithmetic.allImm()) {
            int val = arithmetic.getValue();
            if (arithmetic.getOperandTarget() instanceof TempOperand) {
                spreadConst(arithmetic.getOperandTarget(), val);
                lastIndexOf.put(arithmetic.getOperandTarget().getMark(), index);
                free(arithmetic.getOperandTarget());
                free(target);
            }
            else {
                print("li " + arithmetic.getOperandTarget().getMipsSym() + ", " + val);
            }
            return;
        }
        Operand tmp1;
        Operand tmp2;
        if (operandA instanceof ImmediateOperand) {
            tmp1 = RegisterOperand.allocT01();
            assert tmp1 != null;
            print("li " + tmp1.getMipsSym() + ", " + operandA.getMipsSym());
            operandA = tmp1;
        }
        boolean powerOf2 = false;
        if (operandB instanceof ImmediateOperand && arithmetic.needsTwoRegs()) {
            if (arithmetic.mulOrDiv() &&
                    isPowerOf2(((ImmediateOperand) operandB).getValue())) {
                md2shift(operandA, operandB, target, arithmetic.getOperator());
                powerOf2 = true;
            }
            else {
                tmp2 = RegisterOperand.allocT01();
                assert tmp2 != null;
                print("li " + tmp2.getMipsSym() + ", " + operandB.getMipsSym());
                operandB = tmp2;
            }
        }
        if (arithmetic.aisAddress() && !arithmetic.aisGlobalAddress()) {
            VarSymbol varSymbol = (VarSymbol) operandA;
            if (!varSymbol.isSpreadAddress() && !varSymbol.isGlobal()) {
                tmp1 = RegisterOperand.allocT01();
                assert tmp1 != null;
                print("addi " + tmp1.getMipsSym() + ", $sp, " +
                        (arithmetic.getAddress() - getBaseSp()));
                operandA = tmp1;
            }
            else if (varSymbol.isSpreadAddress() && !varSymbol.isGlobal()
                    && !varSymbol.hasAllocToReg()) {
                tmp1 = RegisterOperand.allocT01();
                assert tmp1 != null;
                print("lw " + tmp1.getMipsSym() + ", " +
                        (varSymbol.getAddress() - getBaseSp()) + "($sp)");
                operandA = tmp1;
            }
            // TODO: 2022/10/24 index为常数时可优化
            // TODO: 2022/11/13 主函数中取地址操作可简化
        }
        free(operandA);
        free(operandB);
        if (!powerOf2) {
            if (arithmetic.getMipsOp().equals("div")) {
                if (arithmetic.getOperator().equals("/")) {
                    print("div " + operandA.getMipsSym() + ", " + operandB.getMipsSym());
                    print("mflo " + target.getMipsSym());
                }
                else {
                    print("div " + operandA.getMipsSym() + ", " + operandB.getMipsSym());
                    print("mfhi " + target.getMipsSym());
                }
            }
            else {
                print(arithmetic.getMipsOp() + " " + target.getMipsSym() + ", "
                        + operandA.getMipsSym() + ", " + operandB.getMipsSym());
            }
        }
        if (sto) {
            storeToSp(arithmetic.getOperandTarget(), target);
            free(target);
        }
    }

    private void md2shift(Operand operandA, Operand operandB, Operand operandTarget, String op) {
        int num = ((ImmediateOperand) operandB).getValue();
        if (num == 0) {
            print("li " + operandTarget.getMipsSym() + ", 0");
        }
        int power = whatPowerOf2(num);
        if (op.equals("*")) {
            print("sll " + operandTarget.getMipsSym() + ", "
                    + operandA.getMipsSym() + ", " + power);
        }
        else if (op.equals("/")) {
            Label els = new Label();
            print("bgez " + operandA.getMipsSym() + ", " + els.getMipsSym());
            print("subu " + operandTarget.getMipsSym() + ", $zero, " + operandA.getMipsSym());
            print("sra " + operandTarget.getMipsSym() + ", "
                    + operandTarget.getMipsSym() + ", " + power);
            print("subu " + operandTarget.getMipsSym() + ", $zero, " + operandTarget.getMipsSym());
            Label end = new Label();
            print("j " + end.getMipsSym());
            print(els.getName() + ":");
            print("sra " + operandTarget.getMipsSym() + ", " +
                    operandA.getMipsSym() + ", " + power);
            print(end.getName() + ":");
        }
        else {
            Label els = new Label();
            Label end = new Label();
            print("bgez " + operandA.getMipsSym() + ", " + els.getMipsSym());
            print("andi " + operandTarget.getMipsSym() + ", " + operandA.getMipsSym()
                    + ", " + (num - 1));
            print("beqz " + operandTarget.getMipsSym() + ", " + end.getMipsSym());
            print("addiu " + operandTarget.getMipsSym() + ", " + operandTarget.getMipsSym() +
                    ", " + (-num));
            print("j " + end.getMipsSym());
            print(els.getName() + ":");
            print("andi " + operandTarget.getMipsSym() + ", " + operandA.getMipsSym()
                    + ", " + (num - 1));
            print(end.getName() + ":");
        }
    }

    private void spreadConst(Operand operand, int value) {
        for (int i : getVal.get(operand.getMark())) {
            midCodes.get(i).replaceSource(operand, new ImmediateOperand(value));
        }
    }

    private boolean isPowerOf2(int num) {
        if (num < 0) {
            return false;
        }
        if (num == 2 || num == 0) {
            return true;
        }
        else if (num % 2 == 1) {
            return false;
        }
        else {
            return isPowerOf2(num / 2);
        }
    }

    private int whatPowerOf2(int num) {
        int power = 0;
        int n = num;
        while (n != 1) {
            n /= 2;
            power++;
        }
        return power;
    }

    public void handleArrayStore(ArrayStore arrayStore) {
        VarSymbol target = arrayStore.getArrayTarget();
        if (arriveEnd(target) && !target.isSpreadAddress()
                && !(arrayStore.getOperandSource() instanceof GetIntOperand)) {
            free(arrayStore.getIndex());
            free(arrayStore.getOperandSource());
            free(target);
            return;
        }
        free(arrayStore.getOperandSource());
        Operand source = loadFromSp(arrayStore.getOperandSource());
        if (source instanceof GetIntOperand) {
            print("li $v0, 5");
            print("syscall");
            source = new RetValueOperand();
            if (arrayStore.isNotArray() && target.hasAllocToReg()) {
                print("move " + target.getMipsSym() + ", $v0");
                return;
            }
        }
        else if (source instanceof ImmediateOperand) {
            int val = ((ImmediateOperand) source).getValue();
            source = RegisterOperand.allocT01();
            assert source != null;
            print("li " + source.getMipsSym() + ", " + val);
        }
        int offset = target.isGlobal() ? target.getAddress() - getBaseHp()
                : target.getAddress() - getBaseSp();
        String s = target.isGlobal() ? "($gp)" : "($sp)";
        if (target.isSpreadAddress() && target.hasAllocToReg()) {
            offset = 0;
            s = "(" + target.getMipsSym() + ")";
        }
        if (arrayStore.isNotArray()) {
            print("sw " + source.getMipsSym() + ", " +
                    (offset) + s);
        }
        else {
            Operand index = loadFromSp(arrayStore.getIndex());
            free(index);
            if (index instanceof ImmediateOperand) {
                if (target.isSpreadAddress() && !target.hasAllocToReg()) {
                    Operand operand = RegisterOperand.allocT01();
                    assert operand != null;
                    print("lw " + operand.getMipsSym() + ", " + offset + "($sp)");
                    print("sw " + source.getMipsSym() + ", " + (4 * ((ImmediateOperand) index)
                            .getValue()) + "(" + operand.getMipsSym() + ")");
                    free(operand);
                }
                else {
                    print("sw " + source.getMipsSym() + ", " +
                            (offset + 4 * ((ImmediateOperand) index).getValue()) + s);
                }
            }
            else {
                Operand operand = RegisterOperand.allocT01();
                assert operand != null;
                if (target.isSpreadAddress() && !target.hasAllocToReg()) {
                    print("lw " + operand.getMipsSym() + ", " + offset + "($sp)");
                    print("srl " + operand.getMipsSym() + ", " + operand.getMipsSym() + ", 2");
                    print("addu " + operand.getMipsSym() + ", " + operand.getMipsSym()
                            + ", " + index.getMipsSym());
                    print("sll " + operand.getMipsSym() + ", " + operand.getMipsSym() + ", 2");
                    print("sw " + source.getMipsSym() + ", " +
                            0 + "(" + operand.getMipsSym() + ")");
                }
                else {
                    print("sll " + operand.getMipsSym() + ", " + index.getMipsSym() + ", 2");
                    if (target.isSpreadAddress()) {
                        print("addu " + operand.getMipsSym() + ", " + operand.getMipsSym() +
                                ", " + target.getMipsSym());
                        print("sw " + source.getMipsSym() + ", " +
                                0 + "(" + operand.getMipsSym() + ")");
                    }
                    else if (target.isGlobal()) {
                        print("sw " + source.getMipsSym() + ", " +
                                (target.getAddress()) + "(" + operand.getMipsSym() + ")");
                    }
                    else {
                        print("addu " + operand.getMipsSym() + ", " + operand.getMipsSym() +
                                ", $sp");
                        print("sw " + source.getMipsSym() + ", " +
                                (offset) + "(" + operand.getMipsSym() + ")");
                    }
                }
                free(operand);
            }
        }
        free(target);
        free(source);
    }

    public void handleArrayLoad(ArrayLoad arrayLoad) {
        if (arriveEnd(arrayLoad.getOperandTarget())) {
            free(arrayLoad.getOperandTarget());
            free(arrayLoad.getArrayIdent());
            free(arrayLoad.getIndex());
            return;
        }
        Operand target = arrayLoad.getOperandTarget();
        Operand index = loadFromSp(arrayLoad.getIndex());
        free(index);
        alloc(target);
        boolean sto = false;
        if (!((TempOperand) target).isReg()) {
            target = RegisterOperand.allocT01();
            sto = true;
        }
        VarSymbol source = arrayLoad.getArrayIdent();
        free(source);
        int offset = source.isGlobal() ? source.getAddress() - getBaseHp()
                : source.getAddress() - getBaseSp();
        String s = source.isGlobal() ? "($gp)" : "($sp)";
        if (source.isSpreadAddress() && source.hasAllocToReg()) {
            offset = 0;
            s = "(" + source.getMipsSym() + ")";
        }
        assert target != null;
        if (!arrayLoad.isArray()) {
            print("lw " + target.getMipsSym() + ", " +
                    (offset) + s);
        }
        else {
            if (index instanceof ImmediateOperand) {
                if (source.isSpreadAddress() && !source.hasAllocToReg()) {
                    Operand operand = RegisterOperand.allocT01();
                    assert operand != null;
                    print("lw " + operand.getMipsSym() + ", " + offset + "($sp)");
                    print("lw " + target.getMipsSym() + ", " + (4 * ((ImmediateOperand) index)
                            .getValue()) + "(" + operand.getMipsSym() + ")");
                    free(operand);
                }
                else {
                    print("lw " + target.getMipsSym() + ", " +
                            (offset + 4 * ((ImmediateOperand) index).getValue()) + s);
                }
            }
            else {
                Operand operand = RegisterOperand.allocT01();
                assert operand != null;
                if (source.isSpreadAddress() && !source.hasAllocToReg()) {
                    print("lw " + operand.getMipsSym() + ", " + offset + "($sp)");
                    print("srl " + operand.getMipsSym() + ", " + operand.getMipsSym() + ", 2");
                    print("addu " + operand.getMipsSym() + ", " + operand.getMipsSym()
                            + ", " + index.getMipsSym());
                    print("sll " + operand.getMipsSym() + ", " + operand.getMipsSym() + ", 2");
                    print("lw " + target.getMipsSym() + ", " +
                            0 + "(" + operand.getMipsSym() + ")");
                }
                else {
                    print("sll " + operand.getMipsSym() + ", " + index.getMipsSym() + ", 2");
                    if (source.isSpreadAddress()) {
                        print("addu " + operand.getMipsSym() + ", " + operand.getMipsSym() +
                            ", " + source.getMipsSym());
                        print("lw " + target.getMipsSym() + ", " +
                            0 + "(" + operand.getMipsSym() + ")");
                    }
                    else if (source.isGlobal()) {
                        print("lw " + target.getMipsSym() + ", " +
                            (source.getAddress()) + "(" + operand.getMipsSym() + ")");
                    }
                    else {
                        print("addu " + operand.getMipsSym() + ", " + operand.getMipsSym() +
                            ", $sp");
                        print("lw " + target.getMipsSym() + ", " +
                            (offset) + "(" + operand.getMipsSym() + ")");
                    }
                }
                free(operand);
            }
        }
        if (sto) {
            storeToSp(arrayLoad.getOperandTarget(), target);
            free(target);
        }
    }

    public void handleBranch(Branch branch) {
        Operand operandA = loadFromSp(branch.getOperandA());
        Operand operandB = loadFromSp(branch.getOperandB());
        Operand tmp1;
        Operand tmp2;
        if (branch.allImm()) {
            if (branch.jump()) {
                print("j " + branch.getTerminal().getName());
            }
            return;
        }
        if (operandA instanceof ImmediateOperand) {
            if (((ImmediateOperand) operandA).getValue() == 0) {
                operandA =  new RegisterOperand();
            }
            else {
                tmp1 = RegisterOperand.allocT01();
                assert tmp1 != null;
                print("li " + tmp1.getMipsSym() + ", " + operandA.getMipsSym());
                operandA = tmp1;
            }
        }
        if (operandB instanceof ImmediateOperand) {
            if (((ImmediateOperand) operandB).getValue() == 0) {
                operandB = new RegisterOperand();
            }
            else {
                tmp2 = RegisterOperand.allocT01();
                assert tmp2 != null;
                print("li " + tmp2.getMipsSym() + ", " + operandB.getMipsSym());
                operandB = tmp2;
            }
        }
        free(operandA);
        free(operandB);
        String inst = branch.isEqual() ? "beq" : "bne";
        print(inst + " " + operandA.getMipsSym() + ", " + operandB.getMipsSym()
                + ", " + branch.getTerminal().getMipsSym());
    }

    public void handleDeclare(Declare declare) {
        VarSymbol varSymbol = declare.getVarSymbol();
        if (varSymbol.isDeclared()) {
            return;
        }
        varSymbol.setDeclared(true);
        if (lastIndexOf.containsKey(varSymbol.getMark())) {
            varSymbol.setAddress();
            varSymbol.allocSReg();
            if (varSymbol.hasAllocToReg()) {
                if (getVal.containsKey(varSymbol.getMark()) && !varSymbol.isSpreadAddress()) {
                    for (int i : getVal.get(varSymbol.getMark())) {
                        Operand target = ((ArrayLoad) midCodes.get(i)).getOperandTarget();
                        int j = i + 1;
                        for (; j <= lastIndexOf.get(target.getMark()); j++) {
                            midCodes.get(j).replaceSource(target, varSymbol);
                            if (midCodes.get(j).getTargetVarMips().contains(varSymbol.getMipsSym())
                                    || lastIndexOf.get(target.getMark()) == j ||
                                    (varSymbol.isGlobal() && midCodes.get(j) instanceof FuncCall) ||
                                    (midCodes.get(j) instanceof FuncCall && !((FuncCall) midCodes.
                                            get(j)).getStores().contains(varSymbol.getMipsSym()))) {
                                break;
                            }
                        }
                        if (lastIndexOf.get(target.getMark()) == j) {
                            midCodes.set(i, new None());
                        }
                        else {
                            midCodes.set(i, new Arithmetic('+',
                                    ((ArrayLoad) midCodes.get(i)).getArrayIdent(),
                                    new ImmediateOperand(0), target));
                        }
                    }
                }
                if (setVal.containsKey(varSymbol.getMark())) {
                    for (int i : setVal.get(varSymbol.getMark())) {
                        if (midCodes.get(i) instanceof ParaDeclare) {
                            continue;
                        }
                        ArrayStore arrayStore = (ArrayStore) midCodes.get(i);
                        Operand source = arrayStore.getOperandSource();
                        MidCode preCode = midCodes.get(i - 1);
                        if (lastIndexOf.containsKey(source.getMark())
                                && !varSymbol.isSpreadAddress() &&
                                lastIndexOf.get(source.getMark()) == i
                                && preCode instanceof Arithmetic &&
                                preCode.getTargetIds().contains(source.getMark())) {
                            ((Arithmetic) preCode).replaceTarget(varSymbol);
                            midCodes.set(i, new None());
                        }
                        else if (!varSymbol.isSpreadAddress() &&
                                !(arrayStore.getOperandSource() instanceof GetIntOperand)) {
                            Arithmetic arithmetic = new Arithmetic('+',
                                    arrayStore.getOperandSource(),
                                    new ImmediateOperand(0), varSymbol);
                            midCodes.set(i, arithmetic);
                        }
                    }
                }
            }
        }
    }

    public void handleFuncCall(FuncCall funcCall) {
        ArrayList<String> stores = funcCall.getStores();
        stores.addAll(RegisterOperand.needStore());
        if (funcCall.isInMainFunc()) {
            stores.remove("$ra");
        }
        if (!(midCodes.get(index - 1) instanceof ParaPush)) {
            int offset = getCurSp() - stores.size() * 4 - getBaseSp();
            print("addi $sp, $sp, " + offset);
            preOffset = offset;
        }
        int offset = stores.size() * 4;
        for (String reg : stores) {
            offset -= 4;
            SymbolTable.getSymbolTable().allocSp(1);
            print("sw " + reg + ", " + offset + "($sp)");
        }
        FuncSymbol funcSymbol = funcCall.getFuncSymbol();
        print("jal " + funcSymbol.getName());
        offset = 0;
        for (int i = stores.size() - 1; i >= 0; i--) {
            String reg = stores.get(i);
            print("lw " + reg + ", " + offset + "($sp)");
            offset += 4;
        }
        print("addi " + "$sp, $sp, " + (-preOffset));
    }

    public void handleFuncDeclare(FuncDeclare declare) {
        print(declare.getName() + ":");
        SymbolTable.getSymbolTable().setSp(getBaseSp());
    }

    public void handleJump(Jump jump) {
        print("j " + jump.getTerminal().getMipsSym());
    }

    public void handleLabel(Label label) {
        print(label.getName() + ":");
        if (label.getName().contains("endloop")) {
            loopDepth--;
        }
        else if (label.getName().contains("loop")) {
            loopDepth++;
        }
    }

    private boolean notInLoop() {
        return loopDepth == 0;
    }

    private FuncCall getNextFuncCall() {
        for (int i = index; ; i++) {
            if (midCodes.get(i) instanceof FuncCall) {
                return (FuncCall) midCodes.get(i);
            }
        }
    }

    public void handleParaPush() {
        FuncCall nextFuncCall = getNextFuncCall();
        ArrayList<String> stores = nextFuncCall.getStores();
        stores.addAll(RegisterOperand.needStore());
        if (nextFuncCall.isInMainFunc()) {
            stores.remove("$ra");
        }
        int offset = getCurSp() - stores.size() * 4 - getBaseSp();
        print("addi $sp, $sp, " + offset);
        preOffset = offset;
        int i = 0;
        for (; midCodes.get(index) instanceof ParaPush; index++, i++) {
            ParaPush paraPush = (ParaPush) midCodes.get(index);
            Operand source = paraPush.getSource();
            free(source);
            if (i < 4) {
                if (source instanceof ImmediateOperand) {
                    print("li " + "$a" + i + ", " +
                            ((ImmediateOperand) source).getValue());
                }
                else if (source instanceof TempOperand && !((TempOperand) source).isReg()) {
                    offset = ((TempOperand) source).getAddress() -
                            (getCurSp() - stores.size() * 4);
                    print("lw " + "$a" + i + ", " + offset + "($sp)");
                }
                else {
                    print("move " + "$a" + i + ", " + source.getMipsSym());
                }
            }
            else {
                if (source instanceof ImmediateOperand) {
                    Operand tmp = RegisterOperand.allocT01();
                    assert tmp != null;
                    print("li " + tmp.getMipsSym() + ", " +
                            ((ImmediateOperand) source).getValue());
                    print("sw " + tmp.getMipsSym() + ", " + (-4 * (i + 1)) + "($sp)");
                    free(tmp);
                }
                else if (source instanceof TempOperand && !((TempOperand) source).isReg()) {
                    Operand tmp = RegisterOperand.allocT01();
                    assert tmp != null;
                    offset = ((TempOperand) source).getAddress() -
                            (getCurSp() - stores.size() * 4);
                    print("lw " + tmp.getMipsSym() + ", " + offset + "($sp)");
                    print("sw " + tmp.getMipsSym() + ", " + (-4 * (i + 1)) + "($sp)");
                    free(tmp);
                }
                else {
                    print("sw " + source.getMipsSym() + ", " + (-4 * (i + 1)) + "($sp)");
                }
            }
        }
    }

    public void handleParaDeclare() {
        int i;
        int preI = -1;
        for (; midCodes.get(index) instanceof ParaDeclare; index++) {
            ParaDeclare paraDeclare = (ParaDeclare) midCodes.get(index);
            VarSymbol varSymbol = paraDeclare.getPara();
            i = paraDeclare.getIndex();
            if (i > preI + 1) {
                SymbolTable.getSymbolTable().allocSp(i - preI - 1);
            }
            if (lastIndexOf.containsKey(varSymbol.getMark())) {
                varSymbol.allocSReg();
                if (varSymbol.hasAllocToReg()) {
                    if (i < 4) {
                        print("move " + varSymbol.getMipsSym() + ", $a" + i);
                    }
                    else {
                        print("lw " + varSymbol.getMipsSym() + ", " + (-4 * (i + 1)) + "($sp)");
                    }
                    handleDeclare(new Declare(varSymbol));
                }
                else {
                    varSymbol.setAddress();
                    varSymbol.setDeclared(true);
                    if (i < 4) {
                        print("sw $a" + i + ", " + (-4 * (i + 1)) + "($sp)");
                    }
                }
            }
            preI = i;
        }
    }

    public void handleOutput(Output output) {
        free(output.getSource());
        Operand source = loadFromSp(output.getSource());
        free(source);
        if (source instanceof CharOperand) {
            int val = ((CharOperand) source).getContent();
            print("li $a0, " + val);
            print("li $v0, 11");
            print("syscall");
            return;
        }
        if (source instanceof ImmediateOperand) {
            print("li $a0, " + ((ImmediateOperand) source).getValue());
            print("li $v0, 1");
            print("syscall");
        }
        else {
            print("move $a0, " + source.getMipsSym());
            print("li $v0, 1");
            print("syscall");
        }
    }

    public void handleReturn() {
        print("jr $ra");
    }

    private void print(String s) {
        MyIO.writeToMipsBuffer(s + "\n");
    }

    private void free(Operand operand) {
        if (operand instanceof ImmediateOperand
                || operand instanceof RetValueOperand
                || operand instanceof GetIntOperand
                || operand instanceof CharOperand) {
            return;
        }
        if (operand instanceof RegisterOperand) {
            RegisterOperand registerOperand = (RegisterOperand) operand;
            if (registerOperand.isFreed()) {
                return;
            }
            RegisterOperand.freeTRegister(registerOperand);
            registerOperand.setFreed(true);
            return;
        }
        if (lastIndexOf.get(operand.getMark()) == index) {
            if (operand instanceof TempOperand) {
                TempOperand tempOperand = (TempOperand) operand;
                tempOperand.free();
            }
        }
    }

    private void alloc(Operand operand) {
        if (operand instanceof TempOperand && !((TempOperand) operand).isAlloc()) {
            TempOperand tempOperand = (TempOperand) operand;
            tempOperand.alloc();
        }
    }

    private Operand loadFromSp(Operand operand) {
        free(operand);
        if (operand instanceof TempOperand && !((TempOperand) operand).isReg()) {
            Operand tmp = RegisterOperand.allocT01();
            int offset = ((TempOperand) operand).getAddress() - getBaseSp();
            assert tmp != null;
            print("lw " + tmp.getMipsSym() + ", " + (offset) + "($sp)");
            return tmp;
        }
        return operand;
    }

    private void storeToSp(Operand target, Operand source) {
        int offset = ((TempOperand) target).getAddress() - getBaseSp();
        print("sw " + source.getMipsSym() + ", " +
                (offset) + "($sp)");
    }

    private int getCurSp() {
        return SymbolTable.getSymbolTable().getSp();
    }

    private boolean arriveEnd(Operand operand) {
        if (lastIndexOf.containsKey(operand.getMark()) &&
                lastIndexOf.get(operand.getMark()) == index && operand instanceof TempOperand) {
            return true;
        }
        return lastIndexOf.containsKey(operand.getMark()) &&
                lastIndexOf.get(operand.getMark()) == index && notInLoop()
                && !(operand instanceof VarSymbol && ((VarSymbol) operand).isGlobal());
    }
    
    private int getBaseSp() {
        return SymbolTable.getSymbolTable().getBaseSp();
    }
    
    private int getBaseHp() {
        return SymbolTable.getSymbolTable().getBaseHp();
    }
}
