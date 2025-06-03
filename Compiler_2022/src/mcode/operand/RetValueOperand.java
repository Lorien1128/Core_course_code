package mcode.operand;

public class RetValueOperand implements Operand {
    private boolean inverted = false;

    @Override
    public boolean isInverted() {
        return inverted;
    }

    @Override
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    @Override
    public String getMark() {
        return "$v0";
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public void setNegative(boolean negative) {

    }
}
