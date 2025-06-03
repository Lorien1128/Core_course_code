import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrigoFunction implements Comparable<TrigoFunction>, Cloneable {
    private BigInteger coefficient;
    private int insidePower;
    private int outsidePower;

    @Override
    public TrigoFunction clone() {
        try {
            return (TrigoFunction) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public TrigoFunction(String rawStr) {
        if (rawStr == null) {
            this.insidePower = 0;
            this.outsidePower = 0;
            this.coefficient = null;
            return;
        }
        this.coefficient = BigInteger.ONE;
        this.outsidePower = 1;
        Matcher m1 = Pattern.compile("x").matcher(rawStr);
        int count = 0;
        while (m1.find()) {
            count++;
        }
        if (count == 0) {
            Matcher m2 = Pattern.compile("[\\+\\-]?\\d+").matcher(rawStr);
            while (m2.find()) {
                this.coefficient = this.coefficient.multiply(new BigInteger(m2.group()));
            }
            this.insidePower = 0;
        }
        else {
            this.insidePower = count;
        }
    }

    public int compareTo(TrigoFunction other) {
        if (this.insidePower < other.insidePower) {
            return -1;
        }
        else if (this.insidePower > other.insidePower) {
            return 1;
        }
        else {
            return this.coefficient.subtract(other.coefficient).signum();
        }
    }

    public int getOutsidePower() {
        return outsidePower;
    }

    public void setOutsidePower(int outsidePower) {
        this.outsidePower = outsidePower;
    }

    public BigInteger getCoefficient() {
        return coefficient;
    }

    public int getInsidePower() {
        return insidePower;
    }

    public String toStr(String type) {
        String str = "";
        if (type.equals("sin")) {
            str = str + "sin(";
        } else {
            str = str + "cos(";
        }
        if (this.insidePower == 0) {
            str = str + this.coefficient + ")";
        }
        else if (this.insidePower == 1) {
            str = str + "x" + ")";
        }
        else {
            str = str + "x**" + this.insidePower + ")";
        }
        if (this.outsidePower > 1) {
            str = str + "**" + this.outsidePower;
        }
        return str;
    }

    public void setCoefficient(BigInteger coefficient) {
        this.coefficient = coefficient;
    }

    public void setInsidePower(int insidePower) {
        this.insidePower = insidePower;
    }
}
