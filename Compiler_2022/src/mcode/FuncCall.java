package mcode;

import mcode.operand.Operand;
import symboltabel.FuncSymbol;
import symboltabel.SymbolTable;
import symboltabel.VarSymbol;
import tools.Symbol;

import java.util.ArrayList;
import java.util.HashSet;

public class FuncCall implements MidCode {
    private final FuncSymbol funcSymbol;
    private final ArrayList<VarSymbol> nextIsUse = new ArrayList<>();
    private final boolean inMainFunc;

    public FuncCall(Symbol symbol) {
        funcSymbol = SymbolTable.getSymbolTable().getFunc(symbol);
        inMainFunc = SymbolTable.getSymbolTable().isInMainFunc();
    }

    public boolean isInMainFunc() {
        return inMainFunc;
    }

    @Override
    public String toString() {
        return "CALL " + funcSymbol.getName() + "\n";
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

    public FuncSymbol getFuncSymbol() {
        return funcSymbol;
    }

    public void addUse(VarSymbol varSymbol) {
        nextIsUse.add(varSymbol);
    }

    public ArrayList<String> getStores() {
        HashSet<String> willUseInFunc = funcSymbol.getRegUsesInFunc();
        ArrayList<String> list = new ArrayList<>();
        for (VarSymbol varSymbol : nextIsUse) {
            if (varSymbol.hasAllocToReg() &&
                    willUseInFunc.contains(varSymbol.getMipsSym())) {
                list.add(varSymbol.getMipsSym());
            }
        }
        list.add("$ra");
        return list;
    }

    public HashSet<String> getWillUseInFunc() {
        return funcSymbol.getRegUsesInFunc();
    }

    public boolean isLeafFunc() {
        return funcSymbol.isLeafFunc();
    }
}
