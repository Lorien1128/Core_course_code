package mcode.operand;

import nonterminals.Number;

public class ImmediateOperand implements Operand {
    private int value;
    private boolean negative = false;
    private boolean inverted = false;

    @Override
    public boolean isInverted() {
        return inverted;
    }

    @Override
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public ImmediateOperand(Number number) {
        value = number.calculate();
    }

    public ImmediateOperand(int value) {
        this.value = value;
    }

    @Override
    public String getMark() {
        return String.valueOf(value);
    }

    @Override
    public boolean isNegative() {
        return negative;
    }

    @Override
    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public void reversal() {
        value = -1 * value;
    }

    public void boolReversal() {
        if (value == 0) {
            value = 1;
        }
        else {
            value = 0;
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ImmediateOperand) {
            return this.value == ((ImmediateOperand) obj).value;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
