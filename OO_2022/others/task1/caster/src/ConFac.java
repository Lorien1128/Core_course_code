import java.math.BigInteger;

public class ConFac extends Factor {
    private BigInteger value;

    public ConFac(BigInteger num) {
        value = num;
    }

    @Override
    public Poly toPoly() {
        return new Poly(0,value);
    }
}
