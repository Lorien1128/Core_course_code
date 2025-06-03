package mcode;

import mcode.operand.Operand;

import java.util.ArrayList;

public class Label implements MidCode, Operand {
    private static int count = 0;
    private String name;

    public Label() {
        name = "label." + count;
        count++;
    }

    public Label(String name) {
        this.name = name + "." + count;
        count++;
    }

    public void setName(String name) {
        this.name = name + "." + this.name.split("\\.")[1];
    }

    @Override
    public String toString() {
        return name + ":\n";
    }

    public String getName() {
        return name;
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
        return getName();
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public void setNegative(boolean negative) {

    }

    @Override
    public ArrayList<String> getSourceIds() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<String> getTargetIds() {
        return new ArrayList<>();
    }

    @Override
    public void replaceSource(Operand from, Operand to) {

    }
}
