package mcode;

import mcode.operand.Operand;
import symboltabel.SymbolTable;
import symboltabel.VarSymbol;
import tools.Symbol;

import java.util.ArrayList;

public class ArrayStore implements MidCode {
    private VarSymbol arrayTarget;
    private Operand index;
    private Operand operandSource;

    public ArrayStore(Symbol ident, Operand index, Operand operandSource) {
        arrayTarget = SymbolTable.getSymbolTable().getVar(ident);
        this.index = index;
        this.operandSource = operandSource;
    }

    public ArrayStore(Operand varSymbol, Operand index, Operand operandSource) {
        arrayTarget = (VarSymbol) varSymbol;
        this.index = index;
        this.operandSource = operandSource;
    }

    @Override
    public String toString() {
        return arrayTarget.getMark() + "[" + index.getMark() + "]"
                + " = " + operandSource.getMark() + "\n";
    }

    @Override
    public ArrayList<String> getSourceIds() {
        ArrayList<String> ids = new ArrayList<>();
        if (hasId(index)) {
            ids.add(index.getMark());
        }
        if (hasId(operandSource)) {
            ids.add(operandSource.getMark());
        }
        if (arrayTarget.isSpreadAddress()) {
            ids.add(arrayTarget.getMark());
        }
        return ids;
    }

    @Override
    public ArrayList<String> getTargetIds() {
        ArrayList<String> ids = new ArrayList<>();
        if (hasId(arrayTarget) && !arrayTarget.isSpreadAddress()) {
            ids.add(arrayTarget.getMark());
        }
        return ids;
    }

    @Override
    public void replaceSource(Operand from, Operand to) {
        if (operandSource.getMark().equals(from.getMark())) {
            operandSource = to;
        }
        if (index.getMark().equals(from.getMark())) {
            index = to;
        }
    }

    @Override
    public ArrayList<String> getTargetVarMips() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (isMipsVar(arrayTarget)) {
            arrayList.add(arrayTarget.getMipsSym());
        }
        return arrayList;
    }

    public void replaceTarget(VarSymbol to) {
        arrayTarget = to;
    }

    public Operand getIndex() {
        return index;
    }

    public Operand getOperandSource() {
        return operandSource;
    }

    public VarSymbol getArrayTarget() {
        return arrayTarget;
    }

    public boolean isNotArray() {
        return arrayTarget.getDimension() == 0;
    }
}
