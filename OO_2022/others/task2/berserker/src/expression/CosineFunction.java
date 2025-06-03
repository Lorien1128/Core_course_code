package expression;

public class CosineFunction implements Factor {
    private Factor content;
    private int exponent;

    public CosineFunction(Factor content) {
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
            return "cos(" + this.content.toString() + ")";
        }
        return "cos(" + this.content.toString() + ")**" + this.exponent;
    }

}
