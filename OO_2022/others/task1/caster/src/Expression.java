import java.math.BigInteger;
import java.util.ArrayList;
//import java.util.HashSet;

public class Expression {
    private ArrayList<Term> terms;
    private ArrayList<Character> ops;

    public Expression() {
        terms = new ArrayList<>();
        ops = new ArrayList<>();
    }

    public void addTerm(Term term, Character op) {
        terms.add(term);
        ops.add(op);
    }

    public Poly toPoly() {
        Poly res = new Poly(0, BigInteger.valueOf(0));      // 常数0
        for (int i = 0;i < terms.size();++i) {
            Character op = ops.get(i);
            Term term = terms.get(i);
            if (op.equals('+')) {
                res = res.add(term.toPoly());
            }
            else {
                res = res.sub(term.toPoly());
            }
        }
        return res;
    }
}
