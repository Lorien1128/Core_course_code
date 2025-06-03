//import com.oocourse.spec2.ExprInput;
//import com.oocourse.spec2.ExprInputMode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {
    public static void main(String[] args) throws IOException {
//        ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
//        int cnt = scanner.getCount();
//        Parser parser = new Parser();
//        for (int i = 0; i < cnt; i++) {
//            String func = scanner.readLine().replaceAll("[ \t]", "");
//            Function function = new Function(func);
//            parser.put(function.getName(), function);
//        }
//        String expr = scanner.readLine();
//        expr = expr.replaceAll("[ \t]", "");
//        System.out.println(parser.begin(expr));
        String filePath = "C:/Users/Dell/Desktop/sj.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String str = "";
        while((str = buffReader.readLine())!=null) {
            Parser parser = new Parser();
        str = str.replaceAll("[ \t]", "");
        System.out.println(parser.begin(str));
        }
    }
}
