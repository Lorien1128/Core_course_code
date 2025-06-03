//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//
//import com.oocourse.spec2.ExprInput;
//import com.oocourse.spec2.ExprInputMode;
//import organize.Expr;
//import organize.factor.BasicFactor;
//import organize.factor.SinOrCos;
//import organize.factor.Number;
//
//public class ParsedMain {
//
//    public static final ArrayList<String> LINES = new ArrayList<>();
//    public static final HashMap<String, Expr> HASH_MAP = new HashMap<>();
//
//    public static void getInput() {
//        ExprInput scanner = new ExprInput(ExprInputMode.ParsedMode);
//        int n = scanner.getCount();
//        for (int i = 0; i < n; i++) {
//            String curExpr = scanner.readLine();
//            LINES.add(curExpr);
//        }
//    }
//
//    public static String makeExpr(String line) {
//        HashSet<String> singleVariable =
//                new HashSet<String>(Arrays.asList("", "pos", "neg", "sin", "cos"));
//        ArrayList<String> temp = new ArrayList<>(Arrays.asList(line.split(" ")));
//        temp.remove("");
//        String[] splitedLine = temp.toArray(new String[0]);
//        String key = splitedLine[0];
//        String fun = splitedLine[1];
//        String s1;
//        if (splitedLine.length == 2) {
//            fun = "";
//            s1 = splitedLine[1];
//        } else {
//            s1 = splitedLine[2];
//        }
//        Expr expr1 = (s1.equals("x")) ? new Expr('x') :
//                (HASH_MAP.containsKey(s1) ? HASH_MAP.get(s1) :
//                        new Expr(new BigInteger(s1))
//                );
//
//        if (!singleVariable.contains(fun)) {
//            String s2 = splitedLine[3];
//            Expr expr2 = (s2.equals("x")) ? new Expr('x') :
//                    (HASH_MAP.containsKey(s2) ? HASH_MAP.get(s2) :
//                            new Expr(new BigInteger(s2))
//                    );
//            switch (fun) {
//                case "mul":
//                    HASH_MAP.put(key, expr1.times(expr2));
//                    break;
//                case "add":
//                    HASH_MAP.put(key, expr1.add(expr2));
//                    break;
//                case "sub":
//                    HASH_MAP.put(key, expr1.add(expr2.neg()));
//                    break;
//                case "pow":
//                    if (HASH_MAP.containsKey(s2)) {
//                        HASH_MAP.put(
//                                key,
//                                expr1.pow(expr2.getTerms().get(0).getCoefficient().longValue())
//                        );
//                    } else {
//                        long index = Integer.parseInt(splitedLine[3]);//指数是整型
//                        HASH_MAP.put(key, expr1.pow(index));
//                    }
//                    break;
//                default:
//                    System.out.println("Error!");
//            }
//        } else {
//            switch (fun) {
//                case "neg":
//                    HASH_MAP.put(key, expr1.neg());
//                    break;
//                case "sin":
//                    BasicFactor bf;
//                    if (expr1.getTerms().get(0).getFactors().isEmpty()) {
//                        bf = new Number(expr1.getTerms().get(0).getCoefficient());
//                    } else {
//                        bf = expr1.getTerms().get(0).getFactors().get(0);
//                    }
//                    HASH_MAP.put(key, new Expr(new SinOrCos(bf, 1, true)));
//                    break;
//                case "cos":
//                    if (expr1.getTerms().get(0).getFactors().isEmpty()) {
//                        bf = new Number(expr1.getTerms().get(0).getCoefficient());
//                    } else {
//                        bf = expr1.getTerms().get(0).getFactors().get(0);
//                    }
//                    HASH_MAP.put(key, new Expr(new SinOrCos(bf, 1, false)));
//                    break;
//                case "pos":
//                case "":
//                    HASH_MAP.put(key, expr1);
//                    break;
//                default:
//                    System.out.println("Error!");
//            }
//        }
//        return key;
//    }
//
//    public static void main(String[] args) {
//        getInput();
//        String lastKey = "";
//        for (String line : LINES) {
//            lastKey = makeExpr(line);
//        }
//        if (!HASH_MAP.isEmpty()) {
//            System.out.println(HASH_MAP.get(lastKey));
//        }
//    }
//}
