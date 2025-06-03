package expr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Expr implements Factor {
    private final ArrayList<Term> terms;
    private int pow;

    public Expr() {
        this.terms = new ArrayList<>();
        this.pow = 1;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }

    public void addTerm(Term term) {
        this.terms.add(term);
    }

    @Override
    public ArrayList<Variable> use() {
        ArrayList<Variable> v = new ArrayList<>();
        for (Term term : terms) {
            v.addAll(term.use());
        }
        return v;
    }

    @Override
    public int getPow() {
        return this.pow;
    }

    public String toString() {
        HashMap<Integer, Variable> variables = new HashMap<>();
        for (Term term : terms) {
            ArrayList<Variable> v = term.use();
            for (Variable variable : v) {
                if (variable != null && variables.containsKey(variable.getPow())) {
                    Variable tmp1 = variables.get(variable.getPow());
                    Variable tmp2 =
                            new Variable(tmp1.getC().add(variable.getC()), variable.getPow());
                    variables.put(variable.getPow(), tmp2);
                } else if (variable != null) {
                    variables.put(variable.getPow(), variable);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Variable> entry : variables.entrySet()) {
            if (entry.getValue().getC().compareTo(BigInteger.ZERO) >= 0) {
                sb.append("+");
            }
            if (entry.getValue().getC().equals(BigInteger.ZERO)) {
                sb.append(0);
            } else if (entry.getKey() == 0) {
                sb.append(entry.getValue().getC());
            } else if (entry.getKey() == 1) {
                sb.append(entry.getValue().getC());
                sb.append("*x");
            } else {
                sb.append(entry.getValue().getC());
                sb.append("*x**");
                sb.append(entry.getValue().getPow());
            }
        }
        return sb.toString();
    }
}
