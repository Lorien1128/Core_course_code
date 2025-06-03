import java.util.HashMap;

public class Task3 {
    public static void main(String[] args) {
//        ExprInput scanner = new ExprInput(ExprInputMode.NormalMode);
//        int cnt = scanner.getCount();
//        HashMap<String, Function> functionHashMap = new HashMap<>();
//        for (int i = 0; i < cnt; i++) {
//            String func = scanner.readLine();
//            Function f = new Function(func);
//            functionHashMap.put(f.getName(), f);
//        }
//        String expr = scanner.readLine();
//        expr = Operation.extendFunc(expr, functionHashMap);
//        expr = Operation.optimize(expr);
//        System.out.println(expr);
        HashMap<String, Function> functionHashMap = new HashMap<>();
        Function f = new Function("f(y,x)=x*(y+1)");
        Function g = new Function("g(x)=sin(x**2)");
        functionHashMap.put(f.getName(), f);
        functionHashMap.put(g.getName(), g);
        String str = "f(g(sin(x)),cos(sum(i,1,3,i**2)))+f((2*x),cos(3+x))";
        str = Operation.extendFunc(str,functionHashMap);
        System.out.println(str);
    }
}


