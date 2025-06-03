package task;

import java.math.BigInteger;
import java.util.ArrayList;

public class Parser {
    public String parser(String expression) {
        int flag = 0;
        ArrayList<Integer> locat = new ArrayList<>();
        ArrayList<Term> terms = new ArrayList<>();
        //findAdd
        findAddMult(expression, locat, '+');
        getElement(locat, terms, expression);
        StringBuilder result = new StringBuilder();
        int m = 0;
        for (Term term : terms) {
            if (flag == 1) {
                result.append("+");
            }
            m++;
            result.append(term.getResult());
            flag = 1;
        }
        return result.toString();
    }

    public void findAddMult(String expression, ArrayList<Integer> locat, char oper) {
        int bracket = 0;
        locat.removeAll(locat);
        char m;
        for (int i = 0; i < expression.length(); i++) {
            m = expression.charAt(i);
            if (m == '(') {
                bracket++;
            }
            else if (m == ')') {
                bracket--;
            }
            else if (bracket == 0 && m == oper) {
                if (oper == '+') {
                    locat.add(i);
                }
                else if (expression.charAt(i - 1) != '*' &&
                        expression.charAt(i + 1) != '*') {
                    locat.add(i);
                }
            }
        }
    }

    public void getElement(ArrayList<Integer> locat, ArrayList<Term> element, String expression) {
        element.removeAll(element);
        if (locat.size() == 0) {
            element.add(new Term(expression));
        }
        else {
            element.add(new Term(expression.substring(0, locat.get(0))));
            for (int i = 0; i < locat.size(); i++) {
                if (i == locat.size() - 1) {
                    element.add(new Term(expression.substring(locat.get(i) + 1)));
                }
                else {
                    element.add(new Term(expression.substring(locat.get(i) + 1, locat.get(i + 1))));
                }
            }
        }
    }

    public String paserSimplify(String expression, int type) {
        ArrayList<Integer> locat = new ArrayList<>();
        ArrayList<Term> terms = new ArrayList<>();
        //findAdd
        findAddMult(expression, locat, '+');
        getElement(locat, terms, expression);
        for (Term term : terms) {
            term.simplifyTerm(type);//化简项
        }
        //合并项
        Term termi;
        Term termj;
        for (int i = 0; i < terms.size(); i++) {
            termi = terms.get(i);
            for (int j = i + 1; j < terms.size(); j++) {
                termj = terms.get(j);
                if (compareTerm(termi, termj)) {
                    BigInteger op1;
                    BigInteger op2;
                    op1 = terms.get(i).getCofficient();
                    op2 = terms.get(j).getCofficient();
                    op1 = op1.add(op2);
                    terms.get(i).setCofficient(op1);
                    terms.remove(j);
                    j--;
                }
            }
        }
        //输出
        return getfinalExpression(terms, type);
    }

    public String getfinalExpression(ArrayList<Term> terms, int type) {
        StringBuilder result = new StringBuilder();
        ArrayList<Divisor> divisors;
        ArrayList<BigInteger> index;
        BigInteger coefficient;
        BigInteger temp = new BigInteger("0");
        BigInteger temp1 = new BigInteger("1");
        BigInteger tempminus1 = new BigInteger("-1");
        int flag;
        int mark = 0;
        changeFirst(terms);
        for (int i = 0; i < terms.size(); i++) {
            flag = 0;
            divisors = terms.get(i).getDivisors();
            index = terms.get(i).getIndex();
            coefficient = terms.get(i).getCofficient();
            //系数非0则加入表达式中
            if (!coefficient.equals(temp)) {
                if (mark == 1 && coefficient.compareTo(temp) > 0 && type == 1) {
                    result.append("+"); //系数大于0且为中间项时，以+相连，否则直接用系数中的负号即可
                }
                else if (mark == 1 && type == 0) {
                    result.append("+");
                }
                if (divisors.size() == 0) {
                    result.append(coefficient); //为常数项时，直接将系数加入到表达式
                }
                else { //非常数项
                    if (coefficient.equals(tempminus1)) {
                        result.append("-"); //系数为-1时特判
                    } else if (!coefficient.equals(temp1)) {
                        result.append(coefficient); //系数非1，-1时直接作为该项的第一个因子
                        flag = 1; //表明该项已有第一个因子
                    }
                    for (int j = 0; j < divisors.size(); j++) {
                        if (j == 0 && flag == 1) {
                            result.append("*"); //有第一个常数因子了，当系数为-1或1时flag == 0;
                        } else if (j != 0) {
                            result.append("*");
                        }
                        result.append(divisors.get(j).getString());
                        boolean judge = (terms.size() > 1 || divisors.size() > 1
                                || !terms.get(i).getCofficient().equals(temp1));
                        if (divisors.get(j).getString().equals("x") && judge
                                && index.get(j).equals(new BigInteger("2"))) { //
                            result.append("*x");
                        }
                        else if (!index.get(j).equals(new BigInteger("1"))) { //指数大于1时需添加指数表示
                            result.append("**");
                            result.append(index.get(j));
                        }
                    }
                }
                mark = 1;
            }
        }
        if (result.toString().equals("")) {
            result.append("0");
        }
        return result.toString();
    }

    public void changeFirst(ArrayList<Term> terms) {
        BigInteger temp = new BigInteger("0");
        if (terms.get(0).getCofficient().compareTo(temp) < 0) {
            for (int i = 1; i < terms.size(); i++) {
                if (terms.get(i).getCofficient().compareTo(temp) > 0) {
                    Term term0 = terms.get(0);
                    Term termi = terms.get(i);
                    terms.set(0, termi);
                    terms.set(i, term0);
                    return;
                }
            }
        }
    }

    public boolean compareTerm(Term termi, Term termj) {
        ArrayList<Divisor> divisorsi;
        ArrayList<Divisor> divisorsj;
        divisorsi = termi.getDivisors();
        divisorsj = termj.getDivisors();
        ArrayList<BigInteger> indexi;
        ArrayList<BigInteger> indexj;
        indexi = termi.getIndex();
        indexj = termj.getIndex();
        int flag;
        if (divisorsi.size() != divisorsj.size()) {
            return false;
        }
        for (int i = 0; i < divisorsi.size(); i++) {
            flag = 0;
            for (int j = 0; j < divisorsj.size(); j++) {
                if (divisorsi.get(i).getString().equals(divisorsj.get(j).getString()) &&
                        indexi.get(i).equals(indexj.get(j))) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                return false;
            }
        }
        return true;
    }
}
