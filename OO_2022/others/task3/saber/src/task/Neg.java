package task;

public class Neg {
    private String op;

    public Neg(String op) {
        this.op = op;
    }

    //public static void main(String[] args) {
    //    Scanner scanner = new Scanner(System.in);
    public static String setNeg(String neg) {
        int j;
        int bracket = 0;
        String string; //= scanner.nextLine();
        StringBuilder result = new StringBuilder();
        string = neg;
        if (string.charAt(0) != '-') {
            result.append("-" + string.charAt(0));
        }
        for (j = 1; j < string.length(); j++) {
            char m = string.charAt(j);
            if (m == '(') {
                bracket++;
            }
            else if (m == ')') {
                bracket--;
            }
            if (bracket == 0) {
                if (string.charAt(j) == '+' && string.charAt(j + 1) != '-') {
                    result.append("+-");
                } else if (string.charAt(j) == '+' && string.charAt(j + 1) == '-') {
                    result.append("+");
                    j++;
                }
                else {
                    result.append(m);
                }
            }
            else {
                result.append(m);
            }
        }
        //System.out.println(result);
        return result.toString();
    }

    public String getResult() {
        return setNeg(this.op);
    }
}
