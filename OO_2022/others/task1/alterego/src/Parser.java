import module.Expr;
import module.Term;
import module.Factor;
import module.ExprAndTerm;
import module.ExprAndFactor;

import java.math.BigInteger;

public class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Expr parserExpr() {
        Expr expr = new Expr();

        if (lexer.peek().equals("+")) {
            lexer.next();
            expr.addTerm(parserTerm("+"));
        } else if (lexer.peek().equals("-")) {
            lexer.next();
            expr.addTerm(parserTerm("-"));
        } else {
            expr.addTerm(parserTerm("+"));
        }

        while (lexer.peek().equals("+") || lexer.peek().equals("-")) {
            String temp = lexer.peek();
            lexer.next();
            expr.addTerm(parserTerm(temp));
        }
        return expr;
    }

    public ExprAndTerm parserTerm(String exprSign) {
        Term term = new Term(new BigInteger("1"), 0);
        term.setExprPositive(exprSign.equals("+"));

        if (lexer.peek().equals("+")) {
            term.setTermPositive(true);
            lexer.next();
        } else if (lexer.peek().equals("-")) {
            term.setTermPositive(false);
            lexer.next();
        } else {
            term.setTermPositive(true);
        }
        term.addFactors(parserFactor());

        while (lexer.peek().equals("*")) {
            lexer.next();
            term.addFactors(parserFactor()); //拆掉表达式的幂
        }
        term.simplify1();
        return term.simplify2();
    }

    public ExprAndFactor parserFactor() {
        if (lexer.peek().equals("(")) {
            lexer.next();
            Expr expr = parserExpr();
            lexer.next(); // 去除右括号
            if (lexer.peek().equals("**")) {
                lexer.next();
                int power = Integer.parseInt(lexer.peek());
                lexer.next();
                expr.setPower(power);
            }
            return expr;
        } else if (lexer.peek().equals("x")) {
            lexer.next();
            Factor factor = new Factor();
            if (lexer.peek().equals("**")) {
                lexer.next();
                int power = Integer.parseInt(lexer.peek());
                lexer.next();
                if (power == 0) {
                    factor.setConstant(new BigInteger("1"));
                }
                factor.setPower(power);
            } else {
                factor.setPower(1);
            }
            return  factor;
        } else { //lexer.peek()为常数
            Factor factor = new Factor();
            factor.setPower(0);
            if (lexer.peek().equals("-")) {
                lexer.next();
                BigInteger constant = new BigInteger(lexer.peek());
                lexer.next();
                factor.setConstant(constant.negate());
            } else {
                if (lexer.peek().equals("+")) {
                    lexer.next();
                }
                BigInteger constant = new BigInteger(lexer.peek());
                lexer.next();
                factor.setConstant(constant);
            }
            return factor;
        }
    }
}
