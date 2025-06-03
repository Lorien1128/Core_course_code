import expr.Expr;
import expr.Factor;
import expr.Term;
import expr.Variable;

import java.math.BigInteger;

public class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Expr parseExpr() {
        Expr expr = new Expr();
        int k = 0;
        if (lexer.peek().equals("-")) {
            k = 1;
            lexer.next();
        } else if (lexer.peek().equals("+")) {
            lexer.next();
        }
        Term t = parseTerm();
        if (k == 1) {
            t.setIsneg(true);
        }
        expr.addTerm(t);

        while (lexer.peek().equals("+") || lexer.peek().equals("-")) {
            int i = 0;
            if (lexer.peek().equals("-")) {
                i = 1;
            }
            lexer.next();
            Term term = parseTerm();
            if (i == 1) {
                term.setIsneg(true);
            }
            expr.addTerm(term);
        }

        return expr;
    }

    public Term parseTerm() {
        Term term = new Term();
        term.addFactor(parseFactor());
        while (lexer.peek().equals("*")) {
            lexer.next();
            term.addFactor(parseFactor());
        }
        return term;
    }

    public Factor parseFactor() {
        if (lexer.peek().equals("(")) {
            return getExpr();
        } else if (lexer.peek().equals("x")) {
            return getX();
        } else if (lexer.peek().equals("+")) {
            lexer.next();
            return getVariable();
        } else if (lexer.peek().equals("-")) {
            lexer.next();
            Variable variable = getVariable();
            variable.setC(variable.getC().negate());
            return variable;
        } else {
            return getVariable();
        }
    }

    public Expr getExpr() {
        lexer.next();
        Expr expr = parseExpr();
        lexer.next();
        if (lexer.peek().equals("**")) {
            lexer.next();
            if (lexer.peek().equals("+")) {
                lexer.next();
            }
            String num = lexer.peek();
            int m = Integer.parseInt(num, 10);
            if (m == 0) {
                m = -1;
            }
            lexer.next();
            expr.setPow(m);
        }
        return expr;
    }

    public Variable getX() {
        lexer.next();
        if (lexer.peek().equals("**")) {
            lexer.next();
            if (lexer.peek().equals("+")) {
                lexer.next();
            }
            String num = lexer.peek();
            int m = Integer.parseInt(num, 10);
            if (m == 0) {
                m = -1;
            }
            lexer.next();
            return new Variable(BigInteger.ONE, m);
        } else {
            return new Variable(BigInteger.ONE, 1);
        }
    }

    public Variable getVariable() {
        BigInteger num = new BigInteger(lexer.peek());
        lexer.next();
        if (lexer.peek().equals("**")) {
            lexer.next();
            if (lexer.peek().equals("+")) {
                lexer.next();
            }
            String n = lexer.peek();
            lexer.next();
            return new Variable(num.pow(Integer.parseInt(n, 10)), 0);
        } else {
            return new Variable(num, 0);
        }
    }
}
