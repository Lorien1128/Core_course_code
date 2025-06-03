package nonterminals;

import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Word;

public class ConstDecl extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() != Word.CONSTTK) {
            throw new Exception();
        }
        else {
            putIn(la.readSym());
        }
        if (la.nextSym().getType() != Word.INTTK) {
            throw new Exception();
        }
        else {
            putIn(la.readSym());
        }
        while (true) {
            la.nextSym();
            ConstDef constDef = new ConstDef();
            constDef.parse();
            putIn(constDef);
            if (la.readSym().getType() == Word.COMMA) {
                putIn(la.readSym());
            }
            else if (la.readSym().getType() == Word.SEMICN) {
                putIn(la.readSym());
                break;
            }
            else {
                MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
                return;
            }
        }
        la.nextSym();
    }
}
