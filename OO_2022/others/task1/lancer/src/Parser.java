import expression.Expression;
import expression.Term;
import expression.Factor;
import expression.SignedInteger;
import expression.PowerFunction;

import java.math.BigInteger;

public class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.lexer.toNextThing('\0');
    }

    public Expression parseExpression() {
        Expression expression = new Expression();

        if (this.lexer.getNextType() == '+') {
            char firstTermSign = this.lexer.getNextThing().charAt(0);
            this.lexer.toNextThing('\0');
            expression.addTerm(this.parseTerm(firstTermSign));
        }
        else {
            expression.addTerm(this.parseTerm('+'));
        }
        while (this.lexer.getNextType() == '+') {
            char firstTermSign = this.lexer.getNextThing().charAt(0);
            this.lexer.toNextThing('\0');
            expression.addTerm(this.parseTerm(firstTermSign));
        }
        return expression;
    }

    public Term parseTerm(char sign) {
        Term term = new Term();
        if (this.lexer.getNextType() == '+') {
            term.setSign(sign, this.lexer.getNextThing().charAt(0));
            this.lexer.toNextThing('\0');
        }
        else {
            term.setSign(sign, '+');
        }
        term.addFactor(this.parseFactor());
        while (this.lexer.getNextType() == '*') {
            this.lexer.toNextThing('\0');
            term.addFactor(this.parseFactor());
        }
        return term;
    }

    public Factor parseFactor() {
        if (this.lexer.getNextType() == '(') {
            Expression expression = new Expression();
            this.lexer.toNextThing('\0');
            expression = this.parseExpression();
            this.lexer.toNextThing('+');
            if (this.lexer.getNextType() == 'e') {
                this.lexer.toNextThing('\0');
                expression.setExponent(Integer.parseInt(this.lexer.getNextThing()));
                this.lexer.toNextThing('+');
            }
            return expression;
        }
        if (this.lexer.getNextType() == 'n') {
            SignedInteger signedInteger = new SignedInteger(
                    new BigInteger(this.lexer.getNextThing()));
            this.lexer.toNextThing('+');
            return signedInteger;
        }
        if (this.lexer.getNextType() == 'x') {
            this.lexer.toNextThing('+');
            if (this.lexer.getNextType() == 'e') {
                this.lexer.toNextThing('\0');
                PowerFunction powerFunction = new PowerFunction(
                        Integer.parseInt(this.lexer.getNextThing()));
                this.lexer.toNextThing('+');
                return powerFunction;
            }
            else {
                PowerFunction powerFunction = new PowerFunction(1);
                return powerFunction;
            }
        }
        return new SignedInteger(new BigInteger("0"));
    }

}
