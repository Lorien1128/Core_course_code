package term;

import java.math.BigInteger;
import java.util.Objects;

public class CosPower {
    private BigInteger constant;
    private BigInteger exp;

    CosPower(BigInteger exp, String mode) {
        if (mode.equals("constant")) {
            constant = exp.abs();
            this.exp = BigInteger.ZERO;
        } else {
            constant = BigInteger.ONE;
            this.exp = exp;
        }
    }

    CosPower(CosPower other) {
        constant = other.constant.add(BigInteger.ZERO);
        exp = other.exp.add(BigInteger.ZERO);
    }

    public String display() {
        if (exp.equals(BigInteger.ZERO)) {
            return "cos(" + constant + ")";
        }
        if (exp.equals(BigInteger.ONE)) {
            return "cos(x)";
        }
        return "cos(x**" + exp + ")";
    }

    public boolean isOne() {
        return constant.equals(BigInteger.ZERO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CosPower cosPower = (CosPower) o;
        return Objects.equals(constant, cosPower.constant) && Objects.equals(exp, cosPower.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constant, exp);
    }
}
