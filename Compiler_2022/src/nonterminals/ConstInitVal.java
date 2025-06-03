package nonterminals;

import symboltabel.VarSymbol;
import tools.Item;
import tools.LexicalAnalyzer;
import tools.Word;

public class ConstInitVal extends Nonterminal {
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
                    ConstInitVal constInitVal = new ConstInitVal();
                    constInitVal.parse();
                    putIn(constInitVal);
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
            ConstExp constExp = new ConstExp();
            constExp.parse();
            putIn(constExp);
        }
    }

    public void fill(VarSymbol varSymbol) {
        for (Item item : getContents()) {
            if (item instanceof ConstExp) {
                varSymbol.addValue((ConstExp) item);
            }
            else if (item instanceof ConstInitVal) {
                ((ConstInitVal) item).fill(varSymbol);
            }
        }
    }
}
