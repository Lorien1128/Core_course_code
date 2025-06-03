package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public class ExprFact extends FactorWithExp {
    private Expr expr;

    public ExprFact(Expr expr,BigInteger exp) {
        super(exp);
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void expand(ArrayList<Function> functionLib) {
        expr.expand(functionLib);
    }

    @Override
    public boolean canBeMerged(FactorWithExp other) {
        return false;
    }

    @Override
    public String toString() {
        String ret = "(" + expr + ")";
        if (getExp().compareTo(new BigInteger("1")) > 0) {
            ret += "**" + getExp();
        }
        return ret;
    }

    @Override
    public ExprFact clone() {
        return new ExprFact(expr.clone(),getExp());
    }

    @Override
    public Factor substitute(ArrayList<String> parameters, ArrayList<Expr> arguments) {
        return new ExprFact(expr.substitute(parameters,arguments),getExp());
    }
}
