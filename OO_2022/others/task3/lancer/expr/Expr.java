package expr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Expr implements Comparable<Expr> {
    private ArrayList<Term> terms;

    public Expr() {
        terms = new ArrayList<>();
    }

    public Expr(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void addTerm(Term term) {
        terms.add(term);
    }

    public void add(Expr expr) {
        terms.addAll(expr.getTerms());
    }

    public void removeTerm(Term term) {
        terms.remove(term);
    }

    public String toString() {
        if (terms.size() == 0) {
            return "0";
        }
        String ret = "";
        boolean flag = true;
        for (Term term : terms) {
            ret += term.toString(flag);
            if (flag) {
                flag = false;
            }
        }
        return ret;
    }

    public void funcReplace(ArrayList<Function> functionLib) {
        for (Term term : terms) {
            term.funcReplace(functionLib);
        }
    }

    private void simplify() {
        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i);
            term.simplify();
            if (term.getCoef().equals(new BigInteger("0"))) {
                terms.remove(i);
                i--;
            }
        }
    }

    private void merge() {
        Collections.sort(terms);
        for (int i = 0; i < terms.size() - 1; i++) {
            Term term = terms.get(i);
            Term next = terms.get(i + 1);
            if (term.canBeMerged(next)) {
                BigInteger tmpCoef = term.getCoef().add(
                        next.getCoef());
                term.setCoef(tmpCoef);
                terms.remove(i + 1);
                if (term.getCoef().equals(new BigInteger("0"))) {
                    terms.remove(i);
                }
                i--;
            }
        }
    }

    private void splitAll(ArrayList<Function> functionLib) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (Term term : terms) {
                if (term.isSplittable()) {
                    term.split(this,functionLib);
                    flag = true;
                    break;
                }
            }
        }
    }

    public void expand(ArrayList<Function> functionLib) {
        funcReplace(functionLib);
        splitAll(functionLib);
        simplify();
        merge();
        return;
    }

    public Expr substitute(ArrayList<String> parameters, ArrayList<Expr> arguments) {
        Expr ret = new Expr();
        for (Term term : terms) {
            ret.addTerm(term.substitute(parameters,arguments));
        }
        return ret;
    }

    public Expr clone() {
        Expr ret = new Expr();
        for (Term term : terms) {
            ret.addTerm(term.clone());
        }
        return ret;
    }

    @Override
    public int compareTo(Expr other) {
        if (terms.size() != other.getTerms().size()) {
            if (terms.size() < other.getTerms().size()) {
                return -1;
            }
            if (terms.size() > other.getTerms().size()) {
                return 1;
            }
        }
        for (int i = 0; i < terms.size(); i++) {
            Term termThis = terms.get(i);
            Term termOther = other.getTerms().get(i);
            if (termThis.compareTo(termOther) != 0) {
                return termThis.compareTo(termOther);
            }
        }
        return 0;
    }

    public String optimizedOutput() {
        if (terms.size() == 0) {
            return "0";
        }
        if (terms.get(0).getCoef().compareTo(new BigInteger("0")) < 0) {
            int tar = 0;
            for (int i = 1; i < terms.size(); i++) {
                Term term = terms.get(i);
                if (term.getCoef().compareTo(new BigInteger("0")) > 0) {
                    tar = i;
                    break;
                }
            }
            Collections.swap(terms,0,tar);
            String ret = toString();
            Collections.swap(terms,0,tar);
            return ret;
        } else {
            return toString();
        }
    }

    public boolean isSingleFactor() {
        if (terms.size() == 0) {
            return true;
        }
        if (terms.size() == 1) {
            return terms.get(0).isSingleFactor();
        }
        return false;
    }
}
