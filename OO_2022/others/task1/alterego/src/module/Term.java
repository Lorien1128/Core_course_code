package module;

import java.math.BigInteger;
import java.util.ArrayList;

public class Term implements ExprAndTerm {
    private final ArrayList<ExprAndFactor> factors = new ArrayList<>();
    private boolean exprPositive; // 表达式中加减
    private boolean termPositive = true; //项总体的正负
    private BigInteger coefficient;  //系数
    private int termPower;  //指数

    public void setTermPositive(boolean termPositive) {
        this.termPositive = exprPositive == termPositive;
    }

    public void setExprPositive(boolean exprPositive) {
        this.exprPositive = exprPositive;
    }

    public BigInteger getCoefficient() {
        return coefficient;
    }

    public int getTermPower() {
        return termPower;
    }

    public Term(BigInteger coefficient, int termPower) {
        this.coefficient = coefficient;
        this.termPower = termPower;
    }

    public void addFactors(ExprAndFactor factor) {
        if (factor instanceof Expr) {
            int power = ((Expr) factor).getPower();
            while (power-- > 0) {
                factors.add(factor);
            }
        } else {
            factors.add(factor);
        }
    }

    public void simplify1() { // 整理所有不带括号的因子
        termPower = 0;
        coefficient = new BigInteger("1");
        for (ExprAndFactor factor : factors) {
            if (factor instanceof Factor) {
                int power = ((Factor) factor).getPower();
                if (power == 0) {  // 用power判断常数和幂函数
                    //coefficient *= ((Factor) factor).getConstant();
                    coefficient = coefficient.multiply(((Factor) factor).getConstant());
                } else {
                    termPower += power;
                }
            }
        }
        if (!termPositive) {
            coefficient = coefficient.negate();
        }
    }

    public ExprAndTerm simplify2() { // 去除表达式的括号
        ArrayList<Term> temp = new ArrayList<>();
        Term term = new Term(coefficient, termPower);
        temp.add(term);
        for (ExprAndFactor expr : factors) {
            if (expr instanceof Expr) {
                temp = termsMulti(temp, ((Expr) expr).getTerms());
            }
        }
        Expr expr = new Expr();
        expr.setTerms(temp);
        return expr;
    }

    private ArrayList<Term> termsMulti(ArrayList<Term> list1, ArrayList<Term> list2) {
        ArrayList<Term> result = new ArrayList<>();
        for (Term term1 : list1) {
            for (Term term2 : list2) {
                BigInteger coefficient1 = term1.coefficient;
                BigInteger coefficient2 = term2.coefficient;
                int termPower1 = term1.termPower;
                int termPower2 = term2.termPower;
                Term term = new Term(coefficient1.multiply(coefficient2), termPower1 + termPower2);
                result.add(term);
            }
        }
        return result;
    }
}
