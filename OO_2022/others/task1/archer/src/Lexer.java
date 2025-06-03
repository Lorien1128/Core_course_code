public class Lexer {
    private final String input;
    private int pos = 0;
    private String curToken;

    public Lexer(String input) {
        StringBuilder in = new StringBuilder(input);
        for (int i = 0; i < in.length(); i++) {
            int countMinus = 0;
            char c = in.charAt(i);
            if ("+-".indexOf(c) != -1) {
                if (c == '-') {
                    countMinus++;
                }
                for (int j = i + 1; j < in.length(); j++) {
                    int ch = in.charAt(j);
                    if (ch == '-') {
                        countMinus++;
                    } else if (ch != '+') {
                        if (countMinus % 2 != 0) {
                            in.replace(i, j, "-");
                        } else {
                            in.replace(i, j, "+");
                        }
                        break;
                    }
                }
            }
        }
        this.input = String.valueOf(in);
        this.next();
    }

    private String getNumber() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            ++pos;
        }

        return sb.toString();
    }

    public void next() {
        if (pos == input.length()) {
            return;
        }

        char c = input.charAt(pos);
        if ("+-()".indexOf(c) != -1) {
            pos += 1;
            curToken = String.valueOf(c);
        } else if (Character.isDigit(c)) {
            curToken = getNumber();
        } else if (c == 'x') {
            pos += 1;
            curToken = String.valueOf(c);
        } else if (c == '*' && input.charAt(pos + 1) != '*') {
            pos += 1;
            curToken = "*";
        } else {
            pos += 2;
            curToken = "**";
        }
    }

    public String peek() {
        return this.curToken;
    }
}
