import java.math.BigInteger;

public class Factor {
    private BigInteger[] factors = new BigInteger[9];

    public void factor(String str) {
        for (int i = 0; i < 9; i++) {
            factors[i] = BigInteger.ZERO;
        }
        if (str.equals("x")) {
            factors[1] = BigInteger.ONE;
        }
        if (str.matches("[+-]?[0-9]+")) {
            factors[0] = new BigInteger(str);
        }
    }

    public static Factor add(Factor a, Factor b, Factor c) {
        for (int i = 0; i < 9; i++) {
            c.factors[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < 9; i++) {
            c.factors[i] = a.factors[i].add(b.factors[i]);
        }
        return c;
    }

    public static Factor sub(Factor a, Factor b, Factor c) {
        for (int i = 0; i < 9; i++) {
            c.factors[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < 9; i++) {
            c.factors[i] = a.factors[i].subtract(b.factors[i]);
        }
        return c;
    }

    public static Factor neg(Factor a, Factor c) {
        for (int i = 0; i < 9; i++) {
            c.factors[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < 9; i++) {
            c.factors[i] = a.factors[i].multiply(new BigInteger("-1"));
        }
        return c;
    }

    public static Factor pos(Factor a, Factor c) {
        for (int i = 0; i < 9; i++) {
            c.factors[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < 9; i++) {
            c.factors[i] = a.factors[i];
        }
        return c;
    }

    public static Factor mul(Factor a, Factor b, Factor c) {
        for (int i = 0; i < 9; i++) {
            c.factors[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i + j < 9) {
                    c.factors[i + j] = c.factors[i + j].add(a.factors[i].multiply(b.factors[j]));
                }
            }
        }
        return c;
    }

    public static Factor pow(Factor a, Factor b, Factor c, Factor d) {
        for (int i = 0; i < 9; i++) {
            c.factors[i] = BigInteger.ZERO;
            d.factors[i] = BigInteger.ZERO;
        }
        for (int t = 1; t < b.factors[0].intValue(); t++) {
            for (int i = 0; i < 9; i++) {
                c.factors[i] = BigInteger.ZERO;
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i + j < 9) {
                        if (t == 1) {
                            c.factors[i + j] = c.factors[i + j].add(a.factors[i].multiply(a.factors[j]));
                        } else {
                            c.factors[i + j] = c.factors[i + j].add(d.factors[i].multiply(a.factors[j]));
                        }
                    }
                }
            }
            for (int i = 0; i < 9; i++) {
                d.factors[i] = c.factors[i];
            }
        }
        if (b.factors[0].equals(BigInteger.ZERO)) {
            c.factors[0] = BigInteger.ONE;
            for (int i = 1; i < 9; i++) {
                c.factors[i] = BigInteger.ZERO;
            }
        }
        if (b.factors[0].equals(BigInteger.ONE)) {
            for (int i = 0; i < 9; i++) {
                c.factors[i] = a.factors[i];
            }
        }
        return c;
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            if (i == 0) {
                System.out.print(factors[i]);
            } else {
                if (factors[i] != BigInteger.ZERO) {
                    if (i != 1) {
                        System.out.print('+');
                        System.out.print(factors[i]);
                        System.out.print("*x**");
                        System.out.print(i);
                    } else {
                        System.out.print('+');
                        System.out.print(factors[i]);
                        System.out.print("*x");
                    }
                }
            }
        }
    }
}
