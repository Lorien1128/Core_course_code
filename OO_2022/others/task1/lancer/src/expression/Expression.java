package expression;

import java.util.ArrayList;

public class Expression implements Factor {
    private int exponent;
    private ArrayList<Term> terms;

    public Expression() {
        this.exponent = 1;
        this.terms = new ArrayList<>();
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public int getExponent() {
        return this.exponent;
    }

    public void addTerm(Term term) {
        this.terms.add(term);
    }

    public ArrayList<Term> getTerms() {
        return this.terms;
    }

    public void print() {
        System.out.print("(");
        //terms.get(0).print();
        for (int i = 0; i < terms.size(); i++) {
            terms.get(i).print();
        }
        System.out.print(")");
        if (this.exponent != 1) {
            System.out.print("**" + this.exponent);
        }
    }

    public void preTreat() {
        for (Term term: this.terms) {
            term.preTreat();
        }
    }

    public void removeBrackets() {
        for (Term term: this.terms) {
            term.removeBrackets();
        }
    }

    public Expression multiply(Expression other) {
        Expression result = new Expression();
        if (this.terms.size() == 0) {
            for (Term term: other.terms) {
                result.addTerm(term);
            }
            return result;
        }
        else if (other.terms.size() == 0) {
            for (Term term: this.terms) {
                result.addTerm(term);
            }
            return result;
        }

        for (Term term1: this.terms) {
            for (Term term2: other.terms) {
                Term term = new Term();
                for (Factor factor1: term1.getFactors()) {
                    term.addFactor(factor1);
                }
                for (Factor factor2: term2.getFactors()) {
                    term.addFactor(factor2);
                }
                result.addTerm(term);
            }
        }
        return result;
    }

    public Expression multiply(SignedInteger signedInteger) {
        Expression result = new Expression();
        if (this.terms.size() == 0) {
            Term term = new Term();
            term.addFactor(signedInteger);
            result.addTerm(term);
        }
        else {
            for (Term term: this.terms) {
                term.addFactor(signedInteger);
                result.addTerm(term);
            }
        }
        return result;
    }

    public Expression multiply(PowerFunction powerFunction) {
        Expression result = new Expression();
        if (this.terms.size() == 0) {
            Term term = new Term();
            term.addFactor(powerFunction);
            result.addTerm(term);
        }
        else {
            for (Term term: this.terms) {
                term.addFactor(powerFunction);
                result.addTerm(term);
            }
        }
        return result;
    }

}
