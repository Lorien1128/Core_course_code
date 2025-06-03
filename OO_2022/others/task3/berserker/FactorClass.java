import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactorClass {
    private final Pattern numberPattern = Pattern
            .compile("^[+-]?\\d+$");
    private final Pattern powPattern = Pattern
            .compile("^x\\s*\\*{2}\\s*\\+?(\\d+)$");
    private final Pattern exprPattern1 = Pattern
            .compile("^(\\()(.+?)(\\))\\s*\\*{2}\\s*\\+?(\\d+)$");
    private final Pattern exprPattern2 = Pattern
            .compile("^(\\()(.+?)(\\))$");

    public ExprClass factor(String str, ArrayList<Function> functions) {
        ExprClass result = new ExprClass();
        TermClass term = new TermClass();
        Matcher numberMatcher = numberPattern.matcher(str);
        Matcher powMatcher = powPattern.matcher(str);
        Matcher exprMatcher1 = exprPattern1.matcher(str);
        Matcher exprMatcher2 = exprPattern2.matcher(str);
        if (str.equals("x")) {                  // x
            term.setCoefficient(BigInteger.ONE);
            term.setIndex(BigInteger.ONE);
        } else if (numberMatcher.find()) {      // (+/-)123
            term.setIndex(BigInteger.ZERO);
            term.setCoefficient(new BigInteger(numberMatcher.group(0)));
        } else if (powMatcher.find()) {         // x ** (+)2
            term.setCoefficient(BigInteger.ONE);
            term.setIndex(new BigInteger(powMatcher.group(1)));
        } else if (exprMatcher1.find()) {       // (...) ** (+)2
            return new Compute().exprPow(new ExprClass()
                            .expr(exprMatcher1.group(2).trim(), functions),
                    new BigInteger(exprMatcher1.group(4)));
        } else if (exprMatcher2.find()) {       // (...)
            return new ExprClass().expr(exprMatcher2.group(2).trim(), functions);
        } else if (str.startsWith("sin") ||
                str.startsWith("cos")) {        // sin/cos (...)( ** +2)
            return new TriAngle().triAngle(str, functions);
        } else if (str.startsWith("sum")) {     // sum(...)
            return new Sum().sum(str, functions);
        } else if (str.startsWith("f") ||
                str.startsWith("g") ||
                str.startsWith("h")) {          // f/g/h(...)
            return new Function().func(str, functions);
        } else {
            throw new RuntimeException("Wrong Format!");
        }
        result.getTermClasses().add(term);
        return result;
    }
}


