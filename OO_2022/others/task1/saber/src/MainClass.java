import com.oocourse.spec1.ExprInput;
import com.oocourse.spec1.ExprInputMode;
import exp.Tree;
import parse.Lexer;
import parse.Parser;

import java.io.*;

public class MainClass {
    public static void main(String[] argv) throws IOException {
        // 实例化一个ExprInput类型的对象scanner
        // 由于是一般读入模式，所以我们实例化时传递的参数为ExprInputMode.NormalMode
       /* ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);

        // 一般读入模式下，读入一行字符串时使用readLine()方法，在这里我们使用其读入表达式
        String input = scanner.readLine();


        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Tree t = parser.parseExpr();
        t = t.getResult();
        System.out.println(t.toString());*/
        String filePath = "C:/Users/Dell/Desktop/sj.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String str = "";
        while((str = buffReader.readLine())!=null){
            Lexer lexer = new Lexer(str);
            Parser parser = new Parser(lexer);
            Tree t = parser.parseExpr();
            t = t.getResult();
            System.out.println(t.toString());
        }
    }
}
