package expression;

import java.math.BigInteger;

public class StandardTerm implements Comparable<StandardTerm> {
    private BigInteger factor;
    private int exponent;

    public StandardTerm(Term term) {
        this.factor = new BigInteger("1");
        this.exponent = 0;
        for (Factor factor1: term.getFactors()) {
            if (factor1 instanceof SignedInteger) {
                this.factor = this.factor.multiply(((SignedInteger) factor1).getNumber());
            }
            else if (factor1 instanceof PowerFunction) {
                this.exponent += ((PowerFunction)factor1).getExponent();
            }
        }
    }

    public BigInteger getFactor() {
        return this.factor;
    }

    public int getExponent() {
        return this.exponent;
    }

    public void merge(StandardTerm other) {
        if (this.exponent == other.exponent) {
            this.factor = this.factor.add(other.factor);
            other.factor = new BigInteger("0");
        }
    }

    public void print(boolean isFirstTerm) {
        if ((!isFirstTerm) && (this.factor.compareTo(new BigInteger("0"))) > 0) {
            System.out.print("+");
        }

        if (this.factor.equals(new BigInteger("1"))) {
            if (this.exponent == 0) {
                System.out.print(this.factor);
            }
            else if (this.exponent == 1) {
                System.out.print("x");
            }
            else if (this.exponent == 2) {
                System.out.print("x*x");
            }
            else {
                System.out.print("x**" + this.exponent);
            }
        }
        else if (this.factor.equals(new BigInteger("-1"))) {
            if (this.exponent == 0) {
                System.out.print(this.factor);
            }
            else if (this.exponent == 1) {
                System.out.print("-x");
            }
            else if (this.exponent == 2) {
                System.out.print("-x*x");
            }
            else {
                System.out.print("-x**" + this.exponent);
            }
        }
        else {
            if (this.exponent == 0) {
                System.out.print(this.factor);
            }
            else if (this.exponent == 1) {
                System.out.print(this.factor + "*x");
            }
            else if (this.exponent == 2) {
                System.out.print(this.factor + "*x*x");
            }
            else {
                System.out.print(this.factor + "*x**" + this.exponent);
            }
        }
    }

    public int compareTo(StandardTerm other) {
        return this.exponent - other.exponent;
    }

}
