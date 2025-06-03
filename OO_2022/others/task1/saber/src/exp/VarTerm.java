package exp;

import java.math.BigInteger;

public class VarTerm implements Term {
    private SignedInt coefficient;
    private Factor factor;

    public VarTerm(SignedInt coefficient, Factor factor) {
        this.coefficient = coefficient;
        if (coefficient.getNum().compareTo(BigInteger.ZERO) == 0) {
            this.factor = new Factor(BigInteger.ZERO);
        } else {
            this.factor = factor;
        }
    }

    @Override
    public Term plus(Term term) throws Exception {
        if (!this.factor.equals(term.getFactor())) {
            throw new Exception(String.format("Cannot plus two different term %s %s",
                factor, term.getFactor()));
        }
        SignedInt newCoeff = new SignedInt(
            coefficient.add(term.getCoefficient()).toString());// checked
        if (factor.getIndex().getNum().compareTo(BigInteger.ZERO) == 0) {
            return newCoeff;
        } else {
            Factor factor = new Factor(this.factor.getIndex().getNum());
            return new VarTerm(newCoeff, factor);
        }
    }

    @Override
    public Term mult(Term term) {
        SignedInt coefficient = new SignedInt(
            this.coefficient.multiply(term.getCoefficient()).toString());
        Factor factor = this.factor.add(term.getFactor());
        return new VarTerm(coefficient, factor);
    }

    @Override
    public boolean combinable(Term term) {
        return this.factor.equals(term.getFactor());
    }

    @Override
    public Factor getFactor() {
        return factor;
    }

    @Override
    public SignedInt getCoefficient() {
        return coefficient;
    }

    @Override
    public Term reverse() {
        SignedInt newCoeff = new SignedInt(coefficient.negate().toString());
        Factor newFactor = new Factor(new SignedInt(factor.getIndex().toString()));
        return new VarTerm(newCoeff, newFactor);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (coefficient.getNum().compareTo(BigInteger.ZERO) == 0) {
            sb.append(0);
        } else {
            if (coefficient.getNum().compareTo(BigInteger.ONE) != 0) {
                sb.append(coefficient.getNum().toString());
                if (factor.getIndex().getNum().compareTo(BigInteger.ZERO) != 0) {
                    sb.append("*");
                }
            }
            if (factor.getIndex().getNum().compareTo(BigInteger.ZERO) != 0) {
                sb.append("x");
                if (factor.getIndex().getNum().compareTo(BigInteger.ONE) != 0) {
                    if (factor.getIndex().compareTo(new BigInteger("2")) == 0) {
                        sb.append("*x");
                    } else {
                        sb.append("**");
                        sb.append(factor.getIndex().getNum().toString());
                    }
                }
            }
            if (sb.toString().equals("")) {
                sb.append(1);
            }
        }
        return sb.toString();
    }
}
