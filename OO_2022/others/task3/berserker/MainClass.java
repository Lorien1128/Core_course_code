import com.oocourse.spec3.ExprInput;
import com.oocourse.spec3.ExprInputMode;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);

        int cnt = scanner.getCount();

        ArrayList<Function> functions = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            String func = scanner.readLine();
            functions.add(new Function(func));
        }

        String str = scanner.readLine();
        ExprClass result = new ExprClass().expr(str.trim(), functions);
        new Compute().exprSimplify(result);
        System.out.println(result);
    }

}


