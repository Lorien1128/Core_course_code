
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainClass {
    public static void main(String[] args) throws IOException {
//        ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
//        HashMap<Character, Function> functions = new HashMap<>();
//        int cnt = scanner.getCount();
//        for (int i = 1; i <= cnt; i++) {
//            String function = scanner.readLine().replaceAll("\\s*", "");
//            functions.put(function.charAt(0), new Function(function));
//        }
//        String expression = scanner.readLine().replaceAll("\\s*", "");
//        Expression exp = new Expression();
//        Standard standard = exp.divide(expression,functions);
//        System.out.println(standard.toString());

        String str = "(sin(x**2)+1)**2";

            HashMap<Character, Function> functions = new HashMap<>();
            String expression = str.replaceAll("\\s*", "");
            Expression exp = new Expression();
            Standard standard = exp.divide(expression,functions);
            System.out.println(standard.toString());

    }
}
