import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String input;
    private int pos;
    private String next;
    private char nextType;

    public Lexer() {
        this.input = "";
        this.pos = 0;
        this.next = "";
        this.nextType = '\0';
    }

    public void setInput(String input) {
        this.input = input;
        this.reset();
    }

    private void skipBlank() {
        while ((pos < input.length()) && (" \t".indexOf(input.charAt(pos)) != -1)) {
            pos++;
        }
    }

    private String nextNumber() {
        this.skipBlank();
        Pattern pattern = Pattern.compile("[+-]?\\d+");
        Matcher matcher = pattern.matcher(input.substring(pos));
        if (matcher.lookingAt()) {
            if (matcher.start() == 0) {
                pos += matcher.end();
                return matcher.group();
            }
        }
        return "";
    }

    private String nextWord() {
        this.skipBlank();
        Pattern pattern = Pattern.compile("[a-z]+");
        Matcher matcher = pattern.matcher(input.substring(pos));
        if (matcher.lookingAt()) {
            if (matcher.start() == 0) {
                pos += matcher.end();
                return matcher.group();
            }
        }
        return "";
    }

    private String nextInBracketFactor() {
        this.skipBlank();
        Pattern pattern = Pattern.compile(".*?(\\(.*?\\).*?)*\\)");
        Matcher matcher = pattern.matcher(input.substring(pos));
        if (matcher.lookingAt()) {
            if (matcher.start() == 0) {
                pos += matcher.end() - 1;
                String expression = matcher.group();
                return expression.substring(0, expression.length() - 1);
            }
        }
        return "";
    }

    public boolean hasNext() {
        this.skipBlank();
        return pos < this.input.length();
    }

    public char getNextType() {
        return this.nextType;
    }

    public String getNext() {
        return this.next;
    }

    public void toNext(char type) {
        this.skipBlank();
        //System.out.println("pos: " + this.pos);
        if (type == 'p') {
            this.next = this.input.substring(pos);
            this.nextType = 'p';
            return; }
        if (type == 'f') {
            this.next = this.nextInBracketFactor();
            this.nextType = 'f';
            return; }
        if (pos >= input.length()) {
            this.next = "";
            this.nextType = '\0';
            return; }
        if (input.charAt(pos) == '(') {
            this.next = String.valueOf(input.charAt(pos++));
            this.nextType = '(';
            return; }
        if (input.charAt(pos) == ')') {
            this.next = String.valueOf(input.charAt(pos++));
            this.nextType = ')';
            return; }
        if (Character.isLowerCase(input.charAt(pos))) {
            if ("fgh".indexOf(input.charAt(pos)) != -1) {
                this.next = String.valueOf(input.charAt(pos++));
                this.nextType = 'f';
                return; }
            else if ("xyz".indexOf(input.charAt(pos)) != -1) {
                this.next = String.valueOf(input.charAt(pos++));
                this.nextType = 'x';
                return; }
            else if (input.charAt(pos) == 'i') {
                this.next = String.valueOf(input.charAt(pos++));
                this.nextType = 'i';
                return; }
            else {
                this.next = this.nextWord();
                this.nextType = 'w';
                return; } }
        this.toNext2(type);
    }

    private void toNext2(char type) {
        if ("+-".indexOf(input.charAt(pos)) != -1) {
            if ((type != '+') && (Character.isDigit(input.charAt(pos + 1)))) {
                this.next = this.nextNumber();
                this.nextType = 'n';
                return; }
            this.next = String.valueOf(input.charAt(pos++));
            this.nextType = '+';
            return; }
        if (input.charAt(pos) == '*') {
            pos++;
            if (input.charAt(pos) == '*') {
                pos++;
                this.next = "**";
                this.nextType = 'e';
                return; }
            this.next = "*";
            this.nextType = '*';
            return; }
        if (input.charAt(pos) == '=') {
            this.next = String.valueOf(input.charAt(pos++));
            this.nextType = '=';
            return; }
        if (input.charAt(pos) == ',') {
            this.next = String.valueOf(input.charAt(pos++));
            this.nextType = ',';
            return; }
        if (Character.isDigit(input.charAt(pos))) {
            this.next = this.nextNumber();
            this.nextType = 'n';
            return; }
        this.next = "";
        this.nextType = '\0';
    }

    private void reset() {
        this.pos = 0;
        this.next = "";
        this.nextType = '\0';
    }

}
