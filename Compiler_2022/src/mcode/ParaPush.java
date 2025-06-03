package mcode;

import mcode.operand.Operand;
import mcode.operand.ParameterOperand;

import java.util.ArrayList;

public class ParaPush implements MidCode {
    private Operand source;
    private final ParameterOperand parameterOperand;
    private final int index;

    public ParaPush(Operand source, int index) {
        this.source = source;
        parameterOperand = new ParameterOperand(index);
        this.index = index;
    }

    public ParameterOperand getParameterOperand() {
        return parameterOperand;
    }

    @Override
    public String toString() {
        return "PUSH " + source.getMark() + "\n";
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

    public int getIndex() {
        return index;
    }

    public Operand getSource() {
        return source;
    }
}
