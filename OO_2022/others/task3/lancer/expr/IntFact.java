package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public class IntFact implements Factor {
    private BigInteger val;

    public IntFact(BigInteger val) {
        this.val = val;
    }

    public BigInteger getVal() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public IntFact clone() {
        return new IntFact(val);
    }

    @Override
    public Factor substitute(ArrayList<String> parameters, ArrayList<Expr> arguments) {
        return clone();
    }
}
