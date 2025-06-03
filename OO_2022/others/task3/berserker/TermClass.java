import java.math.BigInteger;
import java.util.ArrayList;

public class TermClass {
    private BigInteger coefficient;
    private BigInteger index;
    private ArrayList<TriAngle> triAngles;

    public BigInteger getCoefficient() {
        return coefficient;
    }

    public BigInteger getIndex() {
        return index;
    }

    public ArrayList<TriAngle> getTriAngles() {
        return triAngles;
    }

    public void setCoefficient(BigInteger coefficient) {
        this.coefficient = coefficient;
    }

    public void setIndex(BigInteger index) {
        this.index = index;
    }

    public boolean isZero() {
        boolean flag = false;
        if (this.coefficient.equals(BigInteger.ZERO)) {
            flag = true;
        }
        for (TriAngle triAngle : this.triAngles) {
            if (triAngle.isZero()) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean isFactor() {
        int isOne1 = 0;
        int isOne2 = 0;
        int isOne3 = 0;
        if (this.coefficient.equals(BigInteger.ONE) ||
            this.coefficient.equals(BigInteger.valueOf(-1))) {
            isOne1 = 1;
        }
        if (this.index.equals(BigInteger.ZERO)) {
            isOne2 = 1;
        }
        if (this.triAngles.size() == 0) {
            isOne3 = 1;
        } else if (this.triAngles.size() == 1) {
            isOne3 = 0;
        } else {
            isOne3 = -1;
        }
        int isOne = isOne1 + isOne2 + isOne3;
        if (isOne >= 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String result = "";
        if (this.isZero()) {
            return "";
        } else {
            if (this.coefficient.equals(BigInteger.valueOf(-1))) {
                result = result.concat("-");
            } else if (this.coefficient.equals(BigInteger.ONE)) {
                result = result.concat("+");
            } else {
                if (this.coefficient.compareTo(BigInteger.ZERO) > 0) {
                    result = result.concat("+");
                }
                result = result.concat(this.coefficient + "*");
            }
            if (this.index.equals(BigInteger.ZERO)) {
                result = result.concat("");
            } else if (this.index.equals(BigInteger.ONE)) {
                result = result.concat("x*");
            } else {
                result = result.concat("x**" + this.index + "*");
            }
            for (TriAngle triAngle : this.triAngles) {
                if (triAngle.isSin()) {
                    result = result.concat("sin(");
                } else {
                    result = result.concat("cos(");
                }
                int isFactor = 0;
                if (triAngle.getContent().getTermClasses().size() == 0) {
                    isFactor = 1;
                } else if (triAngle.getContent().getTermClasses().size() == 1) {
                    if (triAngle.getContent().getTermClasses().get(0).isFactor()) {
                        isFactor = 1;
                    }
                }
                if (isFactor == 0) {
                    result = result.concat("(");
                }
                result = result.concat(triAngle.getContent() + ")");
                if (isFactor == 0) {
                    result = result.concat(")");
                }
                if (!triAngle.getIndex().equals(BigInteger.ONE)) {
                    result = result.concat("**" + triAngle.getIndex());
                }
                result = result.concat("*");
            }
            if (result.length() > 0 && result.charAt(result.length() - 1) == '*') {
                result = result.substring(0, result.length() - 1);
            }
            if (result.equals("+")) {
                return "+1";
            } else if (result.equals("-")) {
                return "-1";
            } else {
                return result;
            }
        }
    }

    public boolean similar(TermClass term1, TermClass term2) {
        if (term1.getIndex().equals(term2.getIndex())) {
            int cnt = term1.getTriAngles().size();
            if (cnt == term2.getTriAngles().size()) {
                int num = 0;
                for (int i = 0; i < cnt; i++) {
                    for (int j = 0; j < cnt; j++) {
                        if (new TriAngle().equal(term1.getTriAngles().get(i),
                                term2.getTriAngles().get(j))) {
                            num++;
                        }
                    }
                }
                if (num == cnt) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean equal(TermClass term1, TermClass term2) {
        return (new TermClass().similar(term1, term2)) &&
                (term1.getCoefficient().equals(term2.getCoefficient()));
    }

    public TermClass() {
        this.coefficient = BigInteger.ONE;
        this.index = BigInteger.ZERO;
        this.triAngles = new ArrayList<>();
    }

    public void triAngleAdd(TriAngle triAngle) {
        TriAngle addTriAngle = new TriAngle();
        addTriAngle.setSin(triAngle.isSin());
        addTriAngle.setContent(triAngle.getContent());
        addTriAngle.setIndex(triAngle.getIndex());
        this.getTriAngles().add(addTriAngle);
    }

    public void termSimplify() {
        for (int i = getTriAngles().size() - 1; i >= 0; i--) {
            int isOne = 0;
            if ((!getTriAngles().get(i).isSin()) &&
                    getTriAngles().get(i).getContent().toString().equals("0")) {
                isOne = 1;
            } else if (getTriAngles().get(i).getIndex().equals(BigInteger.ZERO)) {
                isOne = 1;
            }
            if (isOne == 1) {
                getTriAngles().remove(i);
            }
        }

        int cnt = getTriAngles().size();
        for (int i = cnt - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (new TriAngle().similar(getTriAngles().get(i),
                        getTriAngles().get(j))) {
                    BigInteger index0 = BigInteger.ZERO
                            .add(getTriAngles().get(i).getIndex());
                    BigInteger index = index0.add(getTriAngles().get(j).getIndex());
                    getTriAngles().get(j).setIndex(index);
                    getTriAngles().remove(i);
                    break;
                }
            }
        }
    }

    public ExprClass term(String str, ArrayList<Function> functions) {
        int sign = 1;
        int pos = 0;
        if (str.charAt(0) == '-') {
            sign = -1;
            pos++;
        } else if (str.charAt(0) == '+') {
            pos++;
        }
        int posMul = findMul(str, pos);
        ExprClass temp = new ExprClass();
        if (posMul == -1) {
            temp = new FactorClass().factor(str.substring(pos).trim(), functions);
        } else {
            temp = new Compute().exprMul(new FactorClass()
                            .factor(str.substring(pos, posMul).trim(), functions),
                    new TermClass().term(str.substring(posMul + 1).trim(), functions));
        }
        if (sign == 1) {
            return temp;
        } else {
            return new Compute().exprSub(new ExprClass(), temp);
        }
    }

    public int findMul(String str, int start) {
        int result = -1;
        int flag = 0;
        for (int pos = start; pos < str.length(); pos++) {
            if (str.charAt(pos) == '(') {
                flag++;
            } else if (str.charAt(pos) == ')') {
                if (flag > 0) {
                    flag--;
                } else {
                    throw new RuntimeException("Wrong Format!");
                }
            } else if (flag == 0) {
                if (str.charAt(pos) == '*') {
                    if (str.charAt(pos + 1) != '*' &&
                            str.charAt(pos - 1) != '*') {
                        result = pos;
                        break;
                    }
                }
            }
        }
        return result;
    }
}


