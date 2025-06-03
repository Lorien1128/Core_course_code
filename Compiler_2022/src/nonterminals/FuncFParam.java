package nonterminals;

import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Word;

public class FuncFParam extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() == Word.INTTK) {
            putIn(la.readSym());
        }
        else {
            throw new Exception();
        }
        if (la.nextSym().getType() == Word.IDENFR) {
            putIn(la.readSym());
        }
        else {
            throw new Exception();
        }
        if (la.nextSym().getType() == Word.LBRACK) {
            putIn(la.readSym());
            if (la.nextSym().getType() == Word.RBRACK) {
                putIn(la.readSym());
            }
            else {
                MyIO.writeToErrorBuffer(la.preSym().getLine(), "k");
                la.fallBack(1);
            }
            while (la.nextSym().getType() == Word.LBRACK) {
                putIn(la.readSym());
                la.nextSym();
                ConstExp constExp = new ConstExp();
                constExp.parse();
                putIn(constExp);
                if (la.readSym().getType() == Word.RBRACK) {
                    putIn(la.readSym());
                }
                else {
                    MyIO.writeToErrorBuffer(la.preSym().getLine(), "k");
                    la.fallBack(1);
                }
            }
        }
    }
}
