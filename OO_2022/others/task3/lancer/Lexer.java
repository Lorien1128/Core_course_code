public class Lexer {
    private String str;
    private int pos;
    private String curToken;

    public Lexer(String str) {
        pos = 0;
        this.str = str;
        next();
    }

    public String getNumber() {
        StringBuilder ret = new StringBuilder();
        while (pos < str.length() &&
                Character.isDigit(str.charAt(pos))) {
            ret.append(str.charAt(pos));
            ++pos;
        }
        return ret.toString();
    }

    public String getWord() {
        StringBuilder ret = new StringBuilder();
        while (pos < str.length() &&
                Character.isLetter(str.charAt(pos))) {
            ret.append(str.charAt(pos));
            ++pos;
        }
        return ret.toString();
    }

    public String getPowerSign() {
        StringBuilder ret = new StringBuilder();
        char c = str.charAt(pos++);
        ret.append(c);
        c = str.charAt(pos++);
        ret.append(c);
        return ret.toString();
    }

    public void next() {
        if (pos == str.length()) {
            return;
        }
        if (Character.isDigit(str.charAt(pos))) {
            curToken = getNumber();
            return;
        }
        if (Character.isLetter(str.charAt(pos))) {
            curToken = getWord();
            return;
        }
        if (isPowerSign()) {
            curToken = getPowerSign();
            return;
        }
        char c = str.charAt(pos);
        curToken = String.valueOf(c);
        ++pos;
    }

    public boolean hasNext() {
        return !(pos == str.length());
    }

    public boolean isPowerSign() {
        char c = str.charAt(pos);
        if (pos >= str.length() - 1) {
            return false;
        }
        char next = str.charAt(pos + 1);
        return (c == '*' && next == '*');
    }

    public String peek() {
        return curToken;
    }
}
