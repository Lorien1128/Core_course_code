package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public class SumFact implements Factor {
    private String var;
    private BigInteger start;
    private BigInteger end;
    private Expr sumExpr;

    public SumFact(String var, BigInteger start, BigInteger end, Expr sumExpr) {
        this.var = var;
        this.start = start;
        this.end = end;
        this.sumExpr = sumExpr;
    }

    @Override
    public Factor clone() {
        return new SumFact(var,start,end,sumExpr.clone());
    }

    @Override
    public Factor substitute(ArrayList<String> parameters, ArrayList<Expr> arguments) {
        return new SumFact(var,start,end,sumExpr.substitute(parameters,arguments));
    }

    @Override
    public String toString() {
        return "sum(" + var + "," + start + "," + end + "," + sumExpr + ")";
    }

    public Factor replace() {
        Expr expr = new Expr();
        for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(new BigInteger("1"))) {
            Expr tmpExpr = new Expr();
            Term tmpTerm = new Term(i);
            tmpExpr.addTerm(tmpTerm);
            ArrayList<String> parameters = new ArrayList<>();
            ArrayList<Expr> arguments = new ArrayList<>();
            parameters.add(var);
            arguments.add(tmpExpr);
            expr.add(sumExpr.substitute(parameters,arguments));
        }
        ExprFact ret = new ExprFact(expr,new BigInteger("1"));
        return ret;
    }

    public void expand(ArrayList<Function> functionLib) {
        sumExpr.expand(functionLib);
    }
}
