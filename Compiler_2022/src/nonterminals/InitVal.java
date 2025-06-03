package nonterminals;

import tools.Item;
import tools.LexicalAnalyzer;
import tools.Word;

import java.util.ArrayList;

public class InitVal extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() == Word.LBRACE) {
            putIn(la.readSym());
            if (la.nextSym().getType() == Word.RBRACE) {
                putIn(la.readSym());
                la.nextSym();
            }
            else {
                while (true) {
                    InitVal initVal = new InitVal();
                    initVal.parse();
                    putIn(initVal);
                    if (la.readSym().getType() == Word.COMMA) {
                        putIn(la.readSym());
                        la.nextSym();
                    }
                    else if (la.readSym().getType() == Word.RBRACE) {
                        putIn(la.readSym());
                        la.nextSym();
                        break;
                    }
                    else {
                        throw new Exception();
                    }
                }
            }
        }
        else {
            Exp exp = new Exp();
            exp.parse();
            putIn(exp);
        }
    }

    public ArrayList<Exp> getInitList() {
        ArrayList<Exp> exps = new ArrayList<>();
        if (size() == 1) {
            exps.add((Exp) get(0));
        }
        else {
            for (Item item : getContents()) {
                if (item instanceof  InitVal) {
                    exps.addAll(((InitVal) item).getInitList());
                }
            }
        }
        return exps;
    }
}
