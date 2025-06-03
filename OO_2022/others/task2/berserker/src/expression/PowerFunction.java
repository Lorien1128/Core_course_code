package expression;

public class PowerFunction implements Factor {
    private int exponent;

    public PowerFunction(int exponent) {
        this.exponent = exponent;
    }

    public int getExponent() {
        return this.exponent;
    }

    public String toString() {
        if (this.exponent == 0) {
            return "1";
        }
        if (this.exponent == 1) {
            return "x";
        }
        return "x**" + this.exponent;
    }

}
