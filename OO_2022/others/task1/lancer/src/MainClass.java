
import expression.Expression;
import expression.StandardExpression;

import java.io.*;

public class MainClass {
    public static void main(String[] args) throws IOException {
        /*ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
        Lexer lexer = new Lexer(scanner.readLine());
        Parser parser = new Parser(lexer);
        Expression expression = parser.parseExpression();

        expression.preTreat();

        expression.removeBrackets();

        StandardExpression standardExpression = new StandardExpression(expression);

        standardExpression.merge();
        standardExpression.putPositiveTermAhead();
        standardExpression.print();*/
        String filePath = "C:/Users/Dell/Desktop/sj.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String str = "";
        while((str = buffReader.readLine())!=null){
            Lexer lexer = new Lexer(str);
            Parser parser = new Parser(lexer);
            Expression expression = parser.parseExpression();

            expression.preTreat();

            expression.removeBrackets();

            StandardExpression standardExpression = new StandardExpression(expression);

            standardExpression.merge();
            standardExpression.putPositiveTermAhead();
            standardExpression.print();
        }

    }
}
