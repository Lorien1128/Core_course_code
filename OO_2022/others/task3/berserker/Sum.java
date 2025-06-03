import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sum {
    private final Pattern sumPattern = Pattern
            .compile("^sum\\(\\s*i\\s*,\\s*([+-]?\\d+)\\s*," +
                    "\\s*([+-]?\\d+)\\s*,\\s*(.+)\\)$");

    public ExprClass sum(String str, ArrayList<Function> functions) {
        Matcher sumMatcher = sumPattern.matcher(str);
        if (sumMatcher.find()) {
            BigInteger start = new BigInteger(sumMatcher.group(1));
            BigInteger end = new BigInteger(sumMatcher.group(2));
            String content = sumMatcher.group(3).trim();
            if (start.compareTo(end) > 0) {
                return new ExprClass();
            } else {
                ExprClass result = new ExprClass();
                for (BigInteger now = start; now.compareTo(end) <= 0;
                     now = now.add(BigInteger.ONE)) {
                    result = new Compute().exprAdd(result,
                            new Sum().sumAdd(content, now, functions));
                }
                return result;
            }
        } else {
            throw new RuntimeException("Sum input error!");
        }
    }

    public ExprClass sumAdd(String str, BigInteger now, ArrayList<Function> functions) {
        String result = str;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 'i') {
                if (i == 0 || result.charAt(i - 1) != 's') {
                    result = result.substring(0, i) + "j" + result.substring(i + 1);
                }
            }
        }
        result = result.replaceAll("j", "(" + now + ")");
        return new FactorClass().factor(result, functions);
    }
}

