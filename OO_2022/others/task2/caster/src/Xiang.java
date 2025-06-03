import java.util.HashMap;

public class Xiang {
    public Standard divide(String xiang, HashMap<Character, Function> functions) {
        //System.out.println(xiang);
        int position = -1;
        int inKuoHao = !xiang.equals("") && xiang.charAt(0) == '(' ? 1 : 0;
        for (int i = 1; i < xiang.length() - 1; i++) {
            if (xiang.charAt(i) == '*'
                    && xiang.charAt(i - 1) != '*'
                    && xiang.charAt(i + 1) != '*'
                    && inKuoHao == 0) {
                position = i;
            } else if (xiang.charAt(i) == '(') {
                inKuoHao++;
            } else if (xiang.charAt(i) == ')') {
                inKuoHao--;
            }
        }
        if (position != -1) {
            return divide(xiang.substring(0, position), functions).multiply(
                    divide(xiang.substring(position + 1), functions));
        }
        Element element = new Element();
        return element.divide(xiang, functions);
    }
}
