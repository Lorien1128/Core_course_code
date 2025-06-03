package mcode;

import mcode.operand.Operand;
import symboltabel.VarSymbol;

import java.util.ArrayList;

public class Declare implements MidCode {
    private final VarSymbol varSymbol;

    public Declare(VarSymbol varSymbol) {
        this.varSymbol = varSymbol;
    }

    @Override
    public String toString() {
        return "DECLARE INT"  + "[" + varSymbol.getLen() + "] " + varSymbol.getMark() + "\n";
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

    public VarSymbol getVarSymbol() {
        return varSymbol;
    }
}
