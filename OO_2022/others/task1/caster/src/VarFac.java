import java.math.BigInteger;

public class VarFac extends Factor {
    private int power;

    public VarFac(int power) {
        this.power = power;
    }

    @Override
    public Poly toPoly() {
        return new Poly(power,BigInteger.valueOf(1));
    }
}
