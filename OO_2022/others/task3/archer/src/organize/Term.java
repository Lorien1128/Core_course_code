package organize;

import organize.factor.BasicFactor;
import organize.factor.PowerFactor;
import organize.factor.SinOrCos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Term {
    private BigInteger coefficient = BigInteger.ONE;
    private ArrayList<BasicFactor> factors = new ArrayList<>();

    public ArrayList<BasicFactor> getFactors() {
        return factors;
    }

    public Term(BigInteger coefficient, ArrayList<BasicFactor> factors) {
        this.coefficient = coefficient;
        this.factors = factors;
    }

    Term(BigInteger coefficient) {
        this.coefficient = coefficient;
    }

    Term(char variable) {
        this.factors = new ArrayList<>();
        this.factors.add(new PowerFactor(1L));
    }

    public Term(BasicFactor bf) {
        this.factors.add(bf);
    }

    public BigInteger getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigInteger coefficient) {
        this.coefficient = coefficient;
    }

    public Term times(Term term) {
        ArrayList<BasicFactor> result = new ArrayList<>();
        result.addAll(this.getFactors());
        result.addAll(term.getFactors());
        Term termResult = new Term(this.coefficient.multiply(term.coefficient), result);
        termResult.simplify();
        return termResult;
    }

    private void simplify() {
        if (this.factors.size() < 2) {
            return;
        }
        HashMap<Expr, Long> sins = new HashMap<>();//bf映射到次数
        HashMap<Expr, Long> coss = new HashMap<>();//bf映射到次数
        long powerOfx = 0;
        for (BasicFactor bf : this.factors) {
            if (bf instanceof PowerFactor) {
                powerOfx += ((PowerFactor) bf).getPower();
            } else {
                assert bf instanceof SinOrCos;
                Expr expr = ((SinOrCos) bf).getParameter();
                if (((SinOrCos) bf).isSin()) {
                    if (sins.containsKey(expr)) {
                        sins.put(expr, sins.get(expr) + ((SinOrCos) bf).getPower());
                    } else {
                        sins.put(expr, ((SinOrCos) bf).getPower());
                    }
                } else {
                    if (coss.containsKey(expr)) {
                        coss.put(expr, coss.get(expr) + ((SinOrCos) bf).getPower());
                    } else {
                        coss.put(expr, ((SinOrCos) bf).getPower());
                    }
                }
            }
        }
        ArrayList<BasicFactor> result = new ArrayList<>();
        if (powerOfx > 0) {
            result.add(new PowerFactor(powerOfx));
        }
        for (Expr expr : sins.keySet()) {
            result.add(new SinOrCos(expr, sins.get(expr), true));
        }
        for (Expr expr : coss.keySet()) {
            result.add(new SinOrCos(expr, coss.get(expr), false));
        }
        this.factors = result;
    }

    public Term neg() {
        return new Term(this.coefficient.negate(), this.factors);
    }

    public String toStringNoSign() {
        StringBuilder s =
                new StringBuilder(coefficient.equals(BigInteger.ONE) ? "" :
                        coefficient.equals(BigInteger.ONE.negate()) ? "-" :
                                coefficient + "*");
        if (factors.size() == 0) {
            return "" + coefficient;
        }
        for (BasicFactor factor : factors) {
            s.append(factor.toString()).append("*");
        }
        return s.substring(0, s.length() - 1);
    }

    public String toString() {
        if (this.coefficient.compareTo(BigInteger.ZERO) > 0) {
            return "+" + this.toStringNoSign();
        } else {
            return this.toStringNoSign();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Term term = (Term) o;
        return Objects.equals(factors, term.factors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factors);
    }

    public boolean isBasic() {
        if (this.factors.isEmpty() || this.coefficient.equals(BigInteger.ZERO)) {
            return true;
        }
        if (this.factors.size() > 1) {
            return false;
        }
        return this.coefficient.equals(BigInteger.ONE) && this.factors.get(0).isBasic();
    }
}

