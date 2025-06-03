import java.math.BigInteger;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
//import java.util.Locale;

public class Poly {
    private HashMap<Integer, BigInteger> polys;

    public Poly(Integer power,BigInteger cof) {
        polys = new HashMap<>();
        polys.put(power,cof);
    }

    public Poly add(Poly other) {
        for (Integer power : other.polys.keySet()) {
            BigInteger addValue = other.polys.get(power);
            if (this.polys.containsKey(power)) {
                BigInteger oldValue = this.polys.get(power);
                this.polys.replace(power,oldValue.add(addValue));
            }
            else {
                this.polys.put(power,addValue);
            }
        }
        return this;
    }

    public Poly sub(Poly other) {
        for (Integer power : other.polys.keySet()) {
            BigInteger subValue = other.polys.get(power);
            if (this.polys.containsKey(power)) {
                BigInteger oldValue = this.polys.get(power);
                this.polys.replace(power,oldValue.subtract(subValue));
            }
            else {
                this.polys.put(power,subValue.negate());
            }
        }
        return this;
    }

    public Poly mult(Poly other) {
        Poly res = new Poly(0,BigInteger.valueOf(0));       // 常数0
        for (Integer p1 : this.polys.keySet()) {
            for (Integer p2 : other.polys.keySet()) {
                BigInteger c1 = this.polys.get(p1);
                BigInteger c2 = other.polys.get(p2);
                BigInteger newCof = c1.multiply(c2);
                Integer newPower = p1 + p2;
                res = res.add(new Poly(newPower, newCof));
            }
        }
        return res;
    }

    private LinkedList<Integer> getList() {
        LinkedList<Integer> polyArr = new LinkedList<>();
        boolean get = false;
        for (Integer power : polys.keySet()) {
            if (!get && polys.get(power).compareTo(BigInteger.valueOf(0)) > 0) {
                polyArr.addFirst(power);    // 将正项提到前面
            }
            else {
                polyArr.addLast(power);
            }
        }
        return polyArr;
    }

    @Override
    public String toString() {
        if (polys.size() == 0) {
            return "";
        }
        String res = "";
        String sign;
        String strCof;
        String strPow;
        String strMult;
        BigInteger zero = BigInteger.ZERO;
        BigInteger one = BigInteger.ONE;
        boolean start = true;

        LinkedList<Integer> polyArr = getList();

        for (Integer power : polyArr) {
            BigInteger cof = polys.get(power);
            if (cof.equals(zero)) {        // 0时不用再加,且不更新start
                continue;
            }
            else if (cof.compareTo(zero) < 0) {
                sign = "-";
            }
            else {
                sign = "+";
            }
            if (start && sign.equals("+")) {
                sign = "";
            }

            if ((cof.equals(one) || cof.equals(one.negate())) && power != 0) {
                strCof = "";
            }
            else {
                strCof = cof.abs().toString();
            }

            if (power == 0) {
                strPow = "";
            }
            else if (power == 1) {
                strPow = "x";
            }
            else if (power == 2) {
                strPow = "x*x";
            }
            else {
                strPow = "x**" + power;
            }

            if (strPow.equals("") || strCof.equals("")) {
                strMult = "";
            }
            else {
                strMult = "*";
            }
            start = false;

            res = res.concat(sign + strCof + strMult + strPow);

        }
        if (res.equals("")) {
            res = "0";              // 整个式子为0
        }
        return res;
    }
}
