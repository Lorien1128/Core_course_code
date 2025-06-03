package expression;

import java.math.BigInteger;
import java.util.ArrayList;

public class Term {
    private char sign;
    private ArrayList<Factor> factors;

    public Term() {
        this.sign = '+';
        this.factors = new ArrayList<>();
    }

    public void setSign(char sign1, char sign2) {
        this.sign = (sign1 == sign2) ? '+' : '-';
    }

    public void addFactor(Factor factor) {
        this.factors.add(factor);
    }

    public ArrayList<Factor> getFactors() {
        return this.factors;
    }

    public Factor getFactor(int i) {
        return this.factors.get(i);
    }

    public int size() {
        return this.factors.size();
    }

    public String toString() {
        String output = String.valueOf(this.sign);
        output += factors.get(0).toString();
        for (int i = 1; i < factors.size(); i++) {
            output += "*" + factors.get(i).toString();
        }
        return output;
    }

    public void preTreat() {
        if (this.sign == '-') {
            this.sign = '+';
            this.addFactor(new SignedInteger(new BigInteger("-1")));
        }
        int n = this.factors.size();
        for (int i = 0; i < n; i++) {
            if (factors.get(i) instanceof Expression) {
                ((Expression) factors.get(i)).preTreat();
            }
        }
    }

    public void removeBrackets() {
        Factor factor1 = new SignedInteger(new BigInteger("1"));
        Term term = new Term();
        Expression result = new Expression();
        term.addFactor(factor1);
        result.addTerm(term);
        while (this.factors.size() > 0) {
            Factor factor = this.factors.remove(0);
            if (factor instanceof SignedInteger) {
                result = result.multiply((SignedInteger) factor);
            }
            else if (factor instanceof PowerFunction) {
                result = result.multiply((PowerFunction) factor);
            }
            else if (factor instanceof SineFunction) {
                result = result.multiply((SineFunction) factor);
            }
            else if (factor instanceof CosineFunction) {
                result = result.multiply((CosineFunction) factor);
            }
            else {
                Expression expression = (Expression) factor;
                expression.removeBrackets();
                while (expression.getExponent() > 0) {
                    result = result.multiply(expression);
                    expression.setExponent(expression.getExponent() - 1);
                }
            }
        }
        this.addFactor(result);
    }

}
