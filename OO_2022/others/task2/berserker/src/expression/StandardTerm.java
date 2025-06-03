package expression;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class StandardTerm implements Comparable<StandardTerm> {
    private BigInteger factor;
    private int exponent;
    private HashMap<StandardTerm, Integer> sineFunctions;
    private HashMap<StandardTerm, Integer> cosineFunctions;

    public StandardTerm(int i) {
        this.factor = BigInteger.valueOf(i);
        this.exponent = 0;
        this.sineFunctions = new HashMap<>();
        this.cosineFunctions = new HashMap<>();
    }

    public void setFactor(BigInteger factor) {
        this.factor = factor;
    }

    public BigInteger getFactor() {
        return this.factor;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public int getExponent() {
        return this.exponent;
    }

    public void multiply(SignedInteger signedInteger) {
        this.factor = this.factor.multiply(signedInteger.getNumber());
    }

    public void multiply(PowerFunction powerFunction) {
        this.exponent += powerFunction.getExponent();
    }

    public void multiply(SineFunction sineFunction) {
        if (sineFunction.getExponent() == 0) {
            return;
        }
        StandardTerm content = new StandardTerm(1);
        if (sineFunction.getContent() instanceof SignedInteger) {
            content.setFactor(((SignedInteger) sineFunction.getContent()).getNumber());
        }
        else if (sineFunction.getContent() instanceof PowerFunction) {
            content.setExponent(((PowerFunction) sineFunction.getContent()).getExponent());
        }
        for (Map.Entry<StandardTerm, Integer> entry: this.sineFunctions.entrySet()) {
            if (content.equalsTo(entry.getKey())) {
                this.sineFunctions.replace(entry.getKey()
                        , entry.getValue() + sineFunction.getExponent());
                return;
            }
        }
        this.sineFunctions.put(content, sineFunction.getExponent());
    }

    public void multiply(CosineFunction cosineFunction) {
        if (cosineFunction.getExponent() == 0) {
            return;
        }
        StandardTerm content = new StandardTerm(1);
        if (cosineFunction.getContent() instanceof SignedInteger) {
            content.setFactor(((SignedInteger) cosineFunction.getContent()).getNumber());
        }
        else if (cosineFunction.getContent() instanceof PowerFunction) {
            content.setExponent(((PowerFunction) cosineFunction.getContent()).getExponent());
        }
        for (Map.Entry<StandardTerm, Integer> entry: this.cosineFunctions.entrySet()) {
            if (content.equalsTo(entry.getKey())) {
                this.cosineFunctions.replace(entry.getKey()
                        , entry.getValue() + cosineFunction.getExponent());
                return;
            }
        }
        this.cosineFunctions.put(content, cosineFunction.getExponent());
    }

    public void multiply(StandardTerm other) {
        this.factor = this.factor.multiply(other.factor);
        this.exponent += other.exponent;
        for (Map.Entry<StandardTerm, Integer> entry: other.sineFunctions.entrySet()) {
            boolean found = false;
            for (Map.Entry<StandardTerm, Integer> entry1: this.sineFunctions.entrySet()) {
                if (entry1.getKey().equalsTo(entry.getKey())) {
                    this.sineFunctions.replace(entry1.getKey()
                            , entry1.getValue() + entry.getValue());
                    found = true;
                }
            }
            if (!found) {
                this.sineFunctions.put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<StandardTerm, Integer> entry: other.cosineFunctions.entrySet()) {
            boolean found = false;
            for (Map.Entry<StandardTerm, Integer> entry1: this.cosineFunctions.entrySet()) {
                if (entry1.getKey().equalsTo(entry.getKey())) {
                    this.cosineFunctions.replace(entry1.getKey()
                            , entry1.getValue() + entry.getValue());
                    found = true;
                }
            }
            if (!found) {
                this.cosineFunctions.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public StandardTerm multiplyWithReturn(StandardTerm other) {
        StandardTerm result = new StandardTerm(1);
        result.multiply(this);
        result.multiply(other);
        return result;
    }

    public void merge(StandardTerm other) {
        this.factor = this.factor.add(other.factor);
    }

    public void mergeTrigonometricFunctions(StandardTerm other) {
        if (!(this.factor.equals(other.factor) && this.exponent == other.exponent)) {
            return;
        }
        StandardTerm same = new StandardTerm(1);
        same.factor = this.factor;
        same.exponent = this.exponent;
    }

    public int compareTo(StandardTerm other) {
        return this.exponent - other.exponent;
    }

    public String toString(boolean isFirstTerm, boolean inTrigonometricFunction) {
        String output = "";
        String multiplicationSign = "";
        if (!isFirstTerm && this.factor.compareTo(BigInteger.ZERO) > 0) {
            output += "+";
        }
        if (!this.isInteger() && this.factor.equals(new BigInteger("-1"))) {
            output += "-";
        }
        else if (!this.factor.equals(BigInteger.ONE) || this.isInteger()) {
            output += this.factor.toString();
            multiplicationSign = "*";
        }
        if (this.exponent > 0) {
            if (this.exponent == 1) {
                output += multiplicationSign + "x";
                multiplicationSign = "*";
            }
            else if (this.exponent == 2 && !inTrigonometricFunction) {
                output += multiplicationSign + "x*x";
                multiplicationSign = "*";
            }
            else {
                output += multiplicationSign + "x**" + this.exponent;
                multiplicationSign = "*";
            }
        }
        for (Map.Entry<StandardTerm, Integer> entry: this.sineFunctions.entrySet()) {
            output += multiplicationSign + "sin(" + entry.getKey().toString(true, true) + ")";
            multiplicationSign = "*";
            if (entry.getValue() != 1) {
                output += "**" + entry.getValue().toString();
            }
        }
        for (Map.Entry<StandardTerm, Integer> entry: this.cosineFunctions.entrySet()) {
            output += multiplicationSign + "cos(" + entry.getKey().toString(true, true) + ")";
            multiplicationSign = "*";
            if (entry.getValue() != 1) {
                output += "**" + entry.getValue().toString();
            }
        }
        return output;
    }

    public boolean similarTo(StandardTerm other) {
        if (this.exponent != other.exponent) {
            return false;
        }
        if (this.sineFunctions.size() != other.sineFunctions.size()) {
            return false;
        }
        if (this.cosineFunctions.size() != other.cosineFunctions.size()) {
            return false;
        }
        for (Map.Entry<StandardTerm, Integer> entry: this.sineFunctions.entrySet()) {
            boolean flag = false;
            for (Map.Entry<StandardTerm, Integer> entry1: other.sineFunctions.entrySet()) {
                if (entry.getKey().equalsTo(entry1.getKey())
                        && entry.getValue().equals(entry1.getValue())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        for (Map.Entry<StandardTerm, Integer> entry: this.cosineFunctions.entrySet()) {
            boolean flag = false;
            for (Map.Entry<StandardTerm, Integer> entry1: other.cosineFunctions.entrySet()) {
                if (entry.getKey().equalsTo(entry1.getKey())
                        && entry.getValue().equals(entry1.getValue())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsTo(StandardTerm other) {
        return this.factor.equals(other.factor) && this.exponent == other.exponent;
    }

    public boolean isInteger() {
        return this.exponent == 0 && this.sineFunctions.size() == 0 &&
                this.cosineFunctions.size() == 0;
    }

}
