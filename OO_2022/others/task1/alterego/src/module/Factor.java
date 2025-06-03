package module;

import java.math.BigInteger;

public class Factor implements ExprAndFactor {
    private BigInteger constant;
    private int power;

    public void setConstant(BigInteger constant) {
        this.constant = constant;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public BigInteger getConstant() {
        return constant;
    }

    public int getPower() {
        return power;
    }
}
