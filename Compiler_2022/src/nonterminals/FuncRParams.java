package nonterminals;

import mcode.ParaPush;
import mcode.operand.Operand;
import tools.LexicalAnalyzer;
import tools.Word;

import java.util.ArrayList;

public class FuncRParams extends Nonterminal {
    private final ArrayList<Exp> paras = new ArrayList<>();

    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (true) {
            Exp exp = new Exp();
            exp.parse();
            putIn(exp);
            paras.add(exp);
            if (la.readSym().getType() == Word.COMMA) {
                putIn(la.readSym());
                la.nextSym();
            }
            else {
                break;
            }
        }
    }

    public int getParamLen() {
        return (size() + 1) / 2;
    }

    public Operand genMidCode() {
        ArrayList<ParaPush> paraPushes = new ArrayList<>();
        for (int i = 0; i < paras.size(); i++) {
            Operand para = paras.get(i).genMidCode();
            free(para);
            ParaPush paraPush = new ParaPush(para, i);
            paraPushes.add(paraPush);
        }
        for (ParaPush paraPush : paraPushes) {
            writeInMidCode(paraPush);
        }
        return null;
    }
}
