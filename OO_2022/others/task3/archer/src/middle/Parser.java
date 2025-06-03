package middle;

import organize.Expr;
import organize.factor.SinOrCos;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * 这个类里面就存放解析相关的方法喽~~
     */

    private static HashMap<String, Function> FUN_LIST = new HashMap<>();

    public static void putFun(String s, Function fun) {
        FUN_LIST.put(s, fun);
    }

    public static Expr parser(String s) {
        Pattern number = Pattern.compile("[+-]?\\d+");

        //数字
        Matcher m = number.matcher(s);
        if (m.matches()) {
            return new Expr(new BigInteger(s));
        }


        int pm = pmOutBrackets(s);
        if (pm == -1) {
            if (s.charAt(0) == '+' || s.charAt(0) == '-') {
                //去掉前面的正负号
                boolean ifNeg = countNeg(s);
                int p2 = 0;
                for (; p2 < s.length(); p2++) {
                    if (s.charAt(p2) != '+' && s.charAt(p2) != '-') {
                        break;
                    }
                }
                return ifNeg ? parser(s.substring(p2)).neg() : parser(s.substring(p2));
            }
            //没有前面的加减号
            //找括号外面的乘号
            if (mulOutBrackets(s) != -1) {
                int p = mulOutBrackets(s);
                return (parser(s.substring(0, p)).times(parser(s.substring(p + 1))));
            }
            if (powOutBrackets(s) != -1) {
                int p = powOutBrackets(s);
                Pow pow = new Pow(parser(s.substring(0, p)), Integer.parseInt(s.substring(p + 2)));
                return pow.getExpr();

            } else {
                return parse2(s);
            }
        } else {
            return parser(s.substring(0, pm)).add(parser(s.substring(pm)));
        }
    }

    public static Expr parse2(String s) {

        final Pattern singleBrackets = Pattern.compile("^\\(.+\\)$");
        final Pattern sumFun = Pattern.compile("^sum\\((.+),(.+),(.+),(.+)\\)$");
        final Pattern sinFun = Pattern.compile("^sin\\((.+)\\)$");
        final Pattern cosFun = Pattern.compile("^cos\\((.+)\\)$");
        final Pattern definedFun = Pattern.compile("^([fgh])\\((.+)\\)$");
        //解析三角函数、求和函数、自定义函数、(表达式)
        Matcher m = singleBrackets.matcher(s);
        if (m.matches()) {
            //去掉括号
            return parser(s.substring(1, s.length() - 1));
        }
        m = sumFun.matcher(s);
        if (m.matches()) {
            Sum sum = new Sum(m.group(1), m.group(2), m.group(3), m.group(4));
            return sum.getExpr();
        }
        m = definedFun.matcher(s);
        if (m.matches()) {
            Function fun = FUN_LIST.get(m.group(1));
            return fun.substitution(m.group(2));
        }
        m = sinFun.matcher(s);
        if (m.matches()) {
            Expr exprInTri = parser(m.group(1));
            if (exprInTri.equals(Expr.ZERO)) {
                return Expr.ZERO;
            }
            SinOrCos result;
            if (exprInTri.toString().length() > exprInTri.neg().toString().length()) {
                result = new SinOrCos(exprInTri.neg(), 1, true);
                return new Expr(result).neg();
            }
            result = new SinOrCos(exprInTri, 1, true);
            return new Expr(result);
        }

        m = cosFun.matcher(s);
        if (m.matches()) {
            Expr exprInTri = parser(m.group(1));
            if (exprInTri.toString().length() > exprInTri.neg().toString().length()) {
                exprInTri = exprInTri.neg();
            }
            SinOrCos result = new SinOrCos(exprInTri, 1, false);
            if (exprInTri.equals(Expr.ZERO)) {
                return Expr.ONE;
            }
            return new Expr(result);
        }

        if (s.equals("x")) {
            return new Expr('x');
        } else {
            System.out.println(s);
            return Expr.ZERO;
        }
    }

    /**
     * 这几个函数便于处理表达式
     */
    public static boolean countNeg(String s) {
        //count the "-" in the beginning of s
        boolean neg = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-') {
                neg = !neg;
            } else if (s.charAt(i) != '+') {
                break;
            }
        }
        return neg;
    }

    public static int pmOutBrackets(String s) {
        //找到第一个（起分割作用的）加减号（而不是正负号）
        int bracketDepth = 0;
        int i = 0;
        if (s.charAt(0) == '(') {
            bracketDepth = 1;
            i = 1;
        } else {
            while (s.charAt(i) == '+' || s.charAt(i) == '-') {
                i++;
            }
        }
        for (; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                bracketDepth++;
            } else if (s.charAt(i) == ')') {
                bracketDepth--;
            }
            if ((bracketDepth == 0) && (s.charAt(i) == '+' || s.charAt(i) == '-')
                    && s.charAt(i - 1) != '*') {
                return i;
            }
        }
        return -1;
    }

    public static int mulOutBrackets(String s) {
        //find the first "*" out of brackets
        int bracketDepth = (s.charAt(0) == '(') ? 1 : 0;
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                bracketDepth++;
            } else if (s.charAt(i) == ')') {
                bracketDepth--;
            }
            if ((bracketDepth == 0) && s.charAt(i) == '*' && s.charAt(i + 1) != '*'
                    && s.charAt(i - 1) != '*') {
                return i;
            }
        }
        return -1;
    }

    public static int powOutBrackets(String s) {
        int bracketDepth = (s.charAt(0) == '(') ? 1 : 0;
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                bracketDepth++;
            } else if (s.charAt(i) == ')') {
                bracketDepth--;
            }
            if (bracketDepth == 0 && (s.charAt(i) == '*') && (s.charAt(i + 1) == '*')) {
                return i;
            }
        }
        return -1;
    }
}
