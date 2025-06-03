package task;

public class SelfDefine {
    private String expression;
    private String name;
    private String[] formal;

    public String getName() {
        return this.name;
    }

    public SelfDefine() {

    }

    public SelfDefine(String input) {
        String[] strings = input.split("=");
        this.name = Character.toString(input.charAt(0));
        this.expression = strings[1];

        Formatchange formatchange = new Formatchange();
        this.expression = formatchange.formatechange(expression);

        int start = 0;
        int end = 2;
        for (int i = 0; i < strings[0].length(); i++) {
            if (strings[0].charAt(i) == '(') {
                start = i;
            }
            else if (input.charAt(i) == ')') {
                end = i;
                break;
            }
        }
        formal = strings[0].substring(start + 1, end).split(",");
        for (int i = 0; i < formal.length; i++) {
            formal[i] = formal[i].trim();
            if (formal[i].equals("x")) {
                expression = expression.replaceAll("x", "m");
                formal[i] = "m";
            }
        }
    }

    public String operation(String string1, String string2, String string3) {
        String string = expression;
        string = string.replaceAll(formal[0], "(" + string1 + ")");
        if (!string2.equals("")) {
            string = string.replaceAll(formal[1], "(" + string2 + ")");
        }
        if (!string3.equals("")) {
            string = string.replaceAll(formal[2], "(" + string3 + ")");
        }
        return string;
    }
}
