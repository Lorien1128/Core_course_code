package symboltabel;

import mcode.operand.Operand;
import mcode.operand.RegisterOperand;
import nonterminals.ConstExp;
import nonterminals.FuncFParam;
import tools.Symbol;

import java.util.ArrayList;

public class VarSymbol implements SymbolItem, Operand, Cloneable {
    private static int count = 0;
    private final int id;
    private int subId;
    private String name;
    private final boolean isConst;
    private int line;
    private final ArrayList<Integer> sizes = new ArrayList<>();
    private final ArrayList<Integer> value = new ArrayList<>();
    private int address;
    private boolean negative = false;
    private final boolean isGlobal;
    private RegisterOperand operand = null;
    private Symbol ident;
    private boolean spreadAddress;
    private boolean inverted = false;
    private boolean freed = false;
    private int loopDepth = 0;
    private int referCount = 0;
    private boolean declared = false;

    @Override
    public boolean isInverted() {
        return inverted;
    }

    @Override
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public VarSymbol(Symbol ident, boolean isConst) {
        this.name = ident.getContent();
        this.line = ident.getLine();
        this.isConst = isConst;
        this.ident = ident;
        isGlobal = SymbolTable.getSymbolTable().isGlobalNow();
        id = count;
        count++;
        subId = 0;
    }

    public void setSpreadAddress(boolean spreadAddress) {
        this.spreadAddress = spreadAddress;
    }

    public boolean isSpreadAddress() {
        return spreadAddress;
    }

    public void setDeclared(boolean declared) {
        this.declared = declared;
    }

    public boolean isDeclared() {
        return declared;
    }

    public Symbol getIdent() {
        return ident;
    }

    public void allocSReg() {
        if (!isGlobal) {
            return;
        }
        if (operand != null) {
            return;
        }
        if (sizes.size() == 0 || sizes.get(0) == null) {
            this.operand = RegisterOperand.allocSRegister();
        }
    }

    public void allocSReg(int index) {
        if (operand != null) {
            new Exception().printStackTrace();
        }
        operand = RegisterOperand.allocSRegister(index);
    }

    public int getSRegIndex() {
        return operand.getIndex();
    }

    public boolean hasAllocToReg() {
        return operand != null;
    }

    public void free() {
        if (!isGlobal && !freed) {
            freed = true;
        }
    }

    @Override
    public VarSymbol clone() {
        try {
            return (VarSymbol) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public void setAddress() {
        int size = 1;
        if (!sizes.isEmpty() && sizes.get(0) == null) {
            address = SymbolTable.getSymbolTable().allocSp(1);
            return;
        }
        for (int len : sizes) {
            size *= len;
        }
        if (isGlobal) {
            address = SymbolTable.getSymbolTable().allocHp(size);
        }
        else {
            address = SymbolTable.getSymbolTable().allocSp(size);
        }
    }

    public int getAddress() {
        return address;
    }

    public VarSymbol() {
        isConst = false;
        isGlobal = false;
        id = count;
        count++;
        subId = 0;
    }

    public void addDimension(ConstExp constExp) {
        if (constExp == null) {
            sizes.add(null);
        }
        else {
            sizes.add(constExp.calculate());
        }
    }

    public void addValue(ConstExp constExp) {
        value.add(constExp.calculate());
    }

    public void setDimension(FuncFParam param) {
        name = ((Symbol) param.get(1)).getContent();
        line = ((Symbol) param.get(1)).getLine();
        ident = ((Symbol) param.get(1));
        if (param.size() == 2) {
            return;
        }
        sizes.add(null);
        int index = 5;
        while (index < param.size()) {
            sizes.add(((ConstExp) param.get(index)).calculate());
            index += 3;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String getMark() {
        return name + "." + id + "." + subId;
    }

    @Override
    public String getMipsSym() {
        if (operand == null) {
            return getMark();
        }
        else {
            return operand.getMark();
        }
    }

    @Override
    public boolean isNegative() {
        return negative;
    }

    @Override
    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public int getLine() {
        return line;
    }

    public boolean isConst() {
        return isConst;
    }

    public int getDimension() {
        return sizes.size();
    }

    public boolean isRegAble() {
        return sizes.size() == 0 || sizes.get(0) == null;
    }

    public int getRowLen() {
        return sizes.get(1);
    }

    public int getLen() {
        if (!sizes.isEmpty() && sizes.get(0) == null) {
            return 1;
        }
        int len = 1;
        for (int l : sizes) {
            len *= l;
        }
        return len;
    }

    public int getValue(int index) {
        return value.get(index);
    }

    public int getValue(int row, int col) {
        return value.get(sizes.get(1) * row + col);
    }

    @Override
    public String toString() {
        return getMark();
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setLoopDepth(int depth) {
        if (depth > loopDepth) {
            loopDepth = depth;
        }
    }

    public int getLoopDepth() {
        return loopDepth;
    }

    public void addReferCount() {
        referCount++;
    }

    public int getReferCount() {
        return referCount;
    }
}
