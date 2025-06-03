package module;

import java.math.BigInteger;
import java.util.ArrayList;

public class Expr implements ExprAndFactor, ExprAndTerm {
    private ArrayList<Term> terms = new ArrayList<>();
    private int power = 1;

    public void addTerm(ExprAndTerm exprAndTerm) {
        if (exprAndTerm instanceof Term) {
            terms.add((Term) exprAndTerm);
        } else {
            Expr expr = (Expr) exprAndTerm;
            terms.addAll(expr.getTerms());
        }
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public void mergeTerms() {
        BigInteger[] coefficients = new BigInteger[199];
        for (Term t : terms) {
            int j = t.getTermPower();
            if (coefficients[j] == null) {
                coefficients[j] = t.getCoefficient();
            } else {
                coefficients[j] = coefficients[j].add(t.getCoefficient());
            }
        }
        ArrayList<Term> result = new ArrayList<>();
        for (int i = 0; i < 199; i++) {
            if (coefficients[i] != null) {
                if (!coefficients[i].equals(new BigInteger("0"))) {
                    Term term = new Term(coefficients[i], i);
                    result.add(term);
                }
            }
        }
        this.terms = result;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Term term : terms) {
            if (term.getCoefficient().compareTo(new BigInteger("0")) > 0) {
                output.append("+");
            }
            if (term.getTermPower() == 0) {
                output.append(term.getCoefficient());
            } else if (term.getTermPower() == 1) {
                if (!term.getCoefficient().equals(new BigInteger("1"))) {
                    if (!term.getCoefficient().equals(new BigInteger("-1"))) {
                        output.append(term.getCoefficient());
                        output.append("*");
                    } else {
                        output.append("-");
                    }
                }
                output.append("x");
            } else if (term.getTermPower() == 2) {
                if (!term.getCoefficient().equals(new BigInteger("1"))) {
                    if (!term.getCoefficient().equals(new BigInteger("-1"))) {
                        output.append(term.getCoefficient());
                        output.append("*");
                    } else {
                        output.append("-");
                    }
                }
                output.append("x*x");
            } else {
                if (!term.getCoefficient().equals(new BigInteger("1"))) {
                    if (!term.getCoefficient().equals(new BigInteger("-1"))) {
                        output.append(term.getCoefficient());
                        output.append("*");
                    } else {
                        output.append("-");
                    }
                }
                output.append("x**");
                output.append(term.getTermPower());
            }
        }

        String result = output.toString();
        if (result.indexOf("+") == 0) {
            return result.substring(1);
        } else if (result.equals("")) {
            return "0";
        } else {
            return result;
        }
    }
}
