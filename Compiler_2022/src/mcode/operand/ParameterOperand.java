package mcode.operand;

public class ParameterOperand implements Operand {
    private final boolean isReg;
    private final int index;
    private boolean inverted = false;

    @Override
    public boolean isInverted() {
        return inverted;
    }

    @Override
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public ParameterOperand(int index) {
        this.index = index;
        isReg = index <= 3;
    }

    @Override
    public String getMark() {
        if (isReg) {
            return "$a" + index;
        }
        else {
            return "$sp - "  + 4 * (index + 1);
        }
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public void setNegative(boolean negative) {

    }
}
