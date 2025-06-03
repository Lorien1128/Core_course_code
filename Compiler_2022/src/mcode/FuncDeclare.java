package mcode;

import mcode.operand.Operand;
import symboltabel.FuncSymbol;
import symboltabel.SymbolTable;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;

public class FuncDeclare implements MidCode {
    private final Word type;
    private final FuncSymbol funcSymbol;
    private final String name;

    public FuncDeclare(Symbol funcType, Symbol funcName) {
        type = funcType.getType();
        FuncSymbol func = SymbolTable.getSymbolTable().getFunc(funcName);
        if (func != null) {
            funcSymbol = func;
        }
        else {
            funcSymbol = new FuncSymbol();
        }
        name = funcName.getContent();
    }

    public FuncSymbol getFuncSymbol() {
        return funcSymbol;
    }

    @Override
    public String toString() {
        if (funcSymbol == null || funcSymbol.getName() == null) {
            return type.name().substring(0, type.name().length() - 2).toLowerCase()
                    + " " + name + "()\n";
        }
        return type.name().substring(0, type.name().length() - 2).toLowerCase()
                + " " + funcSymbol.getName() + "()\n";
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

    public String getName() {
        return name;
    }
}
