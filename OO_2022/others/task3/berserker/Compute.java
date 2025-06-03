import java.math.BigInteger;

public class Compute {
    public void exprSimplify(ExprClass expr) {
        for (int i = expr.getTermClasses().size() - 1; i >= 0; i--) {
            if (expr.getTermClasses().get(i).isZero()) {
                expr.getTermClasses().remove(i);
            } else {
                expr.getTermClasses().get(i).termSimplify();
            }
        }
        for (int i = expr.getTermClasses().size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (new TermClass().similar(expr.getTermClasses().get(i),
                        expr.getTermClasses().get(j))) {
                    BigInteger and0 = BigInteger.ZERO.add(expr.getTermClasses()
                            .get(i).getCoefficient());
                    BigInteger and = and0.add(expr.getTermClasses().get(j).getCoefficient());
                    expr.getTermClasses().get(j).setCoefficient(and);
                    expr.getTermClasses().remove(i);
                    break;
                }
            }
        }
        for (int i = expr.getTermClasses().size() - 1; i >= 0; i--) {
            if (expr.getTermClasses().get(i).isZero()) {
                expr.getTermClasses().remove(i);
            }
        }
        for (int i = expr.getTermClasses().size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (expr.getTermClasses().get(i).getCoefficient().equals(expr.getTermClasses()
                        .get(j).getCoefficient()) && expr.getTermClasses().get(i).getIndex()
                                .equals(expr.getTermClasses().get(j).getIndex()) &&
                        expr.getTermClasses().get(i).getTriAngles().size() ==
                                expr.getTermClasses().get(j).getTriAngles().size()) {
                    int k = expr.getTermClasses().get(i).getTriAngles().size();
                    int numEqual = 0;
                    int numCouple = 0;
                    int n0 = 0;
                    for (int m = 0; m < k; m++) {
                        for (int n = 0; n < k; n++) {
                            if (new TriAngle().equal(expr.getTermClasses().get(i).getTriAngles()
                                    .get(m), expr.getTermClasses().get(j).getTriAngles().get(n))) {
                                numEqual++;
                            }
                            numCouple += new TriAngle().couple(expr.getTermClasses().get(i)
                                    .getTriAngles().get(m), expr.getTermClasses().get(j)
                                    .getTriAngles().get(n));
                            if (numCouple == 1) {
                                n0 = n;
                            }
                        }
                    }
                    if (numCouple == 1 && numEqual == k - 1) {
                        expr.getTermClasses().remove(i);
                        expr.getTermClasses().get(j).getTriAngles().remove(n0);
                        break;
                    }
                }
            }
        }
    }

    public ExprClass exprAdd(ExprClass expr1, ExprClass expr2) {
        ExprClass result = new ExprClass();
        result.getTermClasses().addAll(expr1.getTermClasses());
        result.getTermClasses().addAll(expr2.getTermClasses());
        new Compute().exprSimplify(result);
        return result;
    }

    public ExprClass exprSub(ExprClass expr1, ExprClass expr2) {
        ExprClass result = new ExprClass();
        ExprClass temp = expr2;
        for (TermClass term : temp.getTermClasses()) {
            term.setCoefficient(BigInteger.ZERO.subtract(term.getCoefficient()));
        }
        result.getTermClasses().addAll(expr1.getTermClasses());
        result.getTermClasses().addAll(temp.getTermClasses());
        new Compute().exprSimplify(result);
        return result;
    }

    public TermClass termMul(TermClass term1, TermClass term2) {
        TermClass result = new TermClass();
        BigInteger coefficient = BigInteger.ONE;
        coefficient = coefficient.multiply(term1.getCoefficient());
        coefficient = coefficient.multiply(term2.getCoefficient());
        result.setCoefficient(coefficient);
        BigInteger index = BigInteger.ZERO;
        index = index.add(term1.getIndex());
        index = index.add(term2.getIndex());
        result.setIndex(index);
        result.getTriAngles().addAll(term1.getTriAngles());
        result.getTriAngles().addAll(term2.getTriAngles());
        TermClass result1 = new TermClass();
        result1.setCoefficient(result.getCoefficient());
        result1.setIndex(result.getIndex());
        for (TriAngle triAngle : result.getTriAngles()) {
            result1.triAngleAdd(triAngle);
        }
        result1.termSimplify();
        return result1;
    }

    public ExprClass exprMul(ExprClass expr1, ExprClass expr2) {
        ExprClass result = new ExprClass();
        int m = expr1.getTermClasses().size();
        int n = expr2.getTermClasses().size();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.getTermClasses().add(new Compute()
                        .termMul(expr1.getTermClasses().get(i),
                                expr2.getTermClasses().get(j)));
            }
        }
        new Compute().exprSimplify(result);
        return result;
    }

    public ExprClass exprPow(ExprClass expr, BigInteger index) {
        TermClass termInitial = new TermClass();
        termInitial.setCoefficient(BigInteger.ONE);
        ExprClass result = new ExprClass();
        result.getTermClasses().add(termInitial);
        BigInteger remain = index;
        while (remain.compareTo(BigInteger.ZERO) > 0) {
            result = new Compute().exprMul(result, expr);
            remain = remain.subtract(BigInteger.ONE);
        }
        new Compute().exprSimplify(result);
        return result;
    }
}

