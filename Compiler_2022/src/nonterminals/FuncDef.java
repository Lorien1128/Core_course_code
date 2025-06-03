package nonterminals;

import mcode.FuncDeclare;
import mcode.ParaDeclare;
import symboltabel.FuncSymbol;
import symboltabel.VarSymbol;
import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;

public class FuncDef extends Nonterminal {
    private Symbol funcType;
    private Symbol funcName;
    private ArrayList<VarSymbol> paras = new ArrayList<>();

    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        funcType = la.readSym();
        FuncType funcType = new FuncType();
        funcType.parse();
        putIn(funcType);
        FuncSymbol funcSymbol = new FuncSymbol(funcType);
        if (la.readSym().getType() == Word.IDENFR) {
            putIn(la.readSym());
            funcSymbol.setName(la.readSym());
            funcName = la.readSym();
        }
        else {
            throw new Exception();
        }
        if (la.nextSym().getType() == Word.LPARENT) {
            putIn(la.readSym());
        }
        else {
            throw new Exception();
        }
        ArrayList<VarSymbol> varSymbols = new ArrayList<>();
        if (la.nextSym().getType() == Word.RPARENT) {
            putIn(la.readSym());
            la.nextSym();
        }
        else {
            int pointer = la.getSymPointer();
            FuncFParams funcFParams = new FuncFParams();
            try {
                funcFParams.parse();
                putIn(funcFParams);
                varSymbols = funcSymbol.setFuncParams(funcFParams);
                if (la.readSym().getType() == Word.RPARENT) {
                    putIn(la.readSym());
                    la.nextSym();
                }
                else {
                    MyIO.writeToErrorBuffer(la.preSym().getLine(), "j");
                }
            } catch (Exception e) {
                la.setSymPointer(pointer);
                MyIO.writeToErrorBuffer(la.preSym().getLine(), "j");
            }

        }
        getSymbolTable().addSymbol(funcSymbol);
        getSymbolTable().addPile();
        getSymbolTable().addSymbol(varSymbols);
        paras = varSymbols;
        genMidCode();
        Block block = new Block();
        block.parse();
        putIn(block);
        getSymbolTable().removePile();
    }

    private void genMidCode() {
        FuncDeclare declare = new FuncDeclare(funcType, funcName);
        writeInMidCode(declare);
        for (int i = 0; i < paras.size(); i++) {
            VarSymbol para = paras.get(i);
            ParaDeclare paraDeclare = new ParaDeclare(para, i);
            para.setSpreadAddress(para.getDimension() != 0);
            writeInMidCode(paraDeclare);
        }
    }
}
