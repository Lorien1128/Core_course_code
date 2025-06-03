package mcode.operand;

public class CharOperand implements Operand {
    private final char content;

    public CharOperand(char c) {
        content = c;
    }

    @Override
    public boolean isInverted() {
        return false;
    }

    @Override
    public void setInverted(boolean inverted) {

    }

    @Override
    public String getMark() {
        if (content == '\n') {
            return "\\n";
        }
        if (content == ' ') {
            return "SPACE";
        }
        return String.valueOf(content);
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public void setNegative(boolean negative) {

    }

    public char getContent() {
        return content;
    }
}
