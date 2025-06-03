package mcode;

import mcode.operand.Operand;

import java.util.ArrayList;

public class Return implements MidCode {
    @Override
    public String toString() {
        return "RETURN\n";
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
