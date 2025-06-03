import java.util.HashMap;

public class Expression {
    public Standard divide(String exp, HashMap<Character, Function> functions) {
        //System.out.println(exp);
        int position = -1;
        int inKuoHao = 0;
        for (int i = 0; i < exp.length(); i++) {
            if ((exp.charAt(i) == '+'
                || exp.charAt(i) == '-')
                && inKuoHao == 0
                && (i == 0 || (exp.charAt(i - 1) != '*'
                && exp.charAt(i - 1) != '+')
                && exp.charAt(i - 1) != '-')) {
                position = i;
            } else if (exp.charAt(i) == '(') {
                inKuoHao++;
            } else if (exp.charAt(i) == ')') {
                inKuoHao--;
            }
        }
        if (position != -1) {
            return divide(exp.substring(0, position), functions).addOrSub(
                    divide(exp.substring(position + 1), functions), exp.charAt(position));
        }
        Xiang xiang = new Xiang();
        return xiang.divide(exp, functions);
    }
}
