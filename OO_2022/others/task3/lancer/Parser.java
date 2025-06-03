import expr.Expr;
import expr.ExprFact;
import expr.Factor;
import expr.FuncFact;
import expr.Function;
import expr.IntFact;
import expr.SumFact;
import expr.Term;
import expr.TriFact;
import expr.VarFact;

import java.math.BigInteger;
import java.util.ArrayList;

public class Parser {
    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    private boolean isSign() {
        return (lexer.peek().equals("+") ||
                lexer.peek().equals("-"));
    }

    public Expr parseExpr() {
        Expr expr = new Expr();
        BigInteger sign = new BigInteger("1");
        if (isSign()) {
            if (lexer.peek().equals("-")) {
                sign = sign.negate();
            }
            lexer.next();
        }
        expr.addTerm(parseTerm(sign));
        while (lexer.hasNext() && isSign()) {
            if (lexer.peek().equals("+")) {
                sign = new BigInteger("1");
            } else {
                sign = new BigInteger("-1");
            }
            lexer.next();
            expr.addTerm(parseTerm(sign));
        }
        return expr;
    }

    public Term parseTerm(BigInteger outerSign) {
        BigInteger sign = outerSign;
        if (isSign()) {
            if (lexer.peek().equals("-")) {
                sign = sign.negate();
            }
            lexer.next();
        }
        Term term = new Term(sign);
        term.addFactor(parseFactor());
        while (lexer.hasNext() && lexer.peek().equals("*")) {
            lexer.next();
            term.addFactor(parseFactor());
        }
        return term;
    }

    private boolean isVariable() {
        return (lexer.peek().equals("x") ||
                lexer.peek().equals("y") ||
                lexer.peek().equals("z") ||
                lexer.peek().equals("i"));
    }

    private boolean isFunction() {
        return (lexer.peek().equals("f") ||
                lexer.peek().equals("g") ||
                lexer.peek().equals("h"));
    }

    private boolean isTriangle() {
        return (lexer.peek().equals("sin") ||
                lexer.peek().equals("cos"));
    }

    public Factor parseFactor() {
        if (lexer.peek().equals("(")) {
            return parseExprFact();
        } else if (isVariable()) {
            return parseVarFact();
        } else if (isFunction()) {
            return parseFuncFact();
        } else if (isTriangle()) {
            return parseTriFact();
        } else if (lexer.peek().equals("sum")) {
            return parseSumFact();
        } else {
            return parseIntFact();
        }
    }

    public BigInteger getSignedNum() {
        BigInteger sign = new BigInteger("1");
        if (isSign()) {
            if (lexer.peek().equals("-")) {
                sign = sign.multiply(new BigInteger("-1"));
            }
            lexer.next();
        }
        BigInteger ret = new BigInteger(lexer.peek());
        ret = ret.multiply(sign);
        lexer.next();
        return ret;
    }

    public BigInteger getExp() {
        BigInteger ret = new BigInteger("1");
        if (lexer.peek().equals("**")) {
            lexer.next();
            ret = getSignedNum();
        }
        return ret;
    }

    public Factor parseVarFact() {
        String name = lexer.peek();
        lexer.next();
        BigInteger num = getExp();
        if (num.compareTo(new BigInteger("0")) != 0) {
            return new VarFact(name,num);
        } else {
            return new IntFact(new BigInteger("1"));
        }
    }

    public Factor parseIntFact() {
        BigInteger num = getSignedNum();
        return new IntFact(num);
    }

    public Factor parseExprFact() {
        lexer.next(); //"("
        Expr expr = parseExpr();
        lexer.next(); //")"
        BigInteger num = getExp();
        if (num.compareTo(new BigInteger("0")) != 0) {
            return new ExprFact(expr, num);
        } else {
            return new IntFact(new BigInteger("1"));
        }
    }

    public Factor parseFuncFact() {
        final String name = lexer.peek();
        lexer.next();
        lexer.next(); //"("
        ArrayList<Expr> arguments = new ArrayList<>();
        Expr argument = parseExpr();
        arguments.add(argument);
        while (lexer.peek().equals(",")) {
            lexer.next();
            argument = parseExpr();
            arguments.add(argument);
        }
        lexer.next(); //")"
        return new FuncFact(name,arguments);
    }

    public Factor parseTriFact() {
        String name = lexer.peek();
        lexer.next();
        lexer.next(); //"("
        Expr triExpr = parseExpr();
        lexer.next(); //")"
        BigInteger num = getExp();
        if (num.compareTo(new BigInteger("0")) != 0) {
            return new TriFact(name,triExpr,num);
        } else {
            return new IntFact(new BigInteger("1"));
        }
    }

    public Factor parseSumFact() {
        lexer.next(); //"sum"
        lexer.next(); //"("
        final String var = lexer.peek();
        lexer.next();
        lexer.next(); //","
        final BigInteger start = getSignedNum();
        lexer.next(); //","
        BigInteger end = getSignedNum();
        lexer.next(); //","
        Expr sumExpr = parseExpr();
        lexer.next(); //")"
        return new SumFact(var,start,end,sumExpr);
    }

    public Function parseFunction() {
        final String name = lexer.peek();
        lexer.next();
        lexer.next(); //"("
        ArrayList<String> parameters = new ArrayList<>();
        String var = lexer.peek();
        parameters.add(var);
        lexer.next();
        while (lexer.peek().equals(",")) {
            lexer.next();
            var = lexer.peek();
            parameters.add(var);
            lexer.next();
        }
        lexer.next(); //")"
        lexer.next(); //"="
        Expr funcExpr = parseExpr();
        return new Function(name,parameters,funcExpr);
    }
}
