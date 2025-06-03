package mcode.operand;

public interface Operand {

    boolean isInverted();

    void setInverted(boolean inverted);

    String getMark();

    boolean isNegative();

    void setNegative(boolean negative);

    default String getMipsSym() {
        return getMark();
    }
}
