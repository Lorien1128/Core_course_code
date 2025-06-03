package exp;

import java.math.BigInteger;
import java.util.ArrayList;

public class Expression {
    private ArrayList<Term> terms;

    public Expression() {
        this.terms = new ArrayList<>();
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void reverse() {
        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i).reverse();
            terms.set(i, term);
        }
    }

    public void addTerm(Term term) {
        terms.add(term);
    }

    public void findPos() {
        Term firstPosTerm = terms.get(0);
        for (Term term : terms) {
            if (term.getCoefficient().compareTo(BigInteger.ZERO) > 0) {
                firstPosTerm = term;
                break;
            }
        }
        Term firstTerm = terms.get(0);
        int index = terms.indexOf(firstPosTerm);
        terms.set(0, firstPosTerm);
        terms.set(index, firstTerm);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        findPos();
        sb.append(terms.get(0).toString());
        for (int i = 1; i < terms.size();i++) {
            Term term = terms.get(i);
            if (term.getCoefficient().getNum().compareTo(BigInteger.ZERO) < 0) {
                sb.append("-");
                term = term.reverse();
                sb.append(term);
            } else {
                sb.append("+");
                sb.append(terms.get(i));
            }
        }
        return sb.toString();
    }
}
