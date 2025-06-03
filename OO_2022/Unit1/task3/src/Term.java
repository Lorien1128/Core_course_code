import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Term {
    private ArrayList<BasePoly> basePolyArrayList = new ArrayList<>();

    public static String preProcess(String rawTerm) {
        char[] characters = rawTerm.toCharArray();
        int state = 0;
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == '(') {
                state++;
            } else if (characters[i] == ')') {
                state--;
            }
            if (characters[i] == '*' && state == 0) {
                characters[i] = '#';
            }
        }
        return String.valueOf(characters);
    }

    public Term(String rawTerm) {
        String str = Operation.preProcessOffindSubpoly(rawTerm);
        String pattern1 = "(\\([^\\(\\)]+?\\))\\*\\*(\\d+)";
        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(str);
        while (m1.find()) {
            int k = Integer.parseInt(m1.group(2));
            if (k == 0) {
                str = str.replaceFirst(pattern1,"1");
            } else {
                String s = m1.group(1);
                String toReplace =  String.join("", Collections.nCopies(k - 1, s + "*"));
                toReplace = toReplace + s;
                str = str.replaceFirst(pattern1,toReplace);
            }
        }
        String pattern2 = "(x|\\d+)\\*\\*(\\d+)";
        Pattern p2 = Pattern.compile(pattern2);
        Matcher m2 = p2.matcher(str);
        while (m2.find()) {
            int k = Integer.parseInt(m2.group(2));
            if (k == 0) {
                str = str.replaceFirst(pattern2,"1");
            } else {
                String toReplace = String.join("", Collections.nCopies(k - 1,  m2.group(1) + "*"));
                toReplace = toReplace + m2.group(1);
                str = str.replaceFirst(pattern2,toReplace);
            }
        }
        String pattern3 = "(((sin)|(cos))@[^@#]*?#)\\*\\*(\\d+)";
        Pattern p3 = Pattern.compile(pattern3);
        Matcher m3 = p3.matcher(str);
        while (m3.find()) {
            int k = Integer.parseInt(m3.group(5));
            if (k == 0) {
                str = str.replaceFirst(pattern3,"1");
            } else {
                String s = m3.group(1);
                String toReplace =  String.join("", Collections.nCopies(k - 1, s + "*"));
                toReplace = toReplace + s;
                str = str.replaceFirst(pattern3,toReplace);
            }
        }
        if (str.contains("**")) {
            System.exit(0);
        }
        str = str.replaceAll("@", "(");
        str = str.replaceAll("#", ")");
        str = preProcess(str);
        String[] basePolies = str.split("#");
        for (String basePoly : basePolies) {
            this.basePolyArrayList.add(new BasePoly(basePoly));
        }
    }

    public ArrayList<BaseTerm> calculate() {
        ArrayList<BaseTerm> result = new ArrayList<>();
        for (BasePoly basePoly : this.basePolyArrayList) {
            if (result.size() == 0) {
                result = basePoly.calculate();
            }
            else {
                result = mult(result, basePoly.calculate());

            }
        }
        result = combine(result);
        return result;
    }

    public static ArrayList<BaseTerm> mult(ArrayList<BaseTerm> array1, ArrayList<BaseTerm> array2) {
        ArrayList<BaseTerm> result = new ArrayList<>();
        for (BaseTerm bt1 : array1) {
            for (BaseTerm bt2 : array2) {
                BaseTerm bt = new BaseTerm(null);
                bt.setCoefficient(bt1.getCoefficient().multiply(bt2.getCoefficient()));
                bt.setPower(bt1.getPower() + bt2.getPower());
                bt.multTrigofunclist(bt1, bt2);
                result.add(bt);
            }
        }
        for (BaseTerm bt : result) {
            bt.setSinfunclist(BaseTerm.combineTrigo(bt.getSinfunclist()));
            bt.setCosfunclist(BaseTerm.combineTrigo(bt.getCosfunclist()));
        }
        return result;
    }

    public static ArrayList<BaseTerm> combine(ArrayList<BaseTerm> array) {
        ArrayList<BaseTerm> result = new ArrayList<>();
        for (BaseTerm bt : array) {
            if (result.size() == 0) {
                result.add(bt.clone());
            }
            else {
                int find = 0;
                for (BaseTerm bt2 : result) {
                    if (bt.same(bt2)) {
                        bt2.setCoefficient(bt2.getCoefficient().add(bt.getCoefficient()));
                        find = 1;
                        if (bt2.getCoefficient().equals(new BigInteger("0"))) {
                            result.remove(bt2);
                        }
                        break;
                    }
                }
                if (find == 0 && !bt.getCoefficient().equals(BigInteger.ZERO)) {
                    result.add(bt);
                }
            }
        }
        if (result.size() != 0 && result.get(0).getCoefficient().equals(new BigInteger("0"))) {
            result.remove(0);
        }
        Collections.sort(result);
        return result;
    }
}
