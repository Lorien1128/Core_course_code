package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public class FuncFact implements Factor {
    private String name;
    private ArrayList<Expr> arguments;

    public FuncFact(String name) {
        this.name = name;
    }

    public FuncFact(String name, ArrayList<Expr> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public void addArgument(Expr argument) {
        arguments.add(argument);
    }

    @Override
    public Factor clone() {
        FuncFact ret = new FuncFact(name);
        for (Expr argument : arguments) {
            ret.addArgument(argument.clone());
        }
        return ret;
    }

    @Override
    public Factor substitute(ArrayList<String> parameters, ArrayList<Expr> arguments) {
        FuncFact ret = new FuncFact(name);
        for (Expr argument : arguments) {
            ret.addArgument(argument.substitute(parameters,arguments));
        }
        return ret;
    }

    @Override
    public String toString() {
        String ret = name + "(";
        ret += arguments.get(0);
        for (int i = 1; i < arguments.size();i++) {
            ret += "," + arguments.get(i);
        }
        ret += ")";
        return ret;
    }

    public Factor replace(ArrayList<Function> functionLib) {
        for (Function function : functionLib) {
            if (name.equals(function.getName())) {
                Expr expr = function.getFuncExpr().substitute(
                        function.getParameters(),arguments);
                return new ExprFact(expr,new BigInteger("1"));
            }
        }
        return clone();
    }

    public void expand(ArrayList<Function> functionLib) {
        for (Expr argument : arguments) {
            argument.expand(functionLib);
        }
    }
}
