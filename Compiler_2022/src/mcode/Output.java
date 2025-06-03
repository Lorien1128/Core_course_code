package mcode;

import mcode.operand.Operand;

import java.util.ArrayList;

public class Output implements MidCode {
    private Operand source;

    public Output(Operand source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "OUTPUT " + source.getMark() + "\n";
    }

    @Override
    public ArrayList<String> getSourceIds() {
        ArrayList<String> ids = new ArrayList<>();
        if (hasId(source)) {
            ids.add(source.getMark());
        }
        return ids;
    }

    @Override
    public ArrayList<String> getTargetIds() {
        return new ArrayList<>();
    }

    @Override
    public void replaceSource(Operand from, Operand to) {
        if (source.getMark().equals(from.getMark())) {
            source = to;
        }
    }

    public Operand getSource() {
        return source;
    }
}
