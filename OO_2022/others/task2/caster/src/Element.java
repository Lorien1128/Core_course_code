import java.util.HashMap;

public class Element {
    public Standard divide(String ele, HashMap<Character, Function> functions) {
        //System.out.println(ele);
        if (!ele.equals("") && ele.charAt(0) == '(' && ele.charAt(ele.length() - 1) == ')') {
            Expression expression = new Expression();
            return expression.divide(ele.substring(1, ele.length() - 1), functions);
        }
        int position = -1;
        int inKuoHao = !ele.equals("") && ele.charAt(0) == '(' ? 1 : 0;
        for (int i = 1; i < ele.length(); i++) {
            if (ele.charAt(i) == '*'
                    && ele.charAt(i - 1) == '*'
                    && inKuoHao == 0) {
                position = i;
            } else if (ele.charAt(i) == '(') {
                inKuoHao++;
            } else if (ele.charAt(i) == ')') {
                inKuoHao--;
            }
        }
        if (position != -1) {
            Standard one = divide(ele.substring(0, position - 1), functions);
            Standard ans = new Standard("1");
            for (int i = 1; i <= Integer.parseInt(ele.substring(position + 1)); i++) {
                ans = ans.multiply(one);
                //System.out.println("hint:" + ans.toString());
            }
            return ans;
        }
        if (ele.startsWith("sum")) {
            String[] strings = ele.substring(4, ele.length() - 1).split(",");
            Standard ans = new Standard("");
            for (int i = Integer.parseInt(strings[1]); i <= Integer.parseInt(strings[2]); i++) {
                String replacedString = strings[3].replace(
                        "sin", "son").replace(
                                strings[0], String.valueOf(i)).replace(
                                        "son", "sin");
                ans.addOrSub(new Expression().divide(replacedString, functions), '+');
            }
            return ans;
        }
        if (ele.startsWith("f")
            || ele.startsWith("g")
            || ele.startsWith("h")) {
            String[] strings = ele.substring(2, ele.length() - 1).split(",");
            return new Expression().divide(
                    functions.get(ele.charAt(0)).getFunction(strings), functions);
        }
        return new Standard(ele);
    }
}
