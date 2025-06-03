package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Symbol implements Item {
    private final Word type;
    private final String content;
    private final int line;

    public Symbol(int line, Word word, String content) {
        this.type = word;
        this.content = content;
        this.line = line;
    }

    @Override
    public String toString() {
        return type + " " + content + "\n";
    }

    public Word getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public int getLine() {
        return line;
    }

    public int numOfFormatChar() {
        Matcher matcher = Pattern.compile("%d").matcher(content);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
