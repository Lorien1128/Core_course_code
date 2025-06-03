//import java.math.BigInteger;

public class Parser {
    private Reader reader;

    public Parser(Reader reader) {
        this.reader = reader;
    }

    public Expression parseExpr() {
        Expression res = new Expression();
        Character sign = '+';
        if (reader.peek() == '+') {
            sign = reader.get();
        }
        else if (reader.peek() == '-') {
            sign = reader.get();
        }
        res.addTerm(parseTerm(),sign);
        while (reader.hasNextChar() && "+-".indexOf(reader.peek()) != -1) {
            sign = reader.get();
            res.addTerm(parseTerm(),sign);
        }
        return res;
    }

    public Term parseTerm() {
        Term res = new Term();
        res.addFactors(parseFac());
        while (reader.hasNextChar() && reader.peek() == '*') {
            reader.get();
            res.addFactors(parseFac());
        }
        return res;
    }

    public Factor parseFac() {
        if (reader.peek() == '(') {
            reader.get();
            Expression expr = parseExpr();
            reader.get();           // 读掉右括号
            int power = 1;
            if (reader.hasNextChar() && reader.peek() == '^') {
                reader.get();
                power = reader.getNum().intValue();
            }
            return new ExpFac(expr,power);
        }
        else if (reader.peek() == 'x') {
            reader.get();
            int power = 1;
            if (reader.hasNextChar() && reader.peek() == '^') {
                reader.get();
                power = reader.getNum().intValue();
            }
            return new VarFac(power);
        }
        else {
            return new ConFac(reader.getNum());
        }
    }
}
