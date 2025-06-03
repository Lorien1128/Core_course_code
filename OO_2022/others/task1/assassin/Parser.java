import java.math.BigDecimal;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final HashMap<Integer, BigDecimal> result;
    private final Pattern numberPatten = Pattern.compile("\\d+");

    public Parser(String expression) {
        String expr = del(expression);
        //  System.out.println(expr);
        this.result = parse(expr).getResults();
    }

    public HashMap<Integer,BigDecimal> getResult() {
        return this.result;
    }

    public String del(String expr) {
        String exprNew = "";
        exprNew = expr.replaceAll("(\\+\\+-)|(-\\+\\+)|(\\+-\\+)|(---)|(\\+-)|(-\\+)|(-)","-");
        exprNew = exprNew.replaceAll("(\\+\\+\\+)|(\\+--)|(-\\+-)|(--\\+)|(--)|(\\+\\+)|(\\+)","+");
        exprNew = exprNew.replaceAll("(\\*\\+)","*");
        return exprNew;
    }

    public Operate parse(String expr) {
        int bracLeft = findBracLeft(expr);
        int bracRight = findBracRight(expr);
        int positionAddorSub = findAddorSub(expr);
        int positionMul = findMul(expr);
        int positionPow = findPow(expr);
        //  +/-
        if (positionAddorSub != -1) {
            if (expr.charAt(positionAddorSub) == '+') {
                return new Add(parse(expr.substring(0, positionAddorSub)),
                        parse(expr.substring(positionAddorSub + 1)));
            }
            else {
                return new Sub(parse(expr.substring(0, positionAddorSub)),
                        parse(expr.substring(positionAddorSub + 1)));
            }
        }
        // *
        else if (positionMul != -1) {
            return new Mul(parse(expr.substring(0, positionMul)),
                    parse(expr.substring(positionMul + 1)));
        }
        // **
        else if (positionPow != -1) {
            return new Exp(parse(expr.substring(0, positionPow)),
                    parse(expr.substring(positionPow + 2)));
        }
        // have bracket
        else if (bracLeft != expr.length() || bracRight != -1) {
            return new Add(parse(expr.substring(1,expr.length() - 1)), parse(""));
        }
        else if (!expr.equals("")) {
            HashMap<Integer,BigDecimal> map = new HashMap<>();
            Matcher matcher = numberPatten.matcher(expr);
            if (matcher.find()) {
                map.put(0,new BigDecimal(matcher.group()));
            }
            else {
                map.put(1, new BigDecimal(1));
            }
            return new Number(map);
        }
        else {
            return new Number(new HashMap<>());
        }
    }

    public int findBracLeft(String expr) {
        int pos = expr.length();
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                pos = i;
            }
        }
        return pos;
    }

    public int findBracRight(String expr) {
        int pos = -1;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == ')') {
                pos = i;
            }
        }
        return pos;
    }

    public int findAddorSub(String expr) {
        int pos = -1;
        int flag = 0;// 当在()内搜索时，flag->1
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                flag = 1;
                continue;
            }
            if (expr.charAt(i) == ')') {
                flag = 0;
                continue;
            }
            if ((expr.charAt(i) == '+' || expr.charAt(i) == '-') && flag == 0) {
                if (i == 0 || (i >= 1 && !(expr.charAt(i - 1) == '*'))) {
                    pos = i;
                }
            }
        }
        return pos;
    }

    public int findMul(String expr) {
        int pos = -1;
        int flag = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                flag = 1;
                continue;
            }
            if (expr.charAt(i) == ')') {
                flag = 0;
                continue;
            }
            if (i - 1 >= 0 && expr.charAt(i) == '*' && expr.charAt(i + 1) != '*' &&
                    expr.charAt(i - 1) != '*' && flag == 0 && i + 1 < expr.length()) {
                pos = i;
            }
        }
        return pos;
    }

    public int findPow(String expr) {
        int pos = -1;
        int flag = 0;
        for (int i = 0; i < expr.length(); i++) {

            if (expr.charAt(i) == '(') {
                flag = 1;
                continue;
            }
            if (expr.charAt(i) == ')') {
                flag = 0;
                continue;
            }
            if ((expr.charAt(i) == '*' && expr.charAt(i + 1) == '*') && flag == 0) {
                pos = i;
            }
        }
        return pos;
    }

}
