import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TriAngle {
    private boolean sin;
    private BigInteger index;
    private ExprClass content;

    private final Pattern triAnglePattern = Pattern
            .compile("^(sin|cos)\\s*(\\()(.+)(\\))(\\s*\\*{2}\\s*\\+?(\\d+))?$");

    public boolean isSin() {
        return sin;
    }

    public BigInteger getIndex() {
        return index;
    }

    public ExprClass getContent() {
        return content;
    }

    public void setSin(boolean sin) {
        this.sin = sin;
    }

    public void setIndex(BigInteger index) {
        this.index = index;
    }

    public void setContent(ExprClass content) {
        this.content = content;
    }

    public boolean isZero() {
        return this.isSin() &&
                new ExprClass().equal(this.getContent(), new ExprClass());
    }

    public boolean equal(TriAngle triAngle1, TriAngle triAngle2) {
        if (triAngle1.isSin() == triAngle2.isSin() &&
                triAngle1.getIndex().equals(triAngle2.getIndex()) &&
                new ExprClass().equal(triAngle1.getContent(),
                        triAngle2.getContent())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean similar(TriAngle triAngle1, TriAngle triAngle2) {
        if (triAngle1.isSin() == triAngle2.isSin() &&
                new ExprClass().equal(triAngle1.getContent(),
                        triAngle2.getContent())) {
            return true;
        } else {
            return false;
        }
    }

    public int couple(TriAngle triAngle1, TriAngle triAngle2) {
        if (triAngle1.isSin() != triAngle2.isSin() &&
                triAngle1.getIndex().equals(BigInteger.valueOf(2)) &&
                triAngle2.getIndex().equals(BigInteger.valueOf(2)) &&
                new ExprClass().equal(triAngle1.getContent(), triAngle2.getContent())) {
            return 1;
        } else {
            return 0;
        }
    }

    public ExprClass triAngle(String str, ArrayList<Function> functions) {
        Matcher triAngleMatcher = triAnglePattern.matcher(str);
        if (!triAngleMatcher.find()) {
            throw new RuntimeException("TriAngle input error!");
        }
        TermClass term = new TermClass();
        term.setCoefficient(BigInteger.ONE);
        term.setIndex(BigInteger.ZERO);
        TriAngle triAngle = new TriAngle();
        triAngle.setSin(triAngleMatcher.group(1).equals("sin"));
        String content = triAngleMatcher.group(3).trim();
        ExprClass exprTemp = new ExprClass().expr(content,functions);
        new Compute().exprSimplify(exprTemp);
        triAngle.setContent(exprTemp);
        if (triAngleMatcher.group(5) == null) {
            triAngle.setIndex(BigInteger.ONE);
        } else {
            triAngle.setIndex(new BigInteger(triAngleMatcher.group(6)));
        }
        term.getTriAngles().add(triAngle);
        ExprClass result = new ExprClass();
        result.getTermClasses().add(term);
        return result;
    }
}
