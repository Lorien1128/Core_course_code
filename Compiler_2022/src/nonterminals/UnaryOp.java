package nonterminals;

import tools.LexicalAnalyzer;
import tools.Symbol;
import tools.Word;

public class UnaryOp extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        Word type = la.readSym().getType();
        if (type == Word.PLUS || type == Word.MINU || type == Word.NOT) {
            putIn(la.readSym());
            la.nextSym();
        }
        else {
            throw new Exception();
        }
    }

    public Word getType() {
        return ((Symbol) get(0)).getType();
    }
}
