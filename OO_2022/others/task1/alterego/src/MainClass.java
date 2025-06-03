import module.Expr;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {

    public static void main(String[] argv) throws IOException {

        /*ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
        String input = scanner.readLine();

        input = handleSign(input);
        // 合并多余的符号
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Expr expr = parser.parserExpr();
        expr.mergeTerms(); // 合并同类项 生成字符串
        System.out.println(expr);*/
        String filePath = "C:/Users/Dell/Desktop/sj.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String input = "";
        while((input = buffReader.readLine())!=null){
            input = handleSign(input);
            // 合并多余的符号
            Lexer lexer = new Lexer(input);
            Parser parser = new Parser(lexer);

            Expr expr = parser.parserExpr();
            expr.mergeTerms(); // 合并同类项 生成字符串
            System.out.println(expr);
        }
    }

    public static String handleSign(String input) {
        String result = input.replaceAll(" ", "");
        result = result.replaceAll("\\t", "");
        result = result.replace("**+", "**");
        return result;
    }
}
