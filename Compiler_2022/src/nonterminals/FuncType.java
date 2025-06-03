package nonterminals;

import tools.LexicalAnalyzer;
import tools.Word;

public class FuncType extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() == Word.INTTK ||
                la.readSym().getType() == Word.VOIDTK) {
            putIn(la.readSym());
            getSymbolTable().setFuncType(la.readSym().getType());
            la.nextSym();
        }
        else {
            throw new Exception();
        }
    }
}
