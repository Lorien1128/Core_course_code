import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
    private String expr;
    private final String name;
    private final ArrayList<String> parameter = new ArrayList<>();
    private static final Pattern PATTERN1 = Pattern.compile("[fgh]\\(([xyz])\\)=(.+)");
    private static final Pattern PATTERN2 = Pattern.compile("[fgh]\\(([xyz]),([xyz])\\)=(.+)");
    private static final Pattern PATTERN3 =
            Pattern.compile("[fgh]\\(([xyz]),([xyz]),([xyz])\\)=(.+)");

    Function(String str) {
        name = str.substring(0, 1);
        Matcher matcher1 = PATTERN1.matcher(str);
        if (matcher1.find()) {
            expr = matcher1.group(2);
            parameter.add(matcher1.group(1));
        }
        Matcher matcher2 = PATTERN2.matcher(str);
        if (matcher2.find()) {
            expr = matcher2.group(3);
            parameter.add(matcher2.group(1));
            parameter.add(matcher2.group(2));
        }
        Matcher matcher3 = PATTERN3.matcher(str);
        if (matcher3.find()) {
            expr = matcher3.group(4);
            parameter.add(matcher3.group(1));
            parameter.add(matcher3.group(2));
            parameter.add(matcher3.group(3));
        }
    }

    public String getName() {
        return name;
    }

    public String getExpr() {
        return expr;
    }

    public int getNum() {
        return parameter.size();
    }

    public int findPos(String str) {
        return parameter.indexOf(str);
    }
}
