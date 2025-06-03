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

    public String toString() {
        String output = "(";
        for (int i = 0; i < terms.size(); i++) {
            output += terms.get(i).toString();
        }
        output += ")";
        if (this.exponent != 1) {
            output += "**" + this.exponent;
        }
        return output;
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

    public Expression multiply(SineFunction sineFunction) {
        Expression result = new Expression();
        if (this.terms.size() == 0) {
            Term term = new Term();
            term.addFactor(sineFunction);
            result.addTerm(term);
        }
        else {
            for (Term term: this.terms) {
                term.addFactor(sineFunction);
                result.addTerm(term);
            }
        }
        return result;
    }

    public Expression multiply(CosineFunction cosineFunction) {
        Expression result = new Expression();
        if (this.terms.size() == 0) {
            Term term = new Term();
            term.addFactor(cosineFunction);
            result.addTerm(term);
        }
        else {
            for (Term term: this.terms) {
                term.addFactor(cosineFunction);
                result.addTerm(term);
            }
        }
        return result;
    }

    public int size() {
        return this.terms.size();
    }

    public Term getTerm(int i) {
        return this.terms.get(i);
    }

    public StandardExpression expandAndMerge() {
        StandardExpression result = new StandardExpression();
        for (Term term: this.terms) {
            StandardExpression standardExpression = new StandardExpression(1);
            for (Factor factor: term.getFactors()) {
                if (factor instanceof SignedInteger) {
                    standardExpression.multiply((SignedInteger) factor);
                }
                else if (factor instanceof PowerFunction) {
                    standardExpression.multiply((PowerFunction) factor);
                }
                else if (factor instanceof SineFunction) {
                    standardExpression.multiply((SineFunction) factor);
                }
                else if (factor instanceof CosineFunction) {
                    standardExpression.multiply((CosineFunction) factor);
                }
                else if (factor instanceof Expression) {
                    standardExpression.multiply((Expression) factor);
                }
            }
            for (StandardTerm standardTerm: standardExpression.getTerms()) {
                result.addTerm(standardTerm);
                result.merge();
            }
        }
        return result;
    }

}
