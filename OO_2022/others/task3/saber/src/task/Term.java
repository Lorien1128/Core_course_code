package task;

import java.math.BigInteger;
import java.util.ArrayList;

public class Term extends Parser {
    public ArrayList<Divisor> getDivisors() {
        return divisors;
    }

    private ArrayList<Divisor> divisors = new ArrayList<>();

    public ArrayList<BigInteger> getIndex() {
        return index;
    }

    private ArrayList<BigInteger> index = new ArrayList<>();

    public BigInteger getCofficient() {
        return cofficient;
    }

    public void setCofficient(BigInteger cofficient) {
        this.cofficient = cofficient;
    }

    private BigInteger cofficient = new BigInteger("1");
    private String string;

    public Term(String s) {
        this.string = s;
    }

    public String getResult() {
        String data = new String();
        int flag = 0;
        ArrayList<Integer> locat = new ArrayList<>();
        findAddMult(this.string, locat, '*');
        divisor(locat, divisors, this.string);//getdivisor
        for (Divisor divisor : divisors) {
            String adivisor = divisor.getResult();
            if (flag == 0) {
                data = adivisor;
            }
            else {
                data = (new Mult(data, adivisor)).getResult();
            }
            flag = 1;
        }
        return data;
    }

    public void divisor(ArrayList<Integer> locat, ArrayList<Divisor> element, String expression) {
        element.removeAll(element);
        if (locat.size() == 0) {
            element.add(new Divisor(expression));
        }
        else {
            element.add(new Divisor(expression.substring(0, locat.get(0))));
            for (int i = 0; i < locat.size(); i++) {
                if (i == locat.size() - 1) {
                    element.add(new Divisor(expression.substring(locat.get(i) + 1)));
                }
                else {
                    String tempString = expression.substring(locat.get(i) + 1, locat.get(i + 1));
                    element.add(new Divisor(tempString));
                }
            }
        }
    }

    public void simplifyTerm(int type) {
        Divisor divisori;
        Divisor divisorj;
        ArrayList<Integer> locat = new ArrayList<>();
        findAddMult(this.string, locat, '*');
        divisor(locat, divisors, this.string);//getdivisor
        for (Divisor divisor : divisors) {
            divisor.simplifyDivisor(type);//化简因子
            index.add(divisor.getIndex());
        }
        if (divisors.get(0).getString().charAt(0) == '-') {
            cofficient = cofficient.multiply(new BigInteger("-1"));
            String tempString = divisors.get(0).getString().substring(1);
            divisors.get(0).setString(tempString);
        }
        //合并因子
        for (int i = 0; i < divisors.size(); i++) {
            divisori = divisors.get(i);
            if (divisori.getIsconstant() == 1) {
                cofficient = cofficient.multiply(new BigInteger(divisori.getString()));
                divisors.remove(i);
                index.remove(i);
                i--;
            }
            else {
                for (int j = i + 1; j < divisors.size(); j++) {
                    divisorj = divisors.get(j);
                    if (divisori.getString().equals(divisorj.getString())) {
                        index.set(i, index.get(i).add(index.get(j)));
                        divisors.remove(j);
                        index.remove(j);
                        j--;
                    }
                }
            }
        }
    }
}
