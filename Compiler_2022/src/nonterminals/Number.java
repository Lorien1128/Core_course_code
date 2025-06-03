package nonterminals;

import tools.LexicalAnalyzer;
import tools.Symbol;
import tools.Word;

public class Number extends Nonterminal implements Calculable {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() == Word.INTCON) {
            putIn(la.readSym());
            la.nextSym();
        }
        else {
            throw new Exception();
        }
    }

    public int calculate() {
        return Integer.parseInt(((Symbol) get(0)).getContent());
    }
}
