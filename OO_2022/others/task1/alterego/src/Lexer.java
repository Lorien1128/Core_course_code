public class Lexer {
    private final String input;
    private int pos = 0;
    private String curToken;

    public Lexer(String input) {
        this.input = input;
        this.next();
    }

    private String getNumber() {
        StringBuilder result = new StringBuilder();
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            result.append(input.charAt(pos));
            ++pos;
        }
        return result.toString();
    }

    public void next() {
        if (pos == input.length()) {
            return;
        }
        char c = input.charAt(pos);
        if (Character.isDigit(c)) {
            curToken = getNumber();
        } else if ("()+-x".indexOf(c) != -1) {
            curToken = String.valueOf(c);
            pos += 1;
        } else if (c == '*') {
            pos += 1;
            if (input.charAt(pos) == '*') {
                pos += 1;
                curToken = "**";
            } else {
                curToken = "*";
            }
        }
    }

    public String peek() {
        return curToken;
    }
}
