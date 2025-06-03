package nonterminals;

import mcode.FuncDeclare;
import tools.LexicalAnalyzer;
import tools.Symbol;
import tools.Word;

public class MainFuncDef extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() == Word.INTTK) {
            putIn(la.readSym());
        }
        else {
            throw new Exception();
        }
        getSymbolTable().setFuncType(Word.INTTK);
        if (la.nextSym().getType() == Word.MAINTK) {
            putIn(la.readSym());
        }
        else {
            throw new Exception();
        }
        FuncDeclare funcDeclare = new FuncDeclare(new Symbol(0, Word.INTTK, ""),
                new Symbol(0, Word.IDENFR, "main"));
        writeInMidCode(funcDeclare);
        getSymbolTable().setInMainFunc(true);
        if (la.nextSym().getType() == Word.LPARENT) {
            putIn(la.readSym());
        }
        else {
            throw new Exception();
        }
        if (la.nextSym().getType() == Word.RPARENT) {
            putIn(la.readSym());
        }
        else {
            throw new Exception();
        }
        la.nextSym();
        getSymbolTable().addPile();
        Block block = new Block();
        block.parse();
        putIn(block);
        getSymbolTable().setInMainFunc(false);
        getSymbolTable().removePile();
    }
}
