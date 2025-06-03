package parse;

import java.util.regex.Pattern;

public class StringPatternUtil {

    public static boolean stringContains(String input, String regex) {
        return Pattern.compile(regex).matcher(input).find();
    }
}
