package nonterminals;

import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Word;

public class VarDecl extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() != Word.INTTK) {
            throw new Exception();
        }
        else {
            putIn(la.readSym());
        }
        while (true) {
            la.nextSym();
            VarDef varDef = new VarDef();
            varDef.parse();
            putIn(varDef);
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
