import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
    private String name;
    private ArrayList<String> parameter;
    private String expression;

    public String getName() {
        return name;
    }

    public ArrayList<String> getParameter() {
        return parameter;
    }

    public String getExpression() {
        return expression;
    }

    private final Pattern funcPattern = Pattern
            .compile("^([fgh])\\s*\\(\\s*([xyz])\\s*" +
                    "(,\\s*([xyz])\\s*(,\\s*([xyz])\\s*)?)?\\)" +
                    "\\s*=\\s*(.+)$");

    public Function() {
        this.name = null;
        this.parameter = new ArrayList<>();
        this.expression = null;
    }

    public Function(String str) {
        Matcher funcMatcher = funcPattern.matcher(str);
        if (!funcMatcher.find()) {
            throw new RuntimeException("Function input error!");
        }
        this.name = funcMatcher.group(1);
        this.parameter = new ArrayList<>();
        this.parameter.add(funcMatcher.group(2).trim());
        if (funcMatcher.group(3) != null) {
            this.parameter.add(funcMatcher.group(4).trim());
            if (funcMatcher.group(5) != null) {
                this.parameter.add(funcMatcher.group(6).trim());
            }
        }
        this.expression = funcMatcher.group(7).trim();
    }

    public ExprClass func(String str, ArrayList<Function> functions) {
        String name = str.charAt(0) + "";
        Function function = null;
        for (Function temp : functions) {
            if (temp.getName().equals(name)) {
                function = temp;
            }
        }
        if (function == null) {
            throw new RuntimeException("Wrong function use!");
        }
        ArrayList<String> parameters = new ArrayList<>();
        int layer = 0;
        int start = -1;
        for (int i = 0; i < str.length(); i++) {
            int flag = 0;
            if (str.charAt(i) == '(') {
                layer++;
                if (layer == 1) {
                    start = i;
                }
            } else if (str.charAt(i) == ')') {
                layer--;
                if (layer == 0) {
                    flag = 1;
                }
            } else if (str.charAt(i) == ',') {
                if (layer == 1) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                parameters.add(str.substring(start + 1, i).trim());
                start = i;
            }
        }
        String result = new Function().substitute(function.getExpression().trim(),
                function.getParameter(), parameters, functions);
        return new ExprClass().expr(result.trim(), functions);
    }

    public String substitute(String str, ArrayList<String> para1, ArrayList<String> para2,
                             ArrayList<Function> functions) {
        String result = str;
        int cnt = para1.size();
        for (int i = 0; i < result.length(); i++) {
            for (int j = 0; j < cnt; j++) {
                if (result.substring(i, i + 1).equals(para1.get(j))) {
                    String sub = para2.get(j);
                    result = result.substring(0, i) +
                            "(" + sub + ")" +
                            result.substring(i + 1);
                    i += sub.length() + 1;
                    break;
                }
            }
        }
        /*int flag = 0;
        int layer = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.startsWith("sin", i) ||
                    result.startsWith("cos", i)) {
                flag = 1;
            }
            if (flag == 1) {
                if (result.charAt(i) == '(') {
                    if (layer == 0) {
                        start = i;
                    }
                    layer++;
                } else if (result.charAt(i) == ')') {
                    if (layer == 1) {
                        end = i;
                        String former = result.substring(start + 1, end);
                        String sub = new ExprClass().expr(former, functions).toString();
                        result = result.substring(0, start + 1) + sub + result.substring(end);
                        i = sub.length() - former.length() + end;
                        flag = 0;
                    }
                    layer--;
                }
            }
        }*/
        return result;
    }
}

