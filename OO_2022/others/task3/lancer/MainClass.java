import expr.Expr;
import com.oocourse.spec3.ExprInput;
import com.oocourse.spec3.ExprInputMode;
import expr.Function;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        ArrayList<Function> functionLib = new ArrayList<>();

        String toExpr = new Scanner(System.in).next();
        Lexer lexer = new Lexer(toExpr.replaceAll("[ \\t]",""));
        Parser parser = new Parser(lexer);
        Expr expr = parser.parseExpr();
        expr.expand(functionLib);
        System.out.println(expr.optimizedOutput());
    }
}
