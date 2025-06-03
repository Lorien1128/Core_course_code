package mcode;

import mcode.operand.Operand;
import symboltabel.SymbolTable;
import symboltabel.VarSymbol;
import tools.Symbol;

import java.util.ArrayList;

public class ArrayLoad implements MidCode {
    private VarSymbol arrayIdent;
    private Operand index;
    private final Operand operandTarget;

    public ArrayLoad(Symbol ident, Operand index, Operand operandTarget) {
        arrayIdent = SymbolTable.getSymbolTable().getVar(ident);
        this.index = index;
        this.operandTarget = operandTarget;
    }

    public ArrayLoad(Operand varSymbol, Operand index, Operand operandTarget) {
        arrayIdent = (VarSymbol) varSymbol;
        this.index = index;
        this.operandTarget = operandTarget;
    }

    @Override
    public String toString() {
        return operandTarget.getMark() + " = " + arrayIdent.getMark() +
                "[" + index.getMark() + "]\n";
    }

    @Override
    public ArrayList<String> getSourceIds() {
        ArrayList<String> ids = new ArrayList<>();
        ids.add(arrayIdent.getMark());
        if (hasId(index)) {
            ids.add(index.getMark());
        }
        return ids;
    }

    @Override
    public ArrayList<String> getTargetIds() {
        ArrayList<String> ids = new ArrayList<>();
        if (hasId(operandTarget)) {
            ids.add(operandTarget.getMark());
        }
        return ids;
    }

    @Override
    public void replaceSource(Operand from, Operand to) {
        if (arrayIdent.getMark().equals(from.getMark())) {
            arrayIdent = (VarSymbol) to;
        }
        if (index.getMark().equals(from.getMark())) {
            index = to;
        }
    }

    public Operand getIndex() {
        return index;
    }

    public Operand getOperandTarget() {
        return operandTarget;
    }

    public VarSymbol getArrayIdent() {
        return arrayIdent;
    }

    public boolean isArray() {
        return arrayIdent.getDimension() != 0;
    }
}
