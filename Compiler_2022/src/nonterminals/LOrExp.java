package nonterminals;

import mcode.Jump;
import mcode.Label;
import mcode.operand.Operand;
import tools.LexicalAnalyzer;
import tools.Word;

import java.util.ArrayList;

public class LOrExp extends LeftRecursion {
    private final ArrayList<LAndExp> landExps = new ArrayList<>();

    public void parse() throws Exception {
        LAndExp landExp = new LAndExp();
        landExp.parse();
        putIn(landExp);
        landExps.add(landExp);
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (la.readSym().getType() == Word.OR) {
            putIn(la.readSym());
            la.nextSym();
            LAndExp landExp1 = new LAndExp();
            landExp1.parse();
            putIn(landExp1);
            landExps.add(landExp1);
        }
    }

    public Operand genMidCode() {
        Label start = new Label("start");
        Label end = new Label("end");
        for (LAndExp landExp : landExps) {
            landExp.setStart(start);
            if (landExps.size() == 1) {
                landExp.setTheOnlyLAnd(true);
            }
            Label terminal = new Label();
            if (landExp.size() > 1 && landExps.size() > 1) {
                landExp.setTerminal(terminal);
            }
            else if (landExps.size() == 1) {
                landExp.setTerminal(end);
            }
            landExp.genMidCode();
            if (landExp.size() > 1 && landExps.size() > 1) {
                writeInMidCode(terminal);
            }
        }
        if (landExps.size() > 1) {
            Jump jump = new Jump(end);
            writeInMidCode(jump);
            writeInMidCode(start);
        }
        return end;
    }
}
