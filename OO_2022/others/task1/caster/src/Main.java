
import java.io.*;

//import java.math.BigInteger;
//import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /*ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
        //Scanner scanner = new Scanner(System.in);
        String rawStr = scanner.readLine();
        //String rawStr = scanner.nextLine();
        StrProcess sp = new StrProcess();
        Reader rd = new Reader(sp.process(rawStr));
        Parser parser = new Parser(rd);
        Expression expr = parser.parseExpr();
        Poly result = expr.toPoly();
        System.out.println(result);*/
        String filePath = "C:/Users/Dell/Desktop/sj.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String str = "";
        while((str = buffReader.readLine())!=null){
            StrProcess sp = new StrProcess();
            Reader rd = new Reader(sp.process(str));
            Parser parser = new Parser(rd);
            Expression expr = parser.parseExpr();
            Poly result = expr.toPoly();
            System.out.println(result);
        }
    }
}
