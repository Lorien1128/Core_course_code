import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operation {
    public static String extendFunc(String rawStr, HashMap<String, Function> functionHashMap) {
        String str = rawStr.replaceAll("[ \\t]", "");
        str = extendSum(str);
        str = extendDiyfunc(str, functionHashMap);
        return str;
    }

    public static ArrayList<BaseTerm> extendAll(String rawStr) {
        String str = rawStr.replaceAll("[ \\t]", "");
        while (unfold(str) != null) {
            str = unfold(str);
        }
        str = preProcess(str);
        str = toStr(new Polynomial(str).calculate());
        str = str.replaceAll("sin\\(0\\)", "0");
        str = str.replaceAll("cos\\(0\\)", "1");
        str = preProcess(str);
        ArrayList<BaseTerm> res = new Polynomial(str).calculate();
        return res;
    }

    private static ArrayList<BaseTerm> copy(ArrayList<BaseTerm> sourcelist) {
        ArrayList<BaseTerm> res = new ArrayList<>();
        for (BaseTerm bt : sourcelist) {
            res.add(bt.clone());
        }
        return res;
    }

    public static String optimize(String rawStr) {
        ArrayList<BaseTerm> baseTermArrayList = extendAll(rawStr);
        ArrayList<BaseTerm> res = copy(baseTermArrayList);
        boolean shorter = true;
        int minlen = toStr(baseTermArrayList).length();
        while (shorter) {
            shorter = false;
            for (int i = 0; i < baseTermArrayList.size(); i++) {
                BaseTerm bt = baseTermArrayList.get(i);
                for (int index = 0; index < bt.getSinfunclist().size(); index++) {
                    TrigoFunction tf = bt.getSinfunclist().get(index);
                    if (tf.getOutsidePower() >= 2) {
                        BaseTerm nbt1 = bt.clone();
                        baseTermArrayList.remove(bt);
                        nbt1.setCoefficient(nbt1.getCoefficient().negate());
                        nbt1.getSinfunclist().get(index).setOutsidePower(tf.getOutsidePower() - 2);
                        BaseTerm nbt2 = bt.clone();
                        nbt2.getSinfunclist().get(index).setOutsidePower(tf.getOutsidePower() - 2);
                        tf.setOutsidePower(2);
                        nbt1.getCosfunclist().add(tf);
                        nbt1.setCosfunclist(BaseTerm.combineTrigo(nbt1.getCosfunclist()));
                        nbt1.setSinfunclist(BaseTerm.combineTrigo(nbt1.getSinfunclist()));
                        nbt2.setSinfunclist(BaseTerm.combineTrigo(nbt2.getSinfunclist()));
                        baseTermArrayList.add(nbt1);
                        baseTermArrayList.add(nbt2);
                        baseTermArrayList = Term.combine(baseTermArrayList);
                        if (toStr(baseTermArrayList).length() < minlen) {
                            shorter = true;
                            minlen = toStr(baseTermArrayList).length();
                            res = copy(baseTermArrayList);
                            break;
                        } else {
                            baseTermArrayList = copy(res); } } }
                for (int index = 0; index < bt.getCosfunclist().size(); index++) {
                    TrigoFunction tf = bt.getCosfunclist().get(index);
                    if (tf.getOutsidePower() == 2) {
                        BaseTerm nbt1 = bt.clone();
                        baseTermArrayList.remove(bt);
                        nbt1.setCoefficient(nbt1.getCoefficient().negate());
                        nbt1.getCosfunclist().get(index).setOutsidePower(tf.getOutsidePower() - 2);
                        BaseTerm nbt2 = bt.clone();
                        nbt2.getCosfunclist().get(index).setOutsidePower(tf.getOutsidePower() - 2);
                        tf.setOutsidePower(2);
                        nbt1.getSinfunclist().add(tf);
                        nbt1.setCosfunclist(BaseTerm.combineTrigo(nbt1.getCosfunclist()));
                        nbt2.setCosfunclist(BaseTerm.combineTrigo(nbt2.getCosfunclist()));
                        nbt1.setSinfunclist(BaseTerm.combineTrigo(nbt1.getSinfunclist()));
                        baseTermArrayList.add(nbt1);
                        baseTermArrayList.add(nbt2);
                        baseTermArrayList = Term.combine(baseTermArrayList);
                        if (toStr(baseTermArrayList).length() < minlen) {
                            shorter = true;
                            minlen = toStr(baseTermArrayList).length();
                            res = copy(baseTermArrayList);
                            break;
                        } else {
                            baseTermArrayList = copy(res); } } }
                if (shorter) {
                    break; } } }
        return toStr(res); }

    private static String unfold(String rawStr) {
        String[] strings = findSubpoly(rawStr);
        if (strings == null) {
            return null;
        }
        String toReplace = preProcess(strings[1]);
        toReplace = toStr(new Polynomial(toReplace).calculate());
        toReplace = "(" + toReplace + ")";
        return strings[0] + toReplace + strings[2];
    }

    public static String preProcessOffindSubpoly(String rawStr) {
        char[] ch = rawStr.toCharArray();
        boolean flag = false;
        int state = 0;
        int preState = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                state++;
            }
            if (ch[i] == ')') {
                state--;
            }
            if (i < ch.length - 1 && (ch[i] == 'n' || ch[i] == 's')
                    && ch[i + 1] == '(' && !flag) {
                ch[i + 1] = '@';
                flag = true;
                preState = state;
                state++;
            }
            if (flag && state == preState && ch[i] == ')') {
                ch[i] = '#';
                flag = false;
            }
        }
        return String.valueOf(ch);
    }

    public static String[] findSubpoly(String rawStr) {
        String s = preProcessOffindSubpoly(rawStr);
        char[] chs = s.toCharArray();
        int state = 0;
        Matcher m1 = Pattern.compile("([\\(\\)])").matcher(s);
        int n = 0;
        while (m1.find()) {
            n++; }
        int[] mark = new int[n];
        int[] pos = new int[n];
        int count = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(' || chs[i] == ')') {
                if (chs[i] == '(') {
                    state++;
                } else {
                    state--; }
                mark[count] = state;
                pos[count] = i;
                count++; } }
        int begin = -1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (i < n - 3 && mark[i + 1] == mark[i] + 1 && (i == 0 || mark[i] == mark[i - 1] + 1)) {
                for (int j = i + 2; j < n; j++) {
                    if (mark[j] == mark[i] - 1) {
                        begin = pos[i];
                        end = pos[j];
                        break; }
                    if (mark[j] != mark[i] + 1 && mark[j] != mark[i]) {
                        break; } }
                if (begin != -1) {
                    break;
                } } }
        if (begin == -1) {
            return null; }
        else {
            String[] res = new String[3];
            String str = "";
            for (int i = 0; i < begin; i++) {
                str = str + chs[i]; }
            res[0] = str;
            str = "";
            for (int i = begin + 1; i < end; i++) {
                str = str + chs[i];
            }
            res[1] = str;
            str = "";
            for (int i = end + 1; i < chs.length; i++) {
                str = str + chs[i];
            }
            res[2] = str;
            for (int i = 0; i <= 2; i++) {
                res[i] = res[i].replaceAll("@", "(");
                res[i] = res[i].replaceAll("#", ")");
            }
            return res;
        }
    }

    private static String preProcessOfextendDiyfunc(String rawStr) {
        char[] ch = rawStr.toCharArray();
        boolean flag = false;
        int state = 0;
        int preState = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                state++;
            }
            if (ch[i] == ')') {
                state--;
            }
            if (i < ch.length - 1 && (ch[i] == 'f' || ch[i] == 'g' || ch[i] == 'h')
                    && ch[i + 1] == '(' && !flag) {
                ch[i + 1] = '@';
                flag = true;
                preState = state;
                state++;
            }
            if (flag && ch[i] == ',' && state == preState + 1) {
                ch[i] = '$';
            }
            if (flag && state == preState && ch[i] == ')') {
                ch[i] = '#';
                flag = false;
            }
        }
        return String.valueOf(ch);
    }

    private static String extendDiyfunc(String rawStr, HashMap<String, Function> functions) {
        String str = rawStr;
        str = preProcessOfextendDiyfunc(str);
        String s1 = "([fgh])@(.*?)#";
        Pattern p1 = Pattern.compile(s1);
        Matcher m1 = p1.matcher(str);
        while (m1.find()) {
            String name = m1.group(1);
            String actualPara = m1.group(2);
            Function func = functions.get(name);
            String toReplace = "(" + func.substituteIn(actualPara) + ")";
            str = str.replaceFirst(s1,toReplace);
        }
        return str;
    }

    private static String preProcessOfextendSum(String rawStr) {
        char[] ch = rawStr.toCharArray();
        boolean flag = false;
        int state = 0;
        int preState = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                state++;
            }
            if (ch[i] == ')') {
                state--;
            }
            if (i < ch.length - 1 && ch[i] == 'm' && ch[i + 1] == '(' && !flag) {
                ch[i + 1] = '@';
                flag = true;
                preState = state;
                state++;
            }
            if (flag && state == preState && ch[i] == ')') {
                ch[i] = '#';
                flag = false;
            }
            if (i < ch.length - 1 && ch[i] == 's' && ch[i + 1] == 'i') {
                ch[i + 1] = 'o';
            }
        }
        return String.valueOf(ch);
    }

    public static String extendSum(String rawStr) {
        String str = rawStr;
        str = preProcessOfextendSum(str);
        String s1 = "sum@(.*?)#";
        Pattern p1 = Pattern.compile(s1);
        Matcher m1 = p1.matcher(str);
        while (m1.find()) {
            String mch = m1.group(1);
            String[] mches = mch.split(",");
            BigInteger begin = new BigInteger(mches[1]);
            BigInteger end = new BigInteger(mches[2]);
            String expr = mches[3];
            String toReplace;
            if (begin.subtract(end).signum() == 1) {
                toReplace = "0";
            }
            else {
                toReplace = "((" + expr.replaceAll("i","(" + begin + ")") + ")";
                for (BigInteger i = begin.add(BigInteger.ONE); i.subtract(end).signum() != 1;
                     i = i.add(BigInteger.ONE)) {
                    toReplace = toReplace + "+(" + expr.replaceAll("i","(" + i + ")") + ")";
                }
                toReplace = toReplace + ")";
            }
            str = str.replaceFirst(s1,toReplace);
        }
        str = str.replaceAll("son", "sin");
        return str;
    }

    public static String removeSpace(String rawStr) {
        if (rawStr.matches(".*\\*[ \\t]*[\\+\\-][^0-9].*")) {
            System.exit(0);
        }
        String pattern1 = "\\d[ \\t]+\\d";
        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(rawStr);
        if (m1.find()) {
            System.exit(0);
        }
        String pattern2 = "[\\+\\-][ \\t]*[\\+\\-][ \\t]*[\\+\\-][ \\t]+\\d";
        Pattern p2 = Pattern.compile(pattern2);
        Matcher m2 = p2.matcher(rawStr);
        if (m2.find()) {
            System.exit(0);
        }
        String pattern3 = "\\*[ \\t]+\\*";
        Pattern p3 = Pattern.compile(pattern3);
        Matcher m3 = p3.matcher(rawStr);
        if (m3.find()) {
            System.exit(0);
        }
        String str = rawStr.replaceAll("[ \\t]", "");
        String pattern4 = "\\s+";
        Pattern p4 = Pattern.compile(pattern4);
        Matcher m4 = p4.matcher(str);
        if (m4.find()) {
            System.exit(0);
        }
        String pattern5 = "\\*\\*[\\+\\-]{2,}";
        Pattern p5 = Pattern.compile(pattern5);
        Matcher m5 = p5.matcher(str);
        if (m5.find()) {
            System.exit(0);
        }
        str = str.replaceAll("\\*\\*\\+", "\\*\\*");
        if (str.matches(".*\\*[\\+\\-]{2,}.*")) {
            System.exit(0);
        }
        return str;
    }

    public static String preProcess(String rawStr) {
        String str = rawStr.replaceAll("\\*\\*\\+", "\\*\\*");
        char[] characters = str.toCharArray();
        int addcount = 0;
        int subcount = 0;
        int state = 0; //state : the number of '('
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == '(') {
                state++;
            } else if (characters[i] == ')') {
                state--;
            } if (characters[i] == '@' || characters[i] == '#') {
                System.exit(0);
            } if (characters[i] == '+' || characters[i] == '-') {
                if (i == characters.length - 1) {
                    System.exit(0);
                } if (characters[i] == '+') {
                    addcount = 1;
                } else {
                    subcount = 1;
                } int j = i + 1;
                while (j < characters.length && (characters[j] == '+' || characters[j] == '-')) {
                    if (characters[j] == '+') {
                        addcount++;
                    } else {
                        subcount++;
                    } j++;
                } if (addcount + subcount == 2) {
                    if (subcount % 2 == 1) {
                        characters[i] = '-';
                    } else {
                        characters[i] = '+';
                    } characters[i + 1] = ' ';
                } else if (addcount + subcount == 3 || addcount + subcount > 3) {
                    if (i + 3 >= characters.length) {
                        System.exit(0);
                    } if (characters[i + 3] < '0' || characters[i + 3] > '9' ||
                            addcount + subcount > 3) {
                        System.exit(0);
                    } if (subcount % 2 == 1) {
                        characters[i] = '-';
                    } else {
                        characters[i] = '+';
                    } characters[i + 1] = ' ';
                    characters[i + 2] = ' ';
                } addcount = 0;
                subcount = 0;
            } if ((i == 0 && characters[0] == '+') || (i > 0 && state == 0 && characters[i] == '+'
                    && characters[i - 1] != '*')) {
                characters[i] = '@';
            } if ((i == 0 && characters[0] == '-') || (i > 0 && state == 0 && characters[i] == '-'
                    && characters[i - 1] != '*')) {
                characters[i] = '#';
            }
        }
        return String.valueOf(characters).replaceAll("[ \\t]", "");
    }

    public static String toStr(ArrayList<BaseTerm> array) {
        String str = "";
        if (array.size() == 0) {
            return "0";
        } else {
            if (array.get(0).getCoefficient().signum() == -1) {
                for (int i = 1; i < array.size(); i++) {
                    if (array.get(i).getCoefficient().signum() == 1) {
                        BaseTerm temp = array.get(0);
                        array.set(0,array.get(i));
                        array.set(i,temp);
                        break;
                    }
                }
            }
            for (int i = 0; i < array.size(); i++) {
                if (i == 0) {
                    str = str + array.get(i);
                } else {
                    if (array.get(i).getCoefficient().signum() == 1) {
                        str = str + "+";
                    }
                    str = str + array.get(i);
                }
            }
        }
        return str;
    }
}
