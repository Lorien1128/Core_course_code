package symboltabel;

import nonterminals.Exp;
import nonterminals.FuncFParam;
import nonterminals.FuncFParams;
import nonterminals.FuncRParams;
import nonterminals.FuncType;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;
import java.util.HashSet;

public class FuncSymbol implements SymbolItem {
    private String name;
    private int line;
    private final Word returnMode;
    private final ArrayList<VarSymbol> funcParams = new ArrayList<>();
    private final HashSet<String> regUsesInFunc = new HashSet<>();
    private boolean leafFunc = true;

    public FuncSymbol(FuncType funcType) {
        this.returnMode = ((Symbol) funcType.get(0)).getType();
    }

    public FuncSymbol() {
        returnMode = null;
    }

    public void setName(Symbol ident) {
        this.name = ident.getContent();
        line = ident.getLine();
    }

    public ArrayList<VarSymbol> setFuncParams(FuncFParams funcParams) {
        for (int i = 0; i < funcParams.size(); i += 2) {
            VarSymbol varSymbol = new VarSymbol();
            varSymbol.setDimension((FuncFParam) (funcParams.get(i)));
            this.funcParams.add(varSymbol);
        }
        return this.funcParams;
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public int getParamLen() {
        return funcParams.size();
    }

    public void checkParam(FuncRParams params, int line) {
        if (funcParams.size() != params.getParamLen()) {
            MyIO.writeToErrorBuffer(line, "d");
        }
        else {
            for (int i = 0; i < funcParams.size(); i++) {
                int frParaDim = ((Exp) params.get(2 * i)).getDimension();
                if (frParaDim == Integer.MIN_VALUE) {
                    continue;
                }
                if (funcParams.get(i).getDimension() !=
                        frParaDim) {
                    MyIO.writeToErrorBuffer(line, "e");
                }
            }
        }
    }

    public Word getReturnMode() {
        return returnMode;
    }

    public void addReg(String reg) {
        regUsesInFunc.add(reg);
    }

    public HashSet<String> getRegUsesInFunc() {
        return regUsesInFunc;
    }

    public void setLeafFunc(boolean leafFunc) {
        this.leafFunc = leafFunc;
    }

    public boolean isLeafFunc() {
        return leafFunc;
    }
}
