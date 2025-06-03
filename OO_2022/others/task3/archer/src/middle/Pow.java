package middle;

import organize.Expr;

import java.math.BigInteger;

public class Pow {
    private Expr foot;
    private int index;
    private Expr result;

    public Pow(Expr foot, int index) {
        this.foot = foot;
        this.index = index;
        this.standardize();
    }

    private void standardize() {
        if (index == 0) {
            result = new Expr(BigInteger.ONE);
            return;
        }
        //TODO 也许这里会有问题（关于深拷贝
        Expr expr = foot;
        for (int i = 1; i < index; i++) {
            expr = expr.times(foot);
        }
        this.result = expr;
    }

    public Expr getExpr() {
        return result;
    }
}
