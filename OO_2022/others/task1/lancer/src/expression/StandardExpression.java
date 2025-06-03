package expression;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class StandardExpression {
    private final ArrayList<StandardTerm> standardTerms;

    public StandardExpression(Expression expression) {
        this.standardTerms = new ArrayList<>();
        for (Term term: expression.getTerms()) {
            Expression expression1 = ((Expression) term.getFactors().get(0));
            for (Term term1: expression1.getTerms()) {
                this.standardTerms.add(new StandardTerm(term1));
            }
        }
    }

    public void merge() {
        Collections.sort(this.standardTerms);
        for (int i = 0; i < this.standardTerms.size(); i++) {
            int j;
            for (j = i; j < this.standardTerms.size(); j++) {
                if (this.standardTerms.get(j).getExponent() !=
                        this.standardTerms.get(i).getExponent()) {
                    break;
                }
            }
            if (j > i) {
                for (int k = i + 1; k < j; k++) {
                    this.standardTerms.get(i).merge(this.standardTerms.get(k));
                }
            }
        }
        for (int i = this.standardTerms.size() - 1; i >= 0; i--) {
            if (this.standardTerms.get(i).getFactor().equals(new BigInteger("0"))) {
                this.standardTerms.remove(i);
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

    public void print() {
        if (this.standardTerms.size() == 0) {
            System.out.print("0");
        }
        else {
            this.standardTerms.get(0).print(true);
            for (int i = 1; i < this.standardTerms.size(); i++) {
                this.standardTerms.get(i).print(false);
            }
        }
        System.out.print("\n");
    }

}
