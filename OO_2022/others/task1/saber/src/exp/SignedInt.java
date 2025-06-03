package exp;

import java.math.BigInteger;

public class SignedInt extends BigInteger implements Term {

    public SignedInt(String string) {
        super(string);
    }

    public BigInteger getNum() {
        return this;
    }

    // Term
    @Override
    public boolean combinable(Term term) {
        return term.getFactor().equals(this.getFactor());
    }

    @Override
    public Term plus(Term term) throws Exception {
        if (!this.getFactor().equals(term.getFactor())) {
            throw new Exception(String.format("Cannot plus two different term %s %s",
                this, term.getFactor()));
        }
        return new SignedInt(this.add(term.getCoefficient()).toString());//checked
    }

    @Override
    public Term mult(Term term) {
        Factor retFactor = term.getFactor();
        SignedInt coeff = new SignedInt(this.multiply(term.getCoefficient()).toString());
        return new VarTerm(coeff, retFactor);// checked
    }

    @Override
    public Factor getFactor() {
        return new Factor(BigInteger.ZERO);
    }

    @Override
    public SignedInt getCoefficient() {
        return this;
    }

    @Override
    public Term reverse() {
        return new SignedInt(this.negate().toString());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
