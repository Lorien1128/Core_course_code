package expr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Term implements Comparable<Term> {
    private ArrayList<Factor> factors;
    private BigInteger coef;

    public Term(BigInteger coef) {
        this.coef = coef;
        factors = new ArrayList<>();
    }

    public Term(ArrayList<Factor> factors, BigInteger coef) {
        this.factors = factors;
        this.coef = coef;
    }

    public ArrayList<Factor> getFactors() {
        return factors;
    }

    public void addFactor(Factor factor) {
        factors.add(factor);
    }

    public BigInteger getCoef() {
        return coef;
    }

    public void setCoef(BigInteger coef) {
        this.coef = coef;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean isFirst) {
        if (coef.equals(new BigInteger("0"))) {
            if (isFirst) {
                return "0";
            } else {
                return "+0";
            }
        }
        String ret = "";
        if (coef.compareTo(new BigInteger("0")) < 0) {
            ret += "-";
        } else if (!isFirst) {
            ret += "+";
        }
        if (factors.size() == 0) {
            ret += coef.abs();
            return ret;
        }
        if (coef.abs().equals(new BigInteger("1"))) {
            boolean flag = true;
            for (Factor factor : factors) {
                ret += factor.toString(flag);
                if (flag) {
                    flag = false;
                }
            }
        } else {
            ret += coef.abs();
            for (Factor factor : factors) {
                ret += factor.toString(false);
            }
        }
        return ret;
    }

    public void updateCoef() {
        BigInteger tmp = coef;
        Iterator<Factor> ite = factors.iterator();
        while (ite.hasNext()) {
            Factor factor = ite.next();
            if (factor instanceof IntFact) {
                tmp = tmp.multiply(((IntFact) factor).getVal());
                ite.remove();
            }
        }
        coef = tmp;
    }

    public void simplify() {
        updateCoef();
        Iterator<Factor> ite = factors.iterator();
        while (ite.hasNext()) {
            Factor factor = ite.next();
            if (factor instanceof  TriFact) {
                if (((TriFact) factor).getTriExpr().getTerms().size() == 0) {
                    if (((TriFact) factor).getName().equals("sin")) {
                        coef = coef.multiply(new BigInteger("0"));
                    }
                    ite.remove();
                } else if (((TriFact) factor).betterReverse()) {
                    ArrayList<Term> terms = ((TriFact) factor).getTriExpr().getTerms();
                    for (Term term : terms) {
                        term.setCoef(term.getCoef().negate());
                    }
                    if (((TriFact) factor).getName().equals("sin")) {
                        coef = coef.negate();
                    }
                }
            }
        }
        Collections.sort(factors);
        for (int i = 0; i < factors.size() - 1; i++) {
            Factor factor = factors.get(i);
            Factor next = factors.get(i + 1);
            if (factor instanceof FactorWithExp && next instanceof FactorWithExp) {
                if (((FactorWithExp) factor).canBeMerged((FactorWithExp) next)) {
                    BigInteger newExp = ((FactorWithExp) factor).getExp().add(
                            ((FactorWithExp) next).getExp());
                    ((FactorWithExp) factor).setExp(newExp);
                    factors.remove(i + 1);
                    i--;
                }
            }
        }
    }

    public BigInteger getPower() {
        BigInteger expSum = new BigInteger("0");
        for (Factor factor : factors) {
            if (factor instanceof FactorWithExp) {
                expSum = expSum.add(((FactorWithExp) factor).getExp());
            }
        }
        return expSum;
    }

    public boolean isSplittable() {
        for (Factor factor : factors) {
            if (factor instanceof ExprFact) {
                return true;
            }
        }
        return false;
    }

    public void split(Expr expr,ArrayList<Function> functionLib) {
        for (int i = 0; i < factors.size(); i++) {
            Factor factor = factors.get(i);
            if (factor instanceof ExprFact) {
                ((ExprFact) factor).expand(functionLib);
                if (((ExprFact) factor).getExp().compareTo(new BigInteger("1")) > 0) {
                    ((ExprFact) factor).setExp(
                            ((ExprFact) factor).getExp().subtract(new BigInteger("1")));
                } else {
                    factors.remove(factor);
                }
                ArrayList<Term> terms = ((ExprFact) factor).getExpr().getTerms();
                for (Term term : terms) {
                    ArrayList<Factor> tmp = new ArrayList<>();
                    for (Factor fact : factors) {
                        tmp.add(fact.clone());
                    }
                    tmp.addAll(term.getFactors());
                    Term newTerm = new Term(tmp,term.getCoef().multiply(coef));
                    expr.addTerm(newTerm);
                }
                expr.removeTerm(this);
                break;
            }
        }
    }

    public Term substitute(ArrayList<String> parameters, ArrayList<Expr> arguments) {
        Term ret = new Term(coef);
        for (Factor factor : factors) {
            ret.addFactor(factor.substitute(parameters,arguments));
        }
        return ret;
    }
    
    public Term clone() {
        Term ret = new Term(coef);
        for (Factor factor : factors) {
            ret.addFactor(factor.clone());
        }
        return ret;
    }

    @Override
    public int compareTo(Term other) {
        if (!this.getPower().equals(other.getPower())) {
            return this.getPower().compareTo(other.getPower());
        }
        if (factors.size() != other.getFactors().size()) {
            if (factors.size() < other.getFactors().size()) {
                return -1;
            }
            if (factors.size() > other.getFactors().size()) {
                return 1;
            }
        }
        for (int i = 0; i < factors.size(); i++) {
            Factor factorThis = factors.get(i);
            Factor factorOther = other.getFactors().get(i);
            if (factorThis.compareTo(factorOther) != 0) {
                return factorThis.compareTo(factorOther);
            }
        }
        return coef.compareTo(other.getCoef());
    }

    public void funcReplace(ArrayList<Function> functionLib) {
        for (int i = 0; i < factors.size(); i++) {
            Factor factor = factors.get(i);
            if (factor instanceof FuncFact) {
                ((FuncFact) factor).expand(functionLib);
                factors.add(((FuncFact) factor).replace(functionLib));
                factors.remove(i);
                i--;
                continue;
            }
            if (factor instanceof SumFact) {
                ((SumFact) factor).expand(functionLib);
                factors.add(((SumFact) factor).replace());
                factors.remove(i);
                i--;
                continue;
            }
            if (factor instanceof ExprFact) {
                ((ExprFact) factor).expand(functionLib);
            }
            if (factor instanceof TriFact) {
                ((TriFact) factor).expand(functionLib);
            }
        }
    }

    public boolean canBeMerged(Term other) {
        if (factors.size() != other.getFactors().size()) {
            return false;
        }
        for (int i = 0; i < factors.size(); i++) {
            Factor factorThis = factors.get(i);
            Factor factorOther = other.getFactors().get(i);
            if (factorThis.compareTo(factorOther) != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSingleFactor() {
        if (factors.size() == 0) {
            return true;
        } else if (factors.size() == 1 && coef.equals(new BigInteger("1"))) {
            return true;
        }
        return false;
    }
}
