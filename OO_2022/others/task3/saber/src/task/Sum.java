package task;

public class Sum {
    public String operation(int start, int end, String t) {
        String result;
        String tempstring = t;
        tempstring = tempstring.replaceAll("sin", "m");
        int counti;
        if (start > end) {
            return "0";
        }
        else {
            result = tempstring.replaceAll("i", "(" + Integer.toString(start) + ")");
            for (counti = start + 1; counti <= end; counti++) {
                result = (result + "+" +
                        tempstring.replaceAll("i", "(" + Integer.toString(counti) + ")"));
            }
            result = result.replaceAll("m", "sin");
            return result;
        }
    }
}
