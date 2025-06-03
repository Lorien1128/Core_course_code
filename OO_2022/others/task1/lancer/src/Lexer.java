import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private final String line;
    private int pos;
    private String nextThing;
    private char nextType;

    public Lexer(String line) {
        this.line = line;
        this.pos = 0;
        this.nextThing = "";
        this.nextType = '\0';
    }

    private void skipBlank() {
        while ((pos < line.length()) && (" \t".indexOf(line.charAt(pos)) != -1)) {
            pos++;
        }
    }

    private String nextNumber() {
        this.skipBlank();
        Pattern pattern = Pattern.compile("[+-]?\\d+");
        Matcher matcher = pattern.matcher(line.substring(pos));
        if (matcher.lookingAt()) {
            if (matcher.start() == 0) {
                pos += matcher.end();
                return matcher.group();
            }
        }
        return "";
    }

    public boolean hasNextThing() {
        this.skipBlank();
        return pos < this.line.length();
    }

    public char getNextType() {
        return this.nextType;
    }

    public String getNextThing() {
        return this.nextThing;
    }

    public void toNextThing(char type) {
        this.skipBlank();
        if (pos >= line.length()) {
            this.nextThing = "";
            this.nextType = '\0';
            return;
        }
        if (line.charAt(pos) == '(') {
            this.nextThing = String.valueOf(line.charAt(pos++));
            this.nextType = '(';
            return;
        }
        if (line.charAt(pos) == ')') {
            this.nextThing = String.valueOf(line.charAt(pos++));
            this.nextType = ')';
            return;
        }
        if (line.charAt(pos) == 'x') {
            this.nextThing = String.valueOf(line.charAt(pos++));
            this.nextType = 'x';
            return;
        }
        if ("+-".indexOf(line.charAt(pos)) != -1) {
            if ((type != '+') && (Character.isDigit(line.charAt(pos + 1)))) {
                this.nextThing = this.nextNumber();
                this.nextType = 'n';
                return;
            }
            this.nextThing = String.valueOf(line.charAt(pos++));
            this.nextType = '+';
            return;
        }
        if (line.charAt(pos) == '*') {
            pos++;
            if (line.charAt(pos) == '*') {
                pos++;
                this.nextThing = "**";
                this.nextType = 'e';
                return;
            }
            this.nextThing = "*";
            this.nextType = '*';
            return;
        }
        if (Character.isDigit(line.charAt(pos))) {
            this.nextThing = this.nextNumber();
            this.nextType = 'n';
            return;
        }
        this.nextThing = "";
        this.nextType = '\0';
    }

    public void reset() {
        this.pos = 0;
        this.nextThing = "";
        this.nextType = '\0';
    }

}
