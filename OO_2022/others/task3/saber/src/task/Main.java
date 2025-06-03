package task;

import com.oocourse.spec3.ExprInput;
import com.oocourse.spec3.ExprInputMode;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
        int n = scanner.getCount();
        ArrayList<SelfDefine> selfDefines = new ArrayList<>();
        String define;
        for (int i = 0; i < n; i++) {
            define = scanner.readLine();
            selfDefines.add(new SelfDefine(define));
        }
        String originstring = scanner.readLine();
        Formatchange formatchange = new Formatchange();
        originstring = formatchange.formatechange(originstring);//整理表达式格式

        SumAndSelf sumAndSelf = new SumAndSelf();
        originstring = sumAndSelf.sumandself(originstring, selfDefines);//替换sum及自定义函数

        originstring = formatchange.formatechange(originstring);//再次整理格式

        //System.out.println(originstring);

        Parser parser = new Parser();
        //Simplify simplify = new Simplify();
        //System.out.println(parser.parser(originstring));
        System.out.println(parser.paserSimplify(parser.parser(originstring), 1));//去括号并合并同类项
    }
}
