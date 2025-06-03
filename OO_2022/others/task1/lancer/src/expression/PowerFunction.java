package expression;

public class PowerFunction implements Factor {
    private int exponent;

    public PowerFunction(int exponent) {
        this.exponent = exponent;
    }

    public int getExponent() {
        return this.exponent;
    }

    public void print() {
        System.out.print("x**" + this.exponent);
    }

}
