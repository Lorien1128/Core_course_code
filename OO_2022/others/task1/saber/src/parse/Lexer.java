package parse;

public class Lexer {
    private final String input;
    private int pos = 0;
    private String curToken;
    private String curSub;

    public Lexer(String input) {
        this.input = input;
        this.nextNotEmpty();
    }

    public String sub() {
        update();
        return input.substring(pos);
    }

    public void next() {
        if (pos == input.length()) {
            update();
            return;
        }
        if (!Character.toString(input.charAt(pos)).matches("\\s")) {
            pos += 1;
            if (pos == input.length()) {
                update();
                return;
            }
        }

        while (Character.toString(input.charAt(pos)).matches("\\s")) {
            pos += 1;
            if (pos == input.length()) {
                update();
                return;
            }
        }

        update();
    }

    public void nextNotEmpty() {
        if (pos == input.length()) {
            update();
            return;
        }
        while (Character.toString(input.charAt(pos)).matches("\\s")) {
            pos += 1;
            if (pos == input.length()) {
                update();
                return;
            }
        }
        update();
    }

    public void nextEle(String ele) {
        if (pos == input.length()) {
            update();
            return;
        }
        pos += ele.length();
        if (pos == input.length()) {
            update();
            return;
        }
        while (Character.toString(input.charAt(pos)).matches("\\s")) {
            pos += 1;
            if (pos == input.length()) {
                update();
                return;
            }
        }
        update();
    }

    public String getNumber() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            ++pos;
        }

        return sb.toString();
    }

    public String peek() {
        update();
        return this.curToken;
    }

    private void update() {
        if (pos == input.length()) {
            curToken = "";
            curSub = "";
        } else {
            curToken = Character.toString(input.charAt(pos));
            curSub = input.substring(pos);
        }
    }

}
