package arithtool;

import exp.Expression;
import exp.SignedInt;
import exp.Term;

import java.math.BigInteger;
import java.util.ArrayList;

public class Arith {
    public static Expression plus(Expression a, Expression b) {
        Expression ret = new Expression();
        ArrayList<Term> termsA = a.getTerms();
        ArrayList<Term> termsB = b.getTerms();
        if (termsA.size() == 1 && a.toString().equals("0")) {
            for (Term term : termsB) {
                ret.addTerm(term);
            }
            return ret;
        } else if (termsB.size() == 1 && b.toString().equals("0")) {
            for (Term term : termsA) {
                ret.addTerm(term);
            }
            return ret;
        }
        for (Term res : termsA) {
            ArrayList<Term> dels = new ArrayList<>();
            for (Term term : termsB) {
                if (res.combinable(term)) {
                    try {
                        res = res.plus(term);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    dels.add(term);
                }
            }
            for (Term del : dels) {
                termsB.remove(del);
            }
            ret.addTerm(res);
        }
        for (Term term : termsB) {
            ret.addTerm(term);
        }
        return ret;
    }

    public static Expression multiply(Expression a, Expression b) {
        Expression ret = new Expression();
        ArrayList<Term> termsA = a.getTerms();
        ArrayList<Term> termsB = b.getTerms();
        Term term = termsB.get(0);
        for (Term value : termsA) {
            try {
                ret.addTerm(term.mult(value));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        for (int i = 1; i < termsB.size(); i += 1) {
            Expression expression = new Expression();
            term = termsB.get(i);
            for (Term value : termsA) {
                try {
                    expression.addTerm(term.mult(value));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            ret = plus(ret, expression);
        }
        return ret;
    }

    public static Expression power(Expression a, Term b) {
        Expression ret = a;
        if (b.getCoefficient().getNum().compareTo(BigInteger.ZERO) == 0) {
            ret = new Expression();
            ret.addTerm(new SignedInt("1"));
            return ret;
        }
        for (BigInteger i = BigInteger.ONE;
             i.compareTo(b.getCoefficient().getNum()) < 0; i = i.add(BigInteger.ONE)) {
            ret = multiply(ret, a);
        }
        return ret;
    }
}
