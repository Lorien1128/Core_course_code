package mcode;

import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import symboltabel.VarSymbol;
import tools.Symbol;

import java.util.ArrayList;

public class Arithmetic implements MidCode {
    private String operator;
    private Operand operandA;
    private Operand operandB;
    private Operand operandTarget;

    public Arithmetic(Symbol symbol, Operand operandA, Operand operandB,
                      Operand operandTarget) {
        this.operator = symbol.getType().toOperator();
        this.operandA = operandA;
        this.operandB = operandB;
        this.operandTarget = operandTarget;
        convert();
        handleInvert();
        moveImmToLatter();
    }

    public Arithmetic(String operator, Operand operandA, Operand operandB,
                      Operand operandTarget) {
        this.operator = operator;
        this.operandA = operandA;
        this.operandB = operandB;
        this.operandTarget = operandTarget;
        convert();
        handleInvert();
        moveImmToLatter();
    }

    public Arithmetic(char operator, Operand operandA, Operand operandB,
                      Operand operandTarget) {
        this.operator = String.valueOf(operator);
        this.operandA = operandA;
        this.operandB = operandB;
        this.operandTarget = operandTarget;
        convert();
        handleInvert();
        moveImmToLatter();
    }

    private void convert() {
        if (operator.equals("-") && operandB instanceof ImmediateOperand) {
            operator = "+";
            operandB = new ImmediateOperand(
                    -((ImmediateOperand) operandB).getValue());
        }
    }

    private void swapAB() {
        Operand tmp = operandA;
        operandA = operandB;
        operandB = tmp;
    }

