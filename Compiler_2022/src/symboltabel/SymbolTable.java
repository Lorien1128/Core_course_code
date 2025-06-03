package symboltabel;

import mcode.Label;
import mcode.operand.RegisterOperand;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final ArrayList<HashMap<String, SymbolItem>> symbolTables = new ArrayList<>();
    private Word funcType = null;
    private static final SymbolTable SYMBOL_TABLE = new SymbolTable();
    private final int baseSp = 0x7fffeffc;
    private final int baseHp = 0x10008000;
    private int sp = baseSp;
    private int hp = baseHp;
    private final ArrayList<Integer> spRecords = new ArrayList<>();
    private final ArrayList<Label> loopStarts = new ArrayList<>();
    private final ArrayList<Label> loopEnds = new ArrayList<>();
    private boolean inMainFunc = false;
    private final boolean debug = false;

    private SymbolTable() {
        symbolTables.add(new HashMap<>());
    }

    public void setInMainFunc(boolean inMainFunc) {
        this.inMainFunc = inMainFunc;
    }

    public boolean isInMainFunc() {
        return inMainFunc;
    }

    public void pushLoopLabel(Label start, Label end) {
        loopStarts.add(start);
        loopEnds.add(end);
        // System.out.println("enterLoop " + isInLoop());
    }

    public Label getCurLoopStart() {
        return loopStarts.get(loopStarts.size() - 1);
    }

    public Label getCurLoopEnd() {
        return loopEnds.get(loopEnds.size() - 1);
    }

    public void popLoopLabel() {
        loopStarts.remove(loopStarts.size() - 1);
        loopEnds.remove(loopEnds.size() - 1);
        // System.out.println("leaveLoop " + isInLoop());
    }

    public int getSp() {
        if (debug) {
            System.out.println("get sp offset: " + (baseSp - sp));
        }
        return sp;
    }

    public int allocSp(int bytes) {
        sp -= bytes * 4;
        if (debug) {
            System.err.println("alloc " + bytes + ", offset is " + (baseSp - sp));
            // Exception exception = new Exception();
        }
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int allocHp(int bytes) {
        int preAd = hp;
        hp += bytes * 4;
        return preAd;
    }

    public boolean isGlobalNow() {
        return symbolTables.size() == 1;
    }

    public static SymbolTable getSymbolTable() {
        return SYMBOL_TABLE;
    }

    public void setFuncType(Word type) {
        funcType = type;
    }

    public Word getFuncType() {
        return funcType;
    }

    public boolean isInLoop() {
        return !loopStarts.isEmpty();
    }

    public void addSymbol(SymbolItem symbol) {
        if (symbolTables.get(symbolTables.size() - 1).containsKey(symbol.getName())) {
            MyIO.writeToErrorBuffer(symbol.getLine(), "b");
        }
        else {
            symbolTables.get(symbolTables.size() - 1).put(
                    symbol.getName(), symbol);
        }
    }

    public void addSymbol(ArrayList<VarSymbol> varSymbols) {
        for (VarSymbol varSymbol : varSymbols) {
            addSymbol(varSymbol);
        }
    }

    public void addPile() {
        symbolTables.add(new HashMap<>());
        spRecords.add(sp);
    }

    public void removePile() {
        RegisterOperand.check();
        symbolTables.remove(symbolTables.size() - 1);
        int spRecord = spRecords.remove(spRecords.size() - 1);
        if (sp != spRecord) {
            try {
                throw new Exception("栈错误");
            } catch (Exception e) {
                System.err.println(Integer.toHexString(sp) + " " + Integer.toHexString(spRecord));
                e.printStackTrace();
            }
        }
    }

    public VarSymbol getVar(Symbol ident) {
        String name = ident.getContent();
        for (int i = symbolTables.size() - 1; i >= 0; i--) {
            if (symbolTables.get(i).containsKey(name) &&
                    symbolTables.get(i).get(name) instanceof VarSymbol) {
                VarSymbol varSymbol = (VarSymbol) symbolTables.get(i).get(name);
                varSymbol.setLoopDepth(loopStarts.size());
                varSymbol.addReferCount();
                return varSymbol;
            }
        }
        MyIO.writeToErrorBuffer(ident.getLine(), "c");
        return null;
    }

    public VarSymbol getVar(Symbol ident, boolean print) {
        String name = ident.getContent();
        for (int i = symbolTables.size() - 1; i >= 0; i--) {
            if (symbolTables.get(i).containsKey(name) &&
                    symbolTables.get(i).get(name) instanceof VarSymbol) {
                return (VarSymbol) symbolTables.get(i).get(name);
            }
        }
        if (print) {
            MyIO.writeToErrorBuffer(ident.getLine(), "c");
        }
        return null;
    }

    public FuncSymbol getFunc(Symbol ident) {
        String name = ident.getContent();
        for (int i = symbolTables.size() - 1; i >= 0; i--) {
            if (symbolTables.get(i).containsKey(name) &&
                    symbolTables.get(i).get(name) instanceof FuncSymbol) {
                return (FuncSymbol) symbolTables.get(i).get(name);
            }
        }
        MyIO.writeToErrorBuffer(ident.getLine(), "c");
        return null;
    }

    public int getPile() {
        return symbolTables.size();
    }

    public int getBaseSp() {
        return baseSp;
    }

    public int getBaseHp() {
        return baseHp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (HashMap<String, SymbolItem> symbolTable : symbolTables) {
            for (Map.Entry<String, SymbolItem> itemEntry : symbolTable.entrySet()) {
                if (itemEntry.getValue() instanceof VarSymbol) {
                    builder.append(itemEntry.getValue());
                }
            }
        }
        return builder.toString();
    }
}
