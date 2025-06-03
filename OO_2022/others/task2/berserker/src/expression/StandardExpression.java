package expression;

import java.math.BigInteger;
import java.util.ArrayList;

public class StandardExpression {
    private final ArrayList<StandardTerm> standardTerms;

    public StandardExpression(int i) {
        this.standardTerms = new ArrayList<>();
        StandardTerm standardTerm = new StandardTerm(i);
        this.standardTerms.add(standardTerm);
    }

    public StandardExpression() {
        this.standardTerms = new ArrayList<>();
    }

    public void addTerm(StandardTerm standardTerm) {
        this.standardTerms.add(standardTerm);
    }

    public ArrayList<StandardTerm> getTerms() {
        return this.standardTerms;
    }

    public void multiply(SignedInteger signedInteger) {
        for (StandardTerm standardTerm: this.standardTerms) {
            standardTerm.multiply(signedInteger);
        }
    }

    public void multiply(PowerFunction powerFunction) {
        for (StandardTerm standardTerm: this.standardTerms) {
            standardTerm.multiply(powerFunction);
        }
    }

    public void multiply(SineFunction sineFunction) {
        for (StandardTerm standardTerm: this.standardTerms) {
            standardTerm.multiply(sineFunction);
        }
    }

    public void multiply(CosineFunction cosineFunction) {
        for (StandardTerm standardTerm: this.standardTerms) {
            standardTerm.multiply(cosineFunction);
        }
    }

    public void multiply(StandardExpression other) {
        ArrayList<StandardTerm> temp = new ArrayList<>();
        temp.addAll(this.standardTerms);
        this.standardTerms.clear();
        for (StandardTerm standardTerm1: temp) {
            for (StandardTerm standardTerm2: other.standardTerms) {
                this.standardTerms.add(standardTerm1.multiplyWithReturn(standardTerm2));
            }
        }
        this.merge();
    }

    public void multiply(Expression expression) {
        int times = expression.getExponent();
        StandardExpression standardExpression = expression.expandAndMerge();
        for (int i = 0; i < times; i++) {
            this.multiply(standardExpression);
        }
    }

    public void add(StandardExpression other) {
        for (StandardTerm standardTerm: other.standardTerms) {
            this.addTerm(standardTerm);
        }
    }

    public void merge() {
        for (int i = this.standardTerms.size() - 1; i >= 0; i--) {
            if (this.standardTerms.get(i).getFactor().equals(BigInteger.ZERO)) {
                this.standardTerms.remove(i);
            }
        }
        boolean done = false;
        while (!done) {
            done = true;
            for (int j = 0; j < this.standardTerms.size() - 1; j++) {
                StandardTerm first = this.standardTerms.get(j);
                for (int i = j + 1; i < this.standardTerms.size(); i++) {
                    if (first.similarTo(this.standardTerms.get(i))) {
                        first.merge(this.standardTerms.remove(i));
                        if (first.getFactor().equals(BigInteger.ZERO)) {
                            this.standardTerms.remove(j);
                        }
                        done = false;
                        break;
                    }
                }
            }
        }
    }

    public void mergeTrigonometricFunctions() {
        boolean done = false;
        while (!done) {
            done = true;
            for (int j = 0; j < this.standardTerms.size() - 1; j++) {
                StandardTerm first = this.standardTerms.get(j);
                for (int i = j + 1; i < this.standardTerms.size(); i++) {
                    StandardTerm other = this.standardTerms.get(i);
                    first.mergeTrigonometricFunctions(other);
                }
            }
        }
    }

    public void putPositiveTermAhead() {
        int flag = -1;
        for (int i = 0; i < this.standardTerms.size(); i++) {
            if (this.standardTerms.get(i).getFactor().compareTo(BigInteger.ZERO) > 0) {
                flag = i;
                break;
            }
        }
        if (flag != -1) {
            StandardTerm temp = this.standardTerms.get(0);
            this.standardTerms.set(0, this.standardTerms.get(flag));
            this.standardTerms.set(flag, temp);
        }
    }

    public String toString() {
        if (this.standardTerms.size() == 0) {
            return "0";
        }
        String output = "";
        output += this.standardTerms.get(0).toString(true, false);
        for (int i = 1; i < this.standardTerms.size(); i++) {
            output += this.standardTerms.get(i).toString(false, false);
        }
        return output;
    }

}
