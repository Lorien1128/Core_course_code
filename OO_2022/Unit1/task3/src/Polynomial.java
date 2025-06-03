import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private Polynomial subPolynomial;
    private int type;
    private Term term;

    public Polynomial(String rawPolynomial) {
        String pattern1 = "(.*)([@#])(.+)";
        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(rawPolynomial);
        if (m1.matches()) {
            if (m1.group(1).equals("")) {
                this.subPolynomial = null;
            } else {
                this.subPolynomial = new Polynomial(m1.group(1));
            }
            if (m1.group(2).equals("@")) {
                this.type = 1;
            } else {
                this.type = 2;
            }
            this.term = new Term(m1.group(3));
        } else {
            this.subPolynomial = null;
            this.type = 1;
            this.term = new Term(rawPolynomial);
        }
    }

    public ArrayList<BaseTerm> calculate() {
        ArrayList<BaseTerm> array;
        ArrayList<BaseTerm> array2 = this.term.calculate();
        if (this.type == 2) {
            for (BaseTerm bt : array2) {
                bt.setCoefficient(new BigInteger("0").subtract(bt.getCoefficient()));
            }
        }
        if (this.subPolynomial == null) {
            array = array2;
        }
        else {
            array = this.subPolynomial.calculate();
            array.addAll(array2);
        }
        array = Term.combine(array);
        return array;
    }
}
