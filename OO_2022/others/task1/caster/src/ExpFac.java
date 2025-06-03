import java.math.BigInteger;
//import java.util.HashSet;

public class ExpFac extends Factor {
    private Expression exp;
    private int power;

    public ExpFac(Expression exp, int power) {
        this.exp = exp;
        this.power = power;
    }

    @Override
    public Poly toPoly() {
        if (power == 0) {
            return new Poly(0,BigInteger.valueOf(1));       // 常数1
        }
        else {
            Poly poly = exp.toPoly();
            Poly res = new Poly(0,BigInteger.valueOf(1));       // 常数1
            for (int i = 1;i <= power;++i) {
                res = res.mult(poly);
            }
            return res;
        }
    }
}
