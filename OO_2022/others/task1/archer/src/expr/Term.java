package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public class Term {
    private final ArrayList<Factor> factors;
    private boolean isneg = false;

    public Term() {
        this.factors = new ArrayList<>();
    }

    public void setIsneg(boolean isneg) {
        this.isneg = isneg;
    }

    public void addFactor(Factor factor) {
        this.factors.add(factor);
    }

    public ArrayList<Variable> use() {
        ArrayList<ArrayList<Variable>> variables = new ArrayList<>();
        int fsize = factors.size();
        for (int i = 0; i < fsize; i++) {
            Factor factor = factors.get(i);
            if (factor.getPow() > 1) {
                int p = factor.getPow();
                factor.setPow(1);
                for (int j = 0; j < p - 1; j++) {
                    factors.add(factor);
                }
            } else if (factor.getPow() == -1) {
                factors.remove(factor);
                Variable variable = new Variable(BigInteger.ONE, 0);
                factors.add(variable);
            }
        }

        for (Factor factor : factors) {
            variables.add(factor.use());
        }

        int size = variables.size();
        if (size == 1) {
            if (isneg) {
                for (int i = 0; i < variables.get(0).size(); i++) {
                    Variable variable = variables.get(0).get(i);
                    variable.setC(variable.getC().negate());
                }
                return variables.get(0);
            }
        }

        for (int i = 1; i < size; i++) {
            variables.set(i, mult(variables.get(i - 1), variables.get(i)));
        }

        if (isneg) {
            for (int i = 0; i < variables.get(size - 1).size(); i++) {
                Variable variable = variables.get(size - 1).get(i);
                variable.setC(variable.getC().negate());
            }
        }

        return variables.get(size - 1);
    }

    public ArrayList<Variable> mult(ArrayList<Variable> l, ArrayList<Variable> r) {
        ArrayList<Variable> variables = new ArrayList<>();
        for (Variable value : l) {
            for (Variable variable : r) {
                if (variable != null && value != null) {
                    Variable variable1 =
                            new Variable(value.getC().multiply(variable.getC()),
                                    value.getPow() + variable.getPow());
                    variables.add(variable1);
                }
            }
        }

        return variables;
    }
}
