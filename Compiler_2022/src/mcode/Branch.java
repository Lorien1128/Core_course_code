package mcode;

import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;

import java.util.ArrayList;

public class Branch implements MidCode {
    private final boolean equal;
    private Operand operandA;
    private Operand operandB;
    private final Label terminal;

    public Branch(boolean equal, Operand operandA, Operand operandB,
                  Label terminal) {
        this.equal = equal;
        this.operandA = operandA;
        this.operandB = operandB;
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        String inst = equal ? "BEQ" : "BNE";
        return inst + " " + operandA.getMark() + " " +
                operandB.getMark() + " " + terminal.getName() + "\n";
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
        return new ArrayList<>();
    }

    @Override
    public void replaceSource(Operand from, Operand to) {
        if (operandA.getMark().equals(from.getMark())) {
            operandA = to;
        }
        if (operandB.getMark().equals(from.getMark())) {
            operandB = to;
        }
    }

    public Operand getOperandA() {
        return operandA;
    }

    public Operand getOperandB() {
        return operandB;
    }

    public Label getTerminal() {
        return terminal;
    }

    public boolean isEqual() {
        return equal;
    }

    public boolean allImm() {
        return operandA instanceof ImmediateOperand &&
                operandB instanceof ImmediateOperand;
    }

    public boolean jump() {
        return equal == (((ImmediateOperand) operandA).getValue() ==
                ((ImmediateOperand) operandB).getValue());
    }
}
