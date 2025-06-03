import expression.Expression;
import expression.Term;
import expression.SignedInteger;
import expression.PowerFunction;
import expression.Factor;
import expression.SineFunction;
import expression.CosineFunction;
import expression.Function;
import expression.Variable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final Lexer lexer;
    private HashMap<String, Function> functions;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.lexer.toNext('\0');
        this.functions = new HashMap<>();
    }

    public void setLexerInput(String input) {
        this.lexer.setInput(input);
        this.lexer.toNext('\0');
    }

    private void addFunction(Function function) {
        this.functions.put(function.getName(), function);
    }

    public void parseFunctionDefinition() {
        final String name = String.valueOf(this.lexer.getNext());
        this.lexer.toNext('\0');
        this.lexer.toNext('\0');
        ArrayList<Variable> variables = new ArrayList<>();
        while (this.lexer.getNextType() == 'x') {
            variables.add(new Variable(this.lexer.getNext()));
            this.lexer.toNext('\0');
            this.lexer.toNext('\0');
        }
        this.lexer.toNext('p');
        String content = this.lexer.getNext();
        this.addFunction(new Function(name, variables, content));
    }

    public Expression parseExpression() {
        Expression expression = new Expression();

        if (this.lexer.getNextType() == '+') {
            char firstTermSign = this.lexer.getNext().charAt(0);
            this.lexer.toNext('\0');
            expression.addTerm(this.parseTerm(firstTermSign));
        }
        else {
            expression.addTerm(this.parseTerm('+'));
        }
        while (this.lexer.getNextType() == '+') {
            char firstTermSign = this.lexer.getNext().charAt(0);
            this.lexer.toNext('\0');
            expression.addTerm(this.parseTerm(firstTermSign));
        }
        return expression;
    }

    public Term parseTerm(char sign) {
        Term term = new Term();
        if (this.lexer.getNextType() == '+') {
            term.setSign(sign, this.lexer.getNext().charAt(0));
            this.lexer.toNext('\0');
        }
        else {
            term.setSign(sign, '+');
        }
        term.addFactor(this.parseFactor());
        while (this.lexer.getNextType() == '*') {
            this.lexer.toNext('\0');
            term.addFactor(this.parseFactor());
        }
        return term;
    }

    public Factor parseFactor() {
        if (this.lexer.getNextType() == '(') {
            this.lexer.toNext('\0');
            Expression expression = this.parseExpression();
            this.lexer.toNext('+');
            if (this.lexer.getNextType() == 'e') {
                this.lexer.toNext('\0');
                expression.setExponent(Integer.parseInt(this.lexer.getNext()));
                this.lexer.toNext('+');
            }
            return expression;
        }
        if (this.lexer.getNextType() == 'n') {
            return this.parseSignedInteger();
        }
        if (this.lexer.getNextType() == 'x') {
            return this.parsePowerFunction();
        }
        if (this.lexer.getNextType() == 'f') {
            return this.parseFunction(this.lexer.getNext());
        }
        if (this.lexer.getNext().equals("sin")) {
            return this.parseSineFunction();
        }
        if (this.lexer.getNext().equals("cos")) {
            return this.parseCosineFunction();
        }
        if (this.lexer.getNext().equals("sum")) {
            return this.parseSumFunction();
        }
        return new SignedInteger(new BigInteger("0"));
    }

    public SignedInteger parseSignedInteger() {
        SignedInteger signedInteger = new SignedInteger(
                new BigInteger(this.lexer.getNext()));
        this.lexer.toNext('+');
        return signedInteger;
    }

    public PowerFunction parsePowerFunction() {
        this.lexer.toNext('+');
        if (this.lexer.getNextType() == 'e') {
            this.lexer.toNext('\0');
            PowerFunction powerFunction = new PowerFunction(
                    Integer.parseInt(this.lexer.getNext()));
            this.lexer.toNext('+');
            return powerFunction;
        }
        else {
            return new PowerFunction(1);
        }
    }

    public SineFunction parseSineFunction() {
        this.lexer.toNext('\0');
        this.lexer.toNext('+');
        SineFunction sineFunction = new SineFunction(this.parseFactor());
        this.lexer.toNext('+');
        if (this.lexer.getNextType() == 'e') {
            this.lexer.toNext('\0');
            sineFunction.setExponent(Integer.parseInt(this.lexer.getNext()));
            this.lexer.toNext('+');
        }
        else {
            sineFunction.setExponent(1);
        }
        return sineFunction;
    }

    public CosineFunction parseCosineFunction() {
        this.lexer.toNext('\0');
        this.lexer.toNext('+');
        CosineFunction cosineFunction = new CosineFunction(this.parseFactor());
        this.lexer.toNext('+');
        if (this.lexer.getNextType() == 'e') {
            this.lexer.toNext('\0');
            cosineFunction.setExponent(Integer.parseInt(this.lexer.getNext()));
            this.lexer.toNext('+');
        }
        else {
            cosineFunction.setExponent(1);
        }
        return cosineFunction;
    }

    public Expression parseFunction(String functionName) {
        Function function = this.functions.get(functionName);
        String expr = function.getContent();
        this.lexer.toNext('\0');
        for (int i = 0; i < function.getVariables().size(); i++) {
            this.lexer.toNext('\0');
            expr = expr.replaceAll("\\{" + function.getVariables().get(i).getName() + "}"
                    , "(" + this.parseFactor().toString() + ")");
        }
        this.lexer.toNext('+');
        Lexer lexer = new Lexer();
        lexer.setInput(expr);
        Parser parser = new Parser(lexer);
        return parser.parseExpression();
    }

    public Expression parseSumFunction() {
        this.lexer.toNext('\0');
        this.lexer.toNext('\0');
        this.lexer.toNext('\0');
        this.lexer.toNext('\0');
        final BigInteger start = new BigInteger(this.lexer.getNext());
        this.lexer.toNext('\0');
        this.lexer.toNext('\0');
        final BigInteger end = new BigInteger(this.lexer.getNext());
        this.lexer.toNext('\0');
        this.lexer.toNext('f');
        String factorString = this.lexer.getNext();
        this.lexer.toNext('\0');
        this.lexer.toNext('+');
        factorString = factorString.replaceAll("i", "{i}").replaceAll("s\\{i}n", "sin");
        Expression expression = new Expression();
        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);
        for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(BigInteger.ONE)) {
            String factorString1 = factorString.replaceAll("\\{i}", "(" + i + ")");
            parser.setLexerInput(factorString1);
            Term term = new Term();
            Factor factor = parser.parseFactor();
            term.addFactor(factor);
            expression.addTerm(term);
        }
        return expression;
    }

    public void printFunctions() {
        for (Map.Entry<String, Function> entry: this.functions.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }

}
