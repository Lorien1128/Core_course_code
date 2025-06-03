package mcode;

import mcode.operand.Operand;
import mcode.operand.ParameterOperand;
import symboltabel.VarSymbol;

import java.util.ArrayList;

public class ParaDeclare implements MidCode {
    private VarSymbol para;
    private final ParameterOperand operand;
    private final int index;

    public ParaDeclare(VarSymbol varSymbol, int index) {
        para = varSymbol;
        operand = new ParameterOperand(index);
        this.index = index;
    }

    public ParameterOperand getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return "PARA" + index + " " + para.getMark() + "\n";
    }

    @Override
    public ArrayList<String> getSourceIds() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<String> getTargetIds() {
        ArrayList<String> targets = new ArrayList<>();
        targets.add(para.getMark());
        return targets;
    }

    @Override
    public void replaceSource(Operand from, Operand to) {

    }

    public void replacePara(VarSymbol to) {
        para = to;
    }

    public VarSymbol getPara() {
        return para;
    }

    public int getIndex() {
        return index;
    }
}
