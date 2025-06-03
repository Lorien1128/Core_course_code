package term;

import java.math.BigInteger;
import java.util.Objects;

public class SinPower {
    private final BigInteger constant;
    private final BigInteger exp;
    private final int sign;

    SinPower(BigInteger exp, String mode) {
        if (mode.equals("constant")) {
            sign = exp.compareTo(BigInteger.ZERO);
            constant = exp.abs();
            this.exp = BigInteger.ZERO;
        } else {
            sign = 1;
            constant = BigInteger.ONE;
            this.exp = exp;
        }
    }

    SinPower(SinPower other) {
        constant = other.constant.add(BigInteger.ZERO);
        exp = other.exp.add(BigInteger.ZERO);
        sign = other.sign;
    }

    public String display() {
        if (exp.equals(BigInteger.ZERO)) {
            return "sin(" + constant.abs() + ")";
        }
        if (exp.equals(BigInteger.ONE)) {
            return "sin(x)";
        }
        return "sin(x**" + exp + ")";
    }

    public int sign() {
        return sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SinPower sinPower = (SinPower) o;
        return Objects.equals(constant, sinPower.constant) && Objects.equals(exp, sinPower.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constant, exp);
    }
}
