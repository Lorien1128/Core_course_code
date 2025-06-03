package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public class VarFact extends FactorWithExp {
    private String name;

    public VarFact(String name, BigInteger exp) {
        super(exp);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean canBeMerged(FactorWithExp other) {
        if (!(other instanceof VarFact)) {
            return false;
        }
        return name.equals(((VarFact) other).getName());
    }

    @Override
    public String toString() {
        if (getExp().compareTo(new BigInteger("1")) > 0) {
            return name + "**" + getExp();
        } else {
            return name;
        }
    }

    @Override
    public VarFact clone() {
        return new VarFact(name,getExp());
    }

    @Override
    public Factor substitute(ArrayList<String> parameters, ArrayList<Expr> arguments) {
        for (int i = 0; i < parameters.size(); i++) {
            String var = parameters.get(i);
            if (name.equals(var)) {
                return new ExprFact(arguments.get(i), getExp());
            }
        }
        return clone();
    }
}