    private void handleInvert() {
        switch (operator) {
            case "<=": {
                swapAB();
                operandTarget.setInverted(true);
                operator = "<";
                break;
            }
            case ">": {
                swapAB();
                operator = "<";
                break;
            }
            case ">=": {
                operandTarget.setInverted(true);
                operator = "<";
                break;
            }
            case "<":
            default: {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return operandTarget.getMark() + " = " + operandA.getMark() +
                " " + operator + " " + operandB.getMark() + "\n";
    }

    public void reversalOperator() {
        switch (operator) {
            case "+":
                operator = "-";
                break;
            case "-":
                operator = "+";
                break;
            case ">":
                operator = "<";
                break;
            case "<":
                operator = ">";
                break;
            default:
                break;
        }
    }

    @Override
    public ArrayList<String> getSourceIds() {
        ArrayList<String> ids = new ArrayList<>();
        if (hasId(operandA)) {
            ids.add(operandA.getMark());
        }
        if (hasId(operandB)) {
            ids.add(operandB.getMark());
        }
        return ids;
    }

    @Override
    public ArrayList<String> getTargetIds() {
        ArrayList<String> ids = new ArrayList<>();
        if (hasId(operandTarget)) {
            ids.add(operandTarget.getMark());
        }
        return ids;
    }

    @Override
    public ArrayList<String> getTargetVarMips() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (isMipsVar(operandTarget)) {
            arrayList.add(operandTarget.getMipsSym());
        }
        return arrayList;
    }

    @Override
    public void replaceSource(Operand from, Operand to) {
        if (operandA.getMark().equals(from.getMark())) {
            operandA = to;
        }
        if (operandB.getMark().equals(from.getMark())) {
            operandB = to;
        }
        moveImmToLatter();
    }

    private void moveImmToLatter() {
        if (aisImm() && (operator.equals("+") ||
                operator.equals("*") || operator.equals(">") || operator.equals("<"))) {
            swapAB();
            if (operator.equals(">") || operator.equals("<")) {
                reversalOperator();
            }
        }
        if (operandA instanceof ImmediateOperand) {
            int value = ((ImmediateOperand) operandA).getValue();
            if (value == 0) {
                if (operator.equals("/") || operator.equals("%")) {
                    operator = "+";
                    operandA = new ImmediateOperand(0);
                    operandB = new ImmediateOperand(0);
                }
            }
        }
        if (operandB instanceof ImmediateOperand) {
            int value = ((ImmediateOperand) operandB).getValue();
            if (value == 0) {
                if (operator.equals("*")) {
                    operator = "+";
                    operandA = new ImmediateOperand(0);
                    operandB = new ImmediateOperand(0);
                }
            }
            else if (value == 1) {
                if (operator.equals("*") || operator.equals("/")) {
                    operator = "+";
                    operandB = new ImmediateOperand(0);
                }
                else if (operator.equals("%")) {
                    operator = "+";
                    operandA = new ImmediateOperand(0);
                    operandB = new ImmediateOperand(0);
                }
            }
            else if (value == -1) {
                if (operator.equals("*") || operator.equals("/")) {
                    operator = "-";
                    operandB = operandA;
                    operandA = new ImmediateOperand(0);
                }
                else if (operator.equals("%")) {
                    operator = "+";
                    operandA = new ImmediateOperand(0);
                    operandB = new ImmediateOperand(0);
                }
            }
        }
        if (bisAddress() && !bisGlobalAddress()) {
            swapAB();
        }
        if (aisGlobalAddress()) {
            swapAB();
        }
    }

    public Operand getOperandA() {
        if (aisGlobalAddress()) {
            return new ImmediateOperand(((VarSymbol) operandA).getAddress());
        }
        return operandA;
    }

    public Operand getOperandB() {
        if (bisGlobalAddress()) {
            return new ImmediateOperand(((VarSymbol) operandB).getAddress());
        }
        return operandB;
    }

    public boolean aisAddress() {
        return operandA instanceof VarSymbol &&
                ((VarSymbol) operandA).getDimension() != 0;
    }

    public boolean bisAddress() {
        return operandB instanceof VarSymbol &&
                ((VarSymbol) operandB).getDimension() != 0;
    }

    public boolean aisGlobalAddress() {
        return aisAddress() && ((VarSymbol) operandA).isGlobal();
    }

    public boolean bisGlobalAddress() {
        return bisAddress() && ((VarSymbol) operandB).isGlobal();
    }

    public int getAddress() {
        if (aisAddress()) {
            return ((VarSymbol) operandA).getAddress();
        }
        else {
            return ((VarSymbol) operandB).getAddress();
        }
    }

    public boolean containsAddress() {
        return aisAddress() || bisAddress();
    }

    public VarSymbol getAddressVar() {
        if (aisAddress()) {
            return ((VarSymbol) operandA);
        }
        else {
            return ((VarSymbol) operandB);
        }
    }

    public Operand getOperandTarget() {
        return operandTarget;
    }

    public boolean bisImm() {
        return operandB instanceof ImmediateOperand ||
                bisGlobalAddress();
    }

    public boolean aisImm() {
        return operandA instanceof ImmediateOperand ||
                aisGlobalAddress();
    }

    public boolean allImm() {
        return aisImm() & bisImm();
    }

    public void replaceTarget(Operand operand) {
        operandTarget = operand;
    }

    public boolean needsTwoRegs() {
        return operator.equals("*") | operator.equals("/")
                | operator.equals("%") | getMipsOp().equals("slt");
    }

    public int getValue() {
        int a;
        if (operandA instanceof ImmediateOperand) {
            a = ((ImmediateOperand) operandA).getValue();
        }
        else {
            a = ((VarSymbol) operandA).getAddress();
        }
        int b;
        if (operandB instanceof ImmediateOperand) {
            b = ((ImmediateOperand) operandB).getValue();
        }
        else {
            b = ((VarSymbol) operandB).getAddress();
        }
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "%":
                return a % b;
            case "<":
                return a < b ? 1 : 0;
            case ">":
                return a > b ? 1 : 0;
            default:
                return a ^ b;
        }
    }

    public String getMipsOp() {
        switch (operator) {
            case "+": {
                if (bisImm() | aisImm()) {
                    return "addiu";
                }
                return "addu";
            }
            case "-": {
                if (bisImm()) {
                    return "subiu";
                }
                else {
                    return "subu";
                }
            }
            case "*": {
                return "mul";
            }
            case "/":
            case "%":
                return "div";
            case "<": {
                if (bisImm() && Math.abs(((ImmediateOperand) operandB).getValue()) < 32768) {
                    return "slti";
                }
                else {
                    return "slt";
                }
            }
            case ">":
                return "sgt";
            default: {
                if (bisImm()) {
                    return "xori";
                }
                else {
                    return "xor";
                }
            }
        }
    }

    public String getOperator() {
        return operator;
    }

    public boolean mulOrDiv() {
        return operator.equals("*") || operator.equals("/") || operator.equals("%");
    }
}
