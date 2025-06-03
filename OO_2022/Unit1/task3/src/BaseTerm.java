import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseTerm implements Comparable<BaseTerm>, Cloneable {
    private BigInteger coefficient;
    private int power;
    private ArrayList<TrigoFunction> sinfunclist = new ArrayList<>();
    private ArrayList<TrigoFunction> cosfunclist = new ArrayList<>();

    @Override
    public BaseTerm clone() {
        try {
            BaseTerm baseTerm =  (BaseTerm) super.clone();
            ArrayList<TrigoFunction> sintr = new ArrayList<>();
            for (TrigoFunction tf : sinfunclist) {
                TrigoFunction temp = tf.clone();
                sintr.add(temp);
            }
            baseTerm.sinfunclist = sintr;
            ArrayList<TrigoFunction> costr = new ArrayList<>();
            for (TrigoFunction tf : cosfunclist) {
                TrigoFunction temp = tf.clone();
                costr.add(temp);
            }
            baseTerm.cosfunclist = costr;
            return baseTerm;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BaseTerm(String baseTerm) {
        if (baseTerm == null) {
            this.coefficient = BigInteger.ZERO;
            this.power = 0;
        }
        else {
            String str = Operation.preProcessOffindSubpoly(baseTerm);
            Matcher m3 = Pattern.compile("(sin|cos)@(.*?)#").matcher(str);
            while (m3.find()) {
                TrigoFunction f = new TrigoFunction(m3.group(2));
                if (m3.group(1).equals("sin")) {
                    this.sinfunclist.add(f);
                } else {
                    this.cosfunclist.add(f);
                }
            }
            str = str.replaceAll("(sin|cos)@(.*?)#", "");
            BigInteger coeff = BigInteger.ONE;
            int pow = 0;
            String pattern1 = "[\\+\\-]?\\d+";
            Pattern p1 = Pattern.compile(pattern1);
            Matcher m1 = p1.matcher(str);
            while (m1.find()) {
                coeff = coeff.multiply(new BigInteger(m1.group()));
            }
            this.coefficient = coeff;
            String pattern2 = "x";
            Pattern p2 = Pattern.compile(pattern2);
            Matcher m2 = p2.matcher(str);
            while (m2.find()) {
                pow = pow + 1;
            }
            this.power = pow;
            for (TrigoFunction sin : sinfunclist) {
                String sign;
                if (sin.getCoefficient().signum() != -1) {
                    sign = "1";
                } else {
                    sign = "-1";
                    sin.setCoefficient(sin.getCoefficient().negate());
                }
                coefficient  = coefficient.multiply(new BigInteger(sign));
            }
            for (TrigoFunction cos : cosfunclist) {
                if (cos.getCoefficient().signum() == -1) {
                    cos.setCoefficient(cos.getCoefficient().negate());
                }
            }
            this.sinfunclist = combineTrigo(this.sinfunclist);
            this.cosfunclist = combineTrigo(this.cosfunclist);
        }
    }

    public int compareTo(BaseTerm other) {
        if (this.power < other.power) {
            return -1;
        }
        else if (this.power > other.power) {
            return 1;
        }
        else {
            return this.coefficient.subtract(other.coefficient).signum();
        }
    }

    public int getPower() {
        return this.power;
    }

    public BigInteger getCoefficient() {
        return this.coefficient;
    }

    public void setCoefficient(BigInteger coefficient) {
        this.coefficient = coefficient;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setCosfunclist(ArrayList<TrigoFunction> cosfunclist) {
        this.cosfunclist = cosfunclist;
    }

    public void setSinfunclist(ArrayList<TrigoFunction> sinfunclist) {
        this.sinfunclist = sinfunclist;
    }

    public void multTrigofunclist(BaseTerm bt1, BaseTerm bt2) {
        this.sinfunclist.addAll(bt1.getSinfunclist());
        this.sinfunclist.addAll(bt2.getSinfunclist());
        this.cosfunclist.addAll(bt1.getCosfunclist());
        this.cosfunclist.addAll(bt2.getCosfunclist());
    }

    public ArrayList<TrigoFunction> getSinfunclist() {
        return sinfunclist;
    }

    public ArrayList<TrigoFunction> getCosfunclist() {
        return cosfunclist;
    }

    public boolean same(BaseTerm other) {
        if (this.power != other.getPower()) {
            return false;
        }
        if (this.sinfunclist.size() != other.sinfunclist.size()) {
            return false;
        }
        for (int i = 0; i < this.sinfunclist.size(); i++) {
            if (this.sinfunclist.get(i).compareTo(other.sinfunclist.get(i)) != 0 ||
                    this.sinfunclist.get(i).getOutsidePower() !=
                            other.sinfunclist.get(i).getOutsidePower()) {
                return false;
            }
        }
        if (this.cosfunclist.size() != other.cosfunclist.size()) {
            return false;
        }
        for (int i = 0; i < this.cosfunclist.size(); i++) {
            if (this.cosfunclist.get(i).compareTo(other.cosfunclist.get(i)) != 0 ||
                    this.cosfunclist.get(i).getOutsidePower() !=
                            other.cosfunclist.get(i).getOutsidePower()) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<TrigoFunction> test(ArrayList<TrigoFunction> trigoFunctions) {
        return trigoFunctions;
    }

    public static ArrayList<TrigoFunction> combineTrigo(ArrayList<TrigoFunction> functionslist) {
        ArrayList<TrigoFunction> trigoFunctions;
        trigoFunctions = test(functionslist);
        ArrayList<TrigoFunction> result = new ArrayList<>();
        if (trigoFunctions.size() != 0) {
            for (TrigoFunction f1 : trigoFunctions) {
                boolean find = false;
                for (TrigoFunction f2 : result) {
                    if (f1.compareTo(f2) == 0) {
                        find = true;
                        f2.setOutsidePower(f2.getOutsidePower() + f1.getOutsidePower());
                        break;
                    }
                }
                if (!find) {
                    TrigoFunction f = new TrigoFunction(null);
                    f.setOutsidePower(f1.getOutsidePower());
                    f.setCoefficient(f1.getCoefficient());
                    f.setInsidePower(f1.getInsidePower());
                    if (f.getOutsidePower() != 0) {
                        result.add(f);
                    }
                }
            }
            Collections.sort(result);
        }
        return result;
    }

    public String toString() {
        String str = "";
        if ((this.getCoefficient().equals(BigInteger.ONE) ||
                (this.getCoefficient().negate().equals(BigInteger.ONE))) &&
                (this.getPower() != 0 || sinfunclist.size() != 0 || cosfunclist.size() != 0)) {
            if (this.getCoefficient().negate().equals(BigInteger.ONE)) {
                str = str + "-";
            }
            if (power != 0) {
                str = str + "x";
            }
        } else {
            str = str + this.getCoefficient();
            if (this.getPower() != 0) {
                str = str + "*x";
            }
        }
        if (this.getPower() == 2) {
            str = str + "*x";
        }
        else if (this.getPower() > 2) {
            str = str + "**" + this.getPower();
        }
        for (TrigoFunction f : sinfunclist) {
            str = str + "*" + f.toStr("sin");
        }
        for (TrigoFunction f : cosfunclist) {
            str = str + "*" + f.toStr("cos");
        }
        if ((str.charAt(0) == '-' && str.charAt(1) == '*') || str.charAt(0) == '*') {
            str = str.replaceFirst("\\*", "");
        }
        return str;
    }
}
