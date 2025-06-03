package term;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Objects;

public class Term {
    private BigInteger coefficient = BigInteger.ONE;
    private Power power = new Power(BigInteger.ZERO);
    private HashMap<SinPower, BigInteger> sinPowers = new HashMap<>();
    private HashMap<CosPower, BigInteger> cosPowers = new HashMap<>();

    public Term(String str) {
        if (str.equals("x")) {
            power = new Power(BigInteger.ONE);
        }
        else {
            coefficient = new  BigInteger(str);
        }
    }

    public Term(SinPower sinPower) {
        sinPowers.put(sinPower, BigInteger.ONE);
        if (sinPower.sign() < 0) {
            coefficient = coefficient.negate();
        }
    }

    public Term(CosPower cosPower) {
        cosPowers.put(cosPower, BigInteger.ONE);
    }

    public Term() {

    }

    public Term(Term other) {
        coefficient = other.coefficient.add(BigInteger.ZERO);
        power = new Power(other.power);
        for (SinPower sinPower : other.sinPowers.keySet()) {
            sinPowers.put(new SinPower(sinPower),
                    other.sinPowers.get(sinPower).add(BigInteger.ZERO));
        }
        for (CosPower cosPower : other.cosPowers.keySet()) {
            cosPowers.put(new CosPower(cosPower),
                    other.cosPowers.get(cosPower).add(BigInteger.ZERO));
        }
    }

    public static boolean checkAdd(Term a, Term b) {
        return a.power.equals(b.power) && a.sinPowers.equals(b.sinPowers)
                    && a.cosPowers.equals(b.cosPowers);
    }

    public static Term add(Term a, Term b) {
        Term c = new Term();
        c.coefficient = a.coefficient.add(b.coefficient);
        c.power = new Power(a.power);
        for (SinPower sinPower : a.sinPowers.keySet()) {
            c.sinPowers.put(new SinPower(sinPower), a.sinPowers.get(sinPower));
        }
        for (CosPower cosPower : a.cosPowers.keySet()) {
            c.cosPowers.put(new CosPower(cosPower), a.cosPowers.get(cosPower));
        }
        return c;
    }

    public static Term multiply(Term a, Term b) {
        Term c = new Term();
        BigInteger tmp;
        c.coefficient = a.coefficient.multiply(b.coefficient);
        c.power = Power.multiply(a.power, b.power);
        for (SinPower sinPower : a.sinPowers.keySet()) {
            c.sinPowers.put(new SinPower(sinPower), a.sinPowers.get(sinPower));
        }
        for (CosPower cosPower : a.cosPowers.keySet()) {
            c.cosPowers.put(new CosPower(cosPower), a.cosPowers.get(cosPower));
        }
        for (SinPower sinPower : b.sinPowers.keySet()) {
            if (c.sinPowers.containsKey(sinPower)) {
                tmp = c.sinPowers.get(sinPower);
                c.sinPowers.remove(sinPower);
                c.sinPowers.put(sinPower, tmp.add(b.sinPowers.get(sinPower)));
            } else {
                c.sinPowers.put(sinPower, b.sinPowers.get(sinPower));
            }
        }
        for (CosPower cosPower : b.cosPowers.keySet()) {
            if (c.cosPowers.containsKey(cosPower)) {
                tmp = c.cosPowers.get(cosPower);
                c.cosPowers.remove(cosPower);
                c.cosPowers.put(cosPower, tmp.add(b.cosPowers.get(cosPower)));
            } else {
                c.cosPowers.put(cosPower, b.cosPowers.get(cosPower));
            }
        }
        return c;
    }

    public static Term negate(Term other) {
        Term tmp = new Term(other);
        tmp.coefficient = tmp.coefficient.negate();
        return tmp;
    }

    public BigInteger toInt() {
        return coefficient;
    }

    public SinPower toSin() {
        if (power.isConstant()) {
            return new SinPower(coefficient, "constant");
        }
        return new SinPower(power.getExp(), "power");
    }

    public CosPower toCos() {
        if (power.isConstant()) {
            return new CosPower(coefficient, "constant");
        }
        return new CosPower(power.getExp(), "power");
    }

    public String display() {
        if (coefficient.equals(BigInteger.ZERO)) {
            return "";
        }
        String init = power.isConstant() ? coefficient.abs().toString() :
                coefficient.abs().equals(BigInteger.ONE) ? power.display()
                        : coefficient.abs() + "*" + power.display();
        if (power.isConstant() && coefficient.abs().equals(BigInteger.ONE)) {
            init = "";
        }
        for (SinPower sinPower : sinPowers.keySet()) {
            if (sinPower.sign() == 0) {
                return "";
            }
            init += "*" + sinPower.display() +
                    (sinPowers.get(sinPower).equals(BigInteger.ONE) ?
                            "" : "**" + sinPowers.get(sinPower));
        }
        for (CosPower cosPower : cosPowers.keySet()) {
            if (cosPower.isOne()) {
                continue;
            }
            init += "*" + cosPower.display() +
                    (cosPowers.get(cosPower).equals(BigInteger.ONE) ?
                            "" : "**" + cosPowers.get(cosPower));
        }
        if (init.equals("")) {
            return coefficient.equals(BigInteger.ONE) ? "1" : "-1";
        }
        if (init.charAt(0) == '*') {
            return (coefficient.compareTo(BigInteger.ZERO) > 0) ? init.substring(1) :
                    "-" + init.substring(1);
        }
        return (coefficient.compareTo(BigInteger.ZERO) > 0) ? init :
                "-" + init;
    }

    public int sign() {
        //System.out.println(display());
        return coefficient.compareTo(BigInteger.ZERO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Term term = (Term) o;
        return Objects.equals(coefficient, term.coefficient) && Objects.equals(power, term.power)
                && Objects.equals(sinPowers, term.sinPowers)
                && Objects.equals(cosPowers, term.cosPowers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, sinPowers, cosPowers);
    }

    @Override
    public String toString() {
        return display();
    }
}