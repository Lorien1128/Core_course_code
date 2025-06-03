import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePoly {
    private BasePoly subBasePoly;
    private int type;
    private BaseTerm baseTerm;

    public BasePoly(String term) {
        if (term.equals("")) {
            System.exit(0);
        }
        String str = Operation.preProcessOffindSubpoly(term);
        if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') {
            str = str.replaceAll("[\\(\\)]","");
        }
        str = str.replaceAll("@", "(");
        str = str.replaceAll("#", ")");
        String pattern1 = "(.*[^\\*\\(])([\\+\\-])(.+)";
        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(str);
        if (m1.matches()) {
            if (m1.group(1).equals("")) {
                this.subBasePoly = null;
            } else {
                this.subBasePoly = new BasePoly(m1.group(1));
            }
            if (m1.group(2).equals("+")) {
                this.type = 1;
            } else {
                this.type = 2;
            }
            this.baseTerm = new BaseTerm(m1.group(3));
        } else {
            this.subBasePoly = null;
            if (str.charAt(0) == '+') {
                this.type = 1;
                str = str.replaceFirst("\\+", "");
                this.baseTerm = new BaseTerm(str);
            } else if (str.charAt(0) == '-') {
                this.type = 2;
                str = str.replaceFirst("\\-", "");
                this.baseTerm = new BaseTerm(str);
            } else {
                this.type = 1;
                this.baseTerm = new BaseTerm(str);
            }
        }
    }

    public ArrayList<BaseTerm> calculate() {
        ArrayList<BaseTerm> baseTermArrayList = new ArrayList<>();
        if (this.subBasePoly == null) {
            if (this.type == 2) {
                this.baseTerm.setCoefficient(
                        new BigInteger("0").subtract(this.baseTerm.getCoefficient()));
            }
            baseTermArrayList.add(this.baseTerm);
        }
        else {
            baseTermArrayList = this.subBasePoly.calculate();
            int find = 0;
            for (BaseTerm bt : baseTermArrayList) {
                if (bt.same(this.baseTerm)) {
                    if (this.type == 1) {
                        bt.setCoefficient(bt.getCoefficient().add(this.baseTerm.getCoefficient()));
                    } else {
                        bt.setCoefficient(
                                bt.getCoefficient().subtract(this.baseTerm.getCoefficient()));
                    }
                    find = 1;
                    break;
                }
            }
            if (find == 0) {
                BaseTerm bt = this.baseTerm;
                if (this.type == 1) {
                    bt.setCoefficient(bt.getCoefficient());
                } else {
                    bt.setCoefficient(new BigInteger("0").subtract(this.baseTerm.getCoefficient()));
                }
                baseTermArrayList.add(bt);
                Collections.sort(baseTermArrayList);
            }
        }
        return baseTermArrayList;
    }
}
