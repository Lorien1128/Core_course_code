package nonterminals;

import mcode.Jump;
import mcode.Label;
import mcode.operand.Operand;
import tools.LexicalAnalyzer;
import tools.Word;

import java.util.ArrayList;

public class LAndExp extends LeftRecursion {
    private final ArrayList<EqExp> eqExps = new ArrayList<>();
    private Label terminal;
    private boolean theOnlyLAnd = false;
    private Label start;

    public void parse() throws Exception {
        EqExp eqExp = new EqExp();
        eqExp.parse();
        putIn(eqExp);
        eqExps.add(eqExp);
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (la.readSym().getType() == Word.AND) {
            putIn(la.readSym());
            la.nextSym();
            EqExp eqExp1 = new EqExp();
            eqExp1.parse();
            putIn(eqExp1);
            eqExps.add(eqExp1);
        }
    }

    public void setTerminal(Label terminal) {
        this.terminal = terminal;
    }

    public void setTheOnlyLAnd(boolean theOnlyLAnd) {
        this.theOnlyLAnd = theOnlyLAnd;
    }

    public void setStart(Label start) {
        this.start = start;
    }

    public Operand genMidCode() {
        for (EqExp eqExp : eqExps) {
            eqExp.setTerminal(terminal);
            if (!theOnlyLAnd && eqExps.size() == 1) {
                eqExp.setWhenBranch(true);
                eqExp.setTerminal(start);
            }
            eqExp.genMidCode();
        }
        if (!theOnlyLAnd && eqExps.size() > 1) {
            Jump jump = new Jump(start);
            writeInMidCode(jump);
        }
        return null;
    }
}
