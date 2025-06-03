package expression;

import java.math.BigInteger;

public class SignedInteger implements Factor {
    private final BigInteger number;

    public SignedInteger(BigInteger number) {
        this.number = number;
    }

    public BigInteger getNumber() {
        return this.number;
    }

    public void print() {
        System.out.print(this.number);
    }

}
