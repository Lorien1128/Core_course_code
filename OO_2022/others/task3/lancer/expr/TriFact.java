package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public class TriFact extends FactorWithExp {
    private String name;
    private Expr triExpr;

    public TriFact(String name, Expr triExpr, BigInteger exp) {
        super(exp);
        this.name = name;
        this.triExpr = triExpr;
    }

    public String getName() {
        return name;
    }

    public Expr getTriExpr() {
        return triExpr;
    }

    public void expand(ArrayList<Function> functionLib) {
        triExpr.expand(functionLib);
    }

    @Override
    public Factor clone() {
        return new TriFact(name,triExpr.clone(),getExp());
    }

    @Override
    public boolean canBeMerged(FactorWithExp other) {
        if (!(other instanceof TriFact)) {
            return false;
        }
        return (name.equals(((TriFact) other).getName()) &&
                (triExpr.compareTo(((TriFact) other).triExpr) == 0));
    }

    @Override
    public String toString() {
        String ret = "";
        if (triExpr.isSingleFactor()) {
            ret = name + "(" + triExpr.optimizedOutput() + ")";
        } else {
            ret = name + "((" + triExpr.optimizedOutput() + "))";
        }
        if (getExp().compareTo(new BigInteger("1")) > 0) {
            ret += "**" + getExp();
        }
        return ret;
    }

    @Override
    public Factor substitute(ArrayList<String> parameters, ArrayList<Expr> arguments) {
        return new TriFact(name,triExpr.substitute(parameters,arguments),getExp());
    }

    public boolean betterReverse() {
        ArrayList<Term> terms = triExpr.getTerms();
        if (terms.size() == 0) {
            return false;
        }
        if (terms.get(0).getCoef().compareTo(new BigInteger("0")) < 0) {
            return true;
        } else {
            return false;
        }
    }
}
