package term;

import java.math.BigInteger;
import java.util.HashSet;

public class Expr {
    private HashSet<Term> terms = new HashSet<>();

    public Expr(String str) {
        terms.add(new Term(str));
    }

    public Expr(SinPower sinPower) {
        terms.add(new Term(sinPower));
    }

    public Expr(CosPower cosPower) {
        terms.add((new Term(cosPower)));
    }

    public Expr(Expr other) {
        for (Term term : other.terms) {
            terms.add(new Term(term));
        }
    }

    public Expr() {

    }

    public void add(Term b) {
        Term record = null;
        for (Term term : terms) {
            if (Term.checkAdd(term, b)) {
                record = term;
                break;
            }
        }
        if (record != null) {
            terms.remove(record);
            terms.add(Term.add(record, b));
            return;
        }
        terms.add(b);
    }

    public void add(Expr other) {
        for (Term term : other.terms) {
            add(term);
        }
    }

    public void subtract(Term b) {
        add(Term.negate(b));
    }

    public void subtract(Expr other) {
        for (Term term : other.terms) {
            subtract(term);
        }
    }

    public void multiply(Expr b) {
        Expr c = new Expr();
        for (Term asTerm : terms) {
            for (Term bsTerm : b.terms) {
                c.add(Term.multiply(asTerm, bsTerm));
            }
        }
        terms = c.terms;
    }

    public static Expr negate(Expr other) {
        Expr tmp = new Expr();
        for (Term term : other.terms) {
            tmp.add(Term.negate(term));
        }
        return tmp;
    }

    public void power(BigInteger exp) {
        if (exp.equals(BigInteger.ZERO)) {
            terms.clear();
            terms.add(new Term("1"));
        } else {
            Expr tmp = new Expr(this);
            for (BigInteger i = BigInteger.ONE; i.compareTo(exp) < 0; i = i.add(BigInteger.ONE)) {
                tmp.multiply(this);
            }
            terms = tmp.terms;
        }
    }

    public BigInteger toInt() {
        for (Term term : terms) {
            return term.toInt();
        }
        return BigInteger.ZERO;
    }

    public SinPower toSin() {
        for (Term term : terms) {
            return term.toSin();
        }
        return null;
    }

    public CosPower toCos() {
        for (Term term : terms) {
            return term.toCos();
        }
        return null;
    }

    @Override
    public String toString() {
        String ans = "";
        for (Term term : terms) {
            if (term.display().equals("")) {
                continue;
            }
            if (term.sign() > 0) {
                ans += "+" + term.display();
            } else {
                ans += term.display();
            }
        }
        if (ans.equals("")) {
            return "0";
        }
        if (ans.charAt(0) == '+') {
            return ans.substring(1);
        }
        return ans;
    }
}