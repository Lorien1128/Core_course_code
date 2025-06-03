package expr;

import java.util.ArrayList;

public class Function {
    private String name;
    private ArrayList<String> parameters;
    private Expr funcExpr;

    public Function(String name, ArrayList<String> parameters, Expr funcExpr) {
        this.name = name;
        this.parameters = parameters;
        this.funcExpr = funcExpr;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public Expr getFuncExpr() {
        return funcExpr;
    }

    public void expand() {
        ArrayList<Function> empty = new ArrayList<>();
        funcExpr.expand(empty);
    }

    @Override
    public String toString() {
        String ret = name + "(" + parameters.get(0);
        for (int i = 1; i < parameters.size(); i++) {
            ret += "," + parameters.get(i);
        }
        ret += ") = " + funcExpr;
        return ret;
    }
}
