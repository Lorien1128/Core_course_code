package middle;

import organize.Expr;

import java.math.BigInteger;

public class Sum {
    private Expr expr;

    public Sum(String var, String begin, String end, String exprString) {
        long beginNum = Long.parseLong(begin);
        long endNum = Long.parseLong(end);
        expr = new Expr(BigInteger.ZERO);
        String s;
        for (long j = beginNum; j <= endNum; j++) {
            s = exprString;
            s = s.replaceAll("sin", "s~n");
            s = s.replaceAll("i", j + "");
            s = s.replaceAll("s~n", "sin");
            expr = expr.add(Parser.parser(s));
        }
    }

    public Expr getExpr() {
        return expr;
    }
}
