package term;

import java.math.BigInteger;
import java.util.Objects;

public class Power {
    private BigInteger exp;

    Power(BigInteger exp) {
        this.exp = exp;
    }

    Power(Power other) {
        exp = other.exp.add(BigInteger.ZERO);
    }

    public String display() {
        if (exp.equals(BigInteger.ZERO)) {
            return "";
        }
        if (exp.equals(BigInteger.ONE)) {
            return "x";
        }
        if (exp.equals(BigInteger.valueOf(2))) {
            return "x*x";
        }
        return "x**" + exp;
    }

    public BigInteger getExp() {
        return exp;
    }

    public boolean isConstant() {
        return exp.equals(BigInteger.ZERO);
    }

    public static Power multiply(Power a, Power b) {
        return new Power(a.exp.add(b.exp));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Power power = (Power) o;
        return Objects.equals(exp, power.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exp);
    }
}
