package nonterminals;

import tools.LexicalAnalyzer;
import tools.Word;

public class FuncFParams extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (true) {
            FuncFParam funcFParam = new FuncFParam();
            funcFParam.parse();
            putIn(funcFParam);
            if (la.readSym().getType() == Word.COMMA) {
                putIn(la.readSym());
                la.nextSym();
            }
            else {
                break;
            }
        }
    }
}
