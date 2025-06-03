package task;

import java.util.ArrayList;

public class Mult extends Parser {
    private String left;

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    private String right;

    public Mult(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String mult(ArrayList<String> op1, ArrayList<String> op2) {
        ArrayList<String> result = new ArrayList<>();
        for (String j : op1) {
            j = j.trim();
        }
        for (String j : op2) {
            j = j.trim();
        }
        int sub;
        String tempString1;
        String tempString2;
        for (String i : op1) {
            for (String j : op2) {
                sub = 0;
                if (i.charAt(0) == '-') {
                    sub++;
                    tempString1 = i.substring(1);
                }
                else {
                    tempString1 = i;
                }
                if (j.charAt(0) == '-') {
                    sub++;
                    tempString2 = j.substring(1);
                }
                else {
                    tempString2 = j;
                }
                if ((sub & 1) == 1) {
                    result.add("-" + tempString1 + "*" + tempString2 + "+");
                }
                else {
                    result.add(tempString1 + "*" + tempString2 + "+");
                }
            }
        }

        StringBuilder output = new StringBuilder();
        for (int j = 0; j < result.size(); j++) {
            output.append(result.get(j));
        }
        Parser parser = new Parser();
        return (parser.paserSimplify(output.substring(0, output.length() - 1), 0));
        //防止内存爆炸
    }

    public String getResult() {
        ArrayList<Integer> locat1 = new ArrayList<>();
        ArrayList<Integer> locat2 = new ArrayList<>();
        findAddMult(left, locat1, '+');
        findAddMult(right, locat2,'+');
        ArrayList<String> op1 = new ArrayList<>();
        ArrayList<String> op2 = new ArrayList<>();
        getData(locat1, op1, left);
        getData(locat2, op2, right);
        return mult(op1, op2);
    }

    public void getData(ArrayList<Integer> locat, ArrayList<String> element, String expression) {
        element.removeAll(element);
        if (locat.size() == 0) {
            element.add(expression);
        }
        else {
            int temp = 0;
            for (int i = 0; i < locat.size() + 1; i++) {
                if (i < locat.size()) {
                    temp = locat.get(i);
                }
                if (i == 0) {
                    element.add(expression.substring(0, temp));
                }
                else if (i < locat.size()) {
                    element.add(expression.substring(locat.get(i - 1) + 1, temp));
                }
                else {
                    element.add(expression.substring(temp + 1));
                }
            }
        }
    }
}
