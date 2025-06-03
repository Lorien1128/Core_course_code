import com.oocourse.spec3.ExprInput;
import com.oocourse.spec3.ExprInputMode;
import middle.Function;
import middle.Parser;
import organize.Expr;

public class NormalMain {

    public static void run() {
        ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
        int cnt = scanner.getCount();
        for (int i = 0; i < cnt; i++) {
            String funString = scanner.readLine();
            funString = funString.replaceAll("[ \t]", "");
            Function fun = new Function(funString);
            Parser.putFun(funString.substring(0, 1), fun);
        }
        String exprString = scanner.readLine();

        //        long start =  System.currentTimeMillis();
        exprString = exprString.replaceAll("[ \t]", "");
        Expr expr = Parser.parser(exprString);
        System.out.println(expr);
        //        long endTime =  System.currentTimeMillis();
        //        System.out.println(endTime-start);

    }

    public static void main(String[] args) {
        run();
    }

}
