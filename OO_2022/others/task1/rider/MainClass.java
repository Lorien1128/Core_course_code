import com.oocourse.spec1.ExprInput;
import com.oocourse.spec1.ExprInputMode;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        ExprInput scanner = new ExprInput(ExprInputMode.ParsedMode);
        int n = scanner.getCount();
        List<String> exprs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String curExpr = scanner.readLine();
            exprs.add(curExpr);
        }
        HashMap<String, Factor> sites = new HashMap<String, Factor>();
        for (int i = 0; i < n; i++) {
            String t;
            String s = exprs.get(i);
            String[] str = s.split(String.valueOf(' '));
            Factor a = new Factor();
            Factor b = new Factor();
            Factor c = new Factor();
            Factor d = new Factor();
            if (str.length == 2) {
                a.factor(str[1]);
                if (str[1].contains("f")) {
                    a = sites.get(str[1]);
                }
                b.factor("0");
                str[1] = "add";
            }
            if (str.length > 2) {
                a.factor(str[2]);
                if (str[2].contains("f")) {
                    a = sites.get(str[2]);
                }
            }
            if (str.length > 3) {
                b.factor(str[3]);
                if (str[3].contains("f")) {
                    b = sites.get(str[3]);
                }
            }
            if (str[1].equals("add")) {
                c = Factor.add(a, b, c);
                sites.put(str[0], c);
            }
            if (str[1].equals("sub")) {
                c = Factor.sub(a, b, c);
                sites.put(str[0], c);
            }
            if (str[1].equals("neg")) {
                c = Factor.neg(a, c);
                sites.put(str[0], c);
            }
            if (str[1].equals("pos")) {
                c = Factor.pos(a, c);
                sites.put(str[0], c);
            }
            if (str[1].equals("mul")) {
                c = Factor.mul(a, b, c);
                sites.put(str[0], c);
            }
            if (str[1].equals("pow")) {
                c = Factor.pow(a, b, c, d);
                sites.put(str[0], c);
            }
        }
        sites.get("f" + String.valueOf(n)).print();
    }
}