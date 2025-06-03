package expression;

public class SineFunction implements Factor {
    private Factor content;
    private int exponent;

    public SineFunction(Factor content) {
        if (content instanceof Expression) {
            this.content = ((Expression) content).getTerm(0).getFactor(0);
        }
        else {
            this.content = content;
        }
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public int getExponent() {
        return this.exponent;
    }

    public Factor getContent() {
        return this.content;
    }

    public String toString() {
        if (this.exponent == 1) {
            return "sin(" + this.content.toString() + ")";
        }
        return "sin(" + this.content.toString() + ")**" + this.exponent;
    }

}
