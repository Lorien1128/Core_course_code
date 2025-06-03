
import expr.Expr;
import com.oocourse.spec1.ExprInput;
import com.oocourse.spec1.ExprInputMode;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
        String expr = scanner.readLine();

        Lexer lexer = new Lexer(expr.replaceAll("[ \t]", ""));
        Parser parser = new Parser(lexer);

        Expr exp = parser.parseExpr();
        System.out.println(exp.toString());
        /*String filePath = "C:/Users/Dell/Desktop/sj.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String str = "";
        while((str = buffReader.readLine())!=null){
            Lexer lexer = new Lexer(str.replaceAll("[ \t]", ""));
            Parser parser = new Parser(lexer);

            Expr exp = parser.parseExpr();
            System.out.println(exp.toString());
        }*/
    }
}
