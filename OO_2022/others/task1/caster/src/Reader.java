import java.math.BigInteger;

public class Reader {
    private String input;
    private int pos;

    public Reader(String str) {
        input = str;
        pos = 0;
    }

    public boolean hasNextChar() {
        return pos < input.length();
    }

    public char peek() {
        return input.charAt(pos);
    }

    public char get() {
        return input.charAt(pos++);
    }

    public BigInteger getNum() {
        BigInteger res = BigInteger.valueOf(0);
        boolean pos = true;
        if (this.peek() == '+') {
            this.get();
        }
        else if (this.peek() == '-') {
            this.get();
            pos = false;
        }
        while (hasNextChar() && Character.isDigit(this.peek())) {
            res = res.multiply(BigInteger.valueOf(10));
            res = res.add(BigInteger.valueOf(this.get() - '0'));
        }
        if (!pos) {
            res = res.multiply(BigInteger.valueOf(-1));
        }
        return res;
    }
}
