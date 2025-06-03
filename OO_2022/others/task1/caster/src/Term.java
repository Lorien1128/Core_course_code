import java.math.BigInteger;
import java.util.ArrayList;
//import java.util.HashSet;

public class Term {
    private ArrayList<Factor> factors;

    public Term() {
        factors = new ArrayList<>();
    }

    public void addFactors(Factor factor) {
        factors.add(factor);
    }

    public Poly toPoly() {
        Poly res = new Poly(0, BigInteger.valueOf(1));      // 常数1
        for (Factor factor : factors) {
            res = res.mult(factor.toPoly());
        }
        return res;
    }
}
