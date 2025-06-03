

import com.oocourse.spec2.ExprInput;
import com.oocourse.spec2.ExprInputMode;
import expression.Expression;
import expression.StandardExpression;

public class MainClass {
    public static void main(String[] args) {

        int count = 0;
        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);


        parser.setLexerInput("sin(+5)");
        Expression expression = parser.parseExpression();

        expression.preTreat();

        StandardExpression standardExpression = expression.expandAndMerge();

        standardExpression.putPositiveTermAhead();
        System.out.println(standardExpression.toString());

    }
}
