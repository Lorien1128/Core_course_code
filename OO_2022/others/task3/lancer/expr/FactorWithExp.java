package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public abstract class FactorWithExp implements Factor {
    private BigInteger exp;

    public FactorWithExp(BigInteger exp) {
        this.exp = exp;
    }

    public BigInteger getExp() {
        return exp;
    }

    public void setExp(BigInteger exp) {
        this.exp = exp;
    }

    public abstract boolean canBeMerged(FactorWithExp other);

    @Override
    public abstract  String toString();

    @Override
    public abstract  Factor clone();

    @Override
    public abstract  Factor substitute(ArrayList<String> parameters, ArrayList<Expr> arguments);
}
