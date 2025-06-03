import term.Expr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private static final String EOE = "E";
    private String expr;
    private String token;
    private int exprId;
    private BigInteger cur = BigInteger.ZERO;
    private HashMap<String, Function> funcMap = new HashMap<>();
    private ArrayList<Expr> parameters = new ArrayList<>();
    private Function function = null;

    public static boolean isDelim(char c) {
        return "+-*(),".indexOf(c) != -1;
    }

    public static boolean isPlusOrMinus(char c) {
        return  "+-".indexOf(c) != -1;
    }

    private String fresh(String token, char c) {
        if ("".equals(token)) {
            return String.valueOf(c);
        }
        if (c == '+') {
            return token;
        }
        return token.equals("+") ? "-" : "+";
    }

    public static boolean isDigit(char c) {
        return "0123456789".indexOf(c) != -1;
    }

    public static boolean isDigitOrChar(char c) {
        return "0123456789sincoxfghumyz".indexOf(c) != -1;
    }

    public void put(String str, Function function) {
        funcMap.put(str, function);
    }

    public void getToken() {
        String pre = String.copyValueOf(token.toCharArray());
        token = "";
        if (exprId == expr.length()) {
            token = EOE;
            return;
        }
        while (exprId <= expr.length() && " \t".indexOf(expr.charAt(exprId)) != -1) {
            exprId++;
        }
        if (exprId >= expr.length()) {
            token = EOE;
            return;
        }
        if (isPlusOrMinus(expr.charAt(exprId))) {
            while (exprId < expr.length() && isPlusOrMinus(expr.charAt(exprId))) {
                token = fresh(token, expr.charAt(exprId));
                exprId++;
                while (exprId <= expr.length() && " \t".indexOf(expr.charAt(exprId)) != -1) {
                    exprId++;
                }
            }
            while (exprId <= expr.length() && " \t".indexOf(expr.charAt(exprId)) != -1) {
                exprId++;
            }
            if ("**".contains(pre)) {
                while (exprId < expr.length() && isDigit(expr.charAt(exprId))) {
                    token += expr.charAt(exprId);
                    exprId++;
                }
            }
        }
        else if (isDelim(expr.charAt(exprId))) {
            token += expr.charAt(exprId);
            exprId++;
            if (exprId < expr.length()) {
                if (expr.charAt(exprId) == '*' && token.equals("*")) {
                    token += expr.charAt(exprId);
                    exprId++;
                }
            }
        } else if (isDigitOrChar(expr.charAt(exprId))) {
            while (exprId < expr.length() && isDigitOrChar(expr.charAt(exprId))) {
                token += expr.charAt(exprId);
                exprId++;
            }
        } else if (expr.charAt(exprId) == 'x') {
            token = "x";
            exprId++;
        }
    }

    public Expr begin(String str) {
        exprId = 0;
        expr = str;
        token = "";
        getToken();
        return work1();
    }

    public Expr begin(String str, BigInteger i) {
        exprId = 0;
        expr = str;
        token = "";
        cur = i;
        getToken();
        return work1();
    }

    public Expr work1() {
        char op;
        Expr result1 = new Expr(work2());
        while ((op = token.charAt(0)) == '+' || op == '-') {
            getToken();
            if (op == '+') {
                result1.add(work2());
            }
            else {
                result1.subtract(work2());
            }
        }
        return result1;
    }

    public Expr work2() {
        char op;
        Expr result1 = new Expr(work3());
        while ((op = token.charAt(0)) == '*') {
            getToken();
            result1.multiply(work3());
        }
        return result1;
    }

    public Expr work3() {
        Expr result;
        char op = 0;
        if (token.equals("+") || token.equals("-")) {
            op = token.charAt(0);
            getToken();
        }
        result = new Expr(work4());
        if (op == '-') {
            return Expr.negate(result);
        }
        return result;
    }

    public Expr work4() {
        Expr result1 = new Expr(work5());
        while (token.equals("**")) {
            getToken();
            result1.power(work5().toInt());
        }
        return result1;
    }

    public Expr work5() {
        Expr result;
        if (token.equals("(")) {
            getToken();
            result = new Expr(work1());
            getToken();
        }
        else {
            return work();
        }
        return result;
    }

    public Expr work() {
        if (token.equals("sin")) {
            getToken();
            return new Expr(work5().toSin());
        }
        if (token.equals("cos")) {
            getToken();
            return new Expr(work5().toCos());
        }
        if (token.equals("sum")) {
            return parseSum();
        }
        if (token.equals("i")) {
            getToken();
            return new Expr(cur.toString());
        }
        if (token.equals("f") || token.equals("g") || token.equals("h")) {
            return parseFunc();
        }
        if (function == null) {
            Expr expr = new Expr(token);
            getToken();
            return expr;
        } else {
            Expr expr;
            if (function.findPos(token) != -1) {
                expr = new Expr(parameters.get(function.findPos(token)));
            } else {
                expr = new Expr(token);
            }
            getToken();
            return expr;
        }
    }

    public Expr parseSum() {
        getToken(); //(
        getToken(); //i
        getToken(); //,
        getToken();
        String lowString;
        if (token.equals("-")) {
            getToken();
            lowString = "-" + token;
        } else {
            lowString = token;
        }
        final BigInteger low = new BigInteger(lowString);
        getToken(); //,
        getToken();
        String upString;
        if (token.equals("-")) {
            getToken();
            upString = "-" + token;
        } else {
            upString = token;
        }
        final BigInteger up = new BigInteger(upString);
        getToken(); //,
        int start = exprId;
        getToken();
        work1();
        int end = exprId;
        String sub = expr.substring(start, end - 1);
        Parser parser = new Parser();
        Expr ans = new Expr();
        for (cur = low; cur.compareTo(up) <= 0; cur = cur.add(BigInteger.ONE)) {
            ans.add(parser.begin(sub, cur));
        }
        getToken();
        return ans;
    }

    public Expr parseFunc() {
        Function function = funcMap.get(token);
        final String str = function.getExpr();
        Parser parser = new Parser();
        parser.funcMap = funcMap;
        parser.parameters = parameters;
        parser.function = function;
        getToken(); //(
        getToken();
        parameters.clear();
        for (int i = 0; i < function.getNum(); i++) {
            parameters.add(work1());
            getToken(); // . is it so?
        }
        Expr test = parser.begin(str);
        //System.out.println(test);
        return test;
    }
}
