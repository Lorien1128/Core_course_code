package tools;

public class ErrorMsg implements Comparable<ErrorMsg> {
    private final int line;
    private final String sort;

    public ErrorMsg(int line, String sort) {
        this.line = line;
        this.sort = sort;
    }

    public String toString() {
        return line + " " + sort;
    }

    @Override
    public int compareTo(ErrorMsg o) {
        return Integer.compare(line, o.line);
    }

    public int getLine() {
        return line;
    }
}
