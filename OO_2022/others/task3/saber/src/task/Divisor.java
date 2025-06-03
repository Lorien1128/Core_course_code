package task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Divisor {
    private static String constant = "[-]?\\d+";
    private static Pattern patterncon = Pattern.compile(constant);

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getIsconstant() {
        return isconstant;
    }

    private Integer isconstant;

    private String string;

    private BigInteger index;

    public BigInteger getIndex() {
        return index;
    }

    public Divisor(String s) {
        this.string = s;
        index = new BigInteger("1");
    }

    public String getResult() {
        String data;
        ArrayList<Integer> locatbracket = new ArrayList<>();
        ArrayList<Integer> locatneg = new ArrayList<>();
        ArrayList<Integer> locatpow = new ArrayList<>();
        findBracket(string, locatbracket);
        findPow(string, locatpow);
        findNeg(string, locatneg);
        ArrayList<Integer> locatsin = new ArrayList<>();
        ArrayList<Integer> locatcos = new ArrayList<>();
        findSinCos(string, locatsin, "sin");
        findSinCos(string, locatcos, "cos");
        if (locatbracket.size() != 0) {
            Parser parser = new Parser();
            String stringinBracket = string.substring(locatbracket.get(0) + 1, locatbracket.get(1));
            data = parser.parser(stringinBracket);
            if (locatsin.size() != 0) {
                data = "sin(" + data + ")";
            }
            else if (locatcos.size() != 0) {
                data = "cos(" + data + ")";
            }
        }
        else {
            data = data(locatneg, locatpow, string);
        }
        if (locatpow.size() != 0) {
            Pow pow = new Pow(data, string.substring(locatpow.get(1) + 1));
            data = pow.getResult();
        }
        if (locatneg.size() != 0) {
            Neg neg = new Neg(data);
            data = neg.getResult();
        }
        return data;
    }

    public void findNeg(String expression, ArrayList<Integer> locat) {
        locat.removeAll(locat);
        if (expression.charAt(0) == '-') {
            locat.add(0);
        }
    }

    public void findPow(String expression, ArrayList<Integer> locat) {
        int bracket = 0;
        char m;
        locat.removeAll(locat);
        for (int i = 0; i < expression.length(); i++) {
            m = expression.charAt(i);
            if (m == '(') {
                bracket++;
            }
            else if (m == ')') {
                bracket--;
            }
            if (bracket == 0 && m == '*' && expression.charAt(i + 1) == '*') {
                locat.add(i);
                locat.add(i + 1);
                return;
            }
        }
    }

    public void findSinCos(String expression, ArrayList<Integer> locat, String type) {
        locat.removeAll(locat);
        if (expression.length() <= 4) {
            return;
        }
        char m;
        int bracket = 0;
        for (int i = 0; i < expression.length(); i++) {
            m = expression.charAt(i);
            if (m == '(') {
                bracket++;
            }
            else if (m == ')') {
                bracket--;
            }
            if (type.equals("sin") && bracket == 0 &&
                    m == 's' && expression.charAt(i + 1) == 'i' &&
                    expression.charAt(i + 2) == 'n') {
                locat.add(i);
                return;
            }
            else if (type.equals("cos") && bracket == 0 &&
                    m == 'c' && expression.charAt(i + 1) == 'o' &&
                    expression.charAt(i + 2) == 's') {
                locat.add(i);
                return;
            }
        }
    }

    public void findBracket(String expression, ArrayList<Integer> locat) {
        locat.removeAll(locat);
        int bracket = 0;
        char m;
        int flag = 0;
        for (int i = 0; i < expression.length(); i++) {
            m = expression.charAt(i);
            if (flag == 0 && m == '(') {
                locat.add(i);
            }
            else if (bracket == 1 && m == ')') {
                locat.add(i);
            }
            if (m == '(') {
                bracket++;
                flag = 1;
            }
            else if (m == ')') {
                bracket--;
            }
        }
    }

    public String data(ArrayList<Integer> locatneg, ArrayList<Integer> locatpow, String divisor) {
        String data;
        if (locatneg.size() != 0) {
            if (locatpow.size() != 0) {
                data = divisor.substring(locatneg.get(0) + 1, locatpow.get(0));
            }
            else {
                data = divisor.substring(locatneg.get(0) + 1);
            }
        }
        else {
            if (locatpow.size() != 0) {
                data = divisor.substring(0, locatpow.get(0));
            }
            else {
                data = divisor;
            }
        }
        return data;
    }

    public void simplifyDivisor(int type) {
        String data = new String();//此时只有常数因子，幂函数，三角函数，且幂次均为1
        ArrayList<Integer> locatbracket = new ArrayList<>();
        findBracket(string, locatbracket);
        ArrayList<Integer> locatneg = new ArrayList<>();
        ArrayList<Integer> locatpow = new ArrayList<>();
        findPow(string, locatpow);
        findNeg(string, locatneg);
        ArrayList<Integer> locatsin = new ArrayList<>();
        ArrayList<Integer> locatcos = new ArrayList<>();
        findSinCos(string, locatsin, "sin");
        findSinCos(string, locatcos, "cos");//得到因子中的表达式
        if (locatbracket.size() != 0) {
            Parser parser = new Parser();
            String stringinBracket = string.substring(locatbracket.get(0) + 1, locatbracket.get(1));
            data = parser.paserSimplify(stringinBracket, type);//递归化简
            if (locatcos.size() != 0 || locatsin.size() != 0) {
                if (locatsin.size() != 0 && data.equals("0")) {
                    data = "0";
                } else if (locatcos.size() != 0 && data.equals("0")) {
                    data = "1";
                } else {
                    int flag;
                    flag = judgeExpression(data);
                    data = addSinCos(data, flag, locatsin, locatcos);
                }
            }
            if (locatneg.size() != 0) {
                data = "-" + data;
            }
            if (locatpow.size() != 0) {
                index = new BigInteger(string.substring(locatpow.get(1) + 1));
            }
        }
        else {
            if (locatpow.size() != 0) {
                index = new BigInteger(string.substring(locatpow.get(1) + 1));
                data = string.substring(0, locatpow.get(0));
            }
            else {
                data = string;
            }
        }
        string = data;
        Matcher matchercon = patterncon.matcher(string);
        if (matchercon.matches()) {
            isconstant = 1;
        }
        else {
            isconstant = 0;
        }
    }

    public String addSinCos(String s, int flag, ArrayList<Integer> sin, ArrayList<Integer> cos) {
        String data = s;
        if (flag == 1) {
            if (sin.size() != 0) {
                data = "sin((" + data + "))";
            } else if (cos.size() != 0) {
                data = "cos((" + data + "))";
            }
        } else if (flag == 0) {
            if (sin.size() != 0) {
                data = "sin(" + data + ")";
            } else if (cos.size() != 0) {
                data = "cos(" + data + ")";
            }
        }
        return data;
    }

    public int judgeExpression(String expression) {
        int bracket = 0;
        char m;
        for (int i = 0; i < expression.length(); i++) {
            m = expression.charAt(i);
            if (m == '(') {
                bracket++;
            }
            else if (m == ')') {
                bracket--;
            }
            else if (m == '*' && expression.charAt(i - 1) != '*' &&
                    expression.charAt(i + 1) != '*') {
                if (bracket == 0) {
                    return 1;
                }
            }
            else if (m == '+' && bracket == 0) {
                return 1;
            }
            else if (m == '-' && bracket == 0) {
                boolean judge = (expression.charAt(i + 1) < '0' || expression.charAt(i + 1) > '9');
                if (i > 0) {
                    return 1;
                }
                else if (judge) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
