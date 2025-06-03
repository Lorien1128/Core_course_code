package organize;

import organize.factor.BasicFactor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * 表达式
 */
public class Expr {

    public static final Expr ZERO = new Expr(BigInteger.ZERO);
    public static final Expr ONE = new Expr(BigInteger.ONE);

    private ArrayList<Term> terms = new ArrayList<>();

    public Expr(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public Expr(BigInteger bigInteger) {
        terms.add(new Term(bigInteger));
    }

    public Expr(char variable) {
        if (variable == 'x') {
            terms.add(new Term('x'));
        }
    }

    public Expr(BasicFactor bf) {
        terms.add(new Term(bf));
    }

    public Expr times(Expr expr) {
        if (this.terms.isEmpty()) {
            return this;
        } else if (expr.terms.isEmpty()) {
            return expr;
        } else {
            ArrayList<Term> result = new ArrayList<>();
            for (Term t1 : expr.terms) {
                for (Term t2 : this.terms) {
                    result.add(t1.times(t2));
                }
            }
            Expr exprResult = new Expr(result);
            exprResult.simpilize();//化简
            return exprResult;
        }
    }

    public Expr add(Expr expr) {
        ArrayList<Term> result = new ArrayList<>();
        result.addAll(expr.terms);
        result.addAll(this.terms);
        Expr exprResult = new Expr(result);
        exprResult.simpilize();//化简
        return exprResult;
    }

    private void simpilize() {
        //trem的哈希不包含coefficient
        if (this.terms.size() < 2) {
            return;
        }
        HashMap<Term, BigInteger> terms = new HashMap<>();
        for (Term term : this.terms) {
            if (terms.containsKey(term)) {
                terms.put(term, terms.get(term).add(term.getCoefficient()));
            } else {
                terms.put(term, term.getCoefficient());
            }
        }
        ArrayList<Term> result = new ArrayList<>();
        for (Term term : terms.keySet()) {
            if (!terms.get(term).equals(BigInteger.ZERO)) {
                Term nt = new Term(terms.get(term), term.getFactors());
                result.add(nt);
            }
        }
        this.terms = result;

    }

    public Expr neg() {
        ArrayList<Term> result = new ArrayList<>();
        for (Term term : terms) {
            result.add(term.neg());
        }
        return new Expr(result);
    }

    public String toString() {
        if (this.terms.isEmpty()) {
            return "0";
        }
        if (this.terms.size() == 1 && terms.get(0).getCoefficient().equals(new BigInteger("0"))) {
            return "0";
        }
        //想到一个办法，把第一个遇到的没有负号的项放在最前面
        boolean flag = true;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            if (!terms.get(i).toString().equals("0")) {
                if (flag && terms.get(i).getCoefficient().compareTo(BigInteger.ZERO) > 0) {
                    flag = false;
                    s.insert(0, terms.get(i).toStringNoSign());
                } else {
                    s.append(terms.get(i).toString());
                }
            }
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        //项相等，两个expr就相等
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HashSet<String> terms1 = new HashSet<>();
        for (Term t : ((Expr) o).terms) {
            //这里应该只把非零项加进去
            if (!t.toString().equals("0")) {
                terms1.add(t.toString());
            }

        }
        HashSet<String> terms2 = new HashSet<>();
        for (Term t : this.terms) {
            if (!t.toString().equals("0")) {
                terms2.add(t.toString());
            }
        }
        return Objects.equals(terms1, terms2);
    }

    @Override
    public int hashCode() {
        HashSet<String> terms = new HashSet<>();
        for (Term t : this.terms) {
            terms.add(t.toString());
        }
        return Objects.hash(terms);
    }

    public boolean isBasic() {
        if (this.terms.isEmpty()) {
            return true;
        }
        if (this.terms.size() > 1) {
            return false;
        }
        return this.terms.get(0).isBasic();
    }
}
