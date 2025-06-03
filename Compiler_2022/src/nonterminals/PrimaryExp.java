package nonterminals;

import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import tools.LexicalAnalyzer;
import tools.Symbol;
import tools.Word;

public class PrimaryExp extends Nonterminal implements Calculable {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        Word type = la.readSym().getType();
        if (type == Word.LPARENT) {
            putIn(la.readSym());
            la.nextSym();
            Exp exp = new Exp();
            exp.parse();
            putIn(exp);
            if (la.readSym().getType() == Word.RPARENT) {
                putIn(la.readSym());
                la.nextSym();
            }
            else {
                throw new Exception();
            }
        }
        else if (type == Word.IDENFR) {
            LVal lval = new LVal();
            lval.parse();
            putIn(lval);
        }
        else if (type == Word.INTCON) {
            Number number = new Number();
            number.parse();
            putIn(number);
        }
        else {
            System.out.println(la.readSym().getContent());
            // throw new Exception();
        }
    }

    public int calculate() {
        if (get(0) instanceof Symbol &&
                ((Symbol) get(0)).getType() == Word.LPARENT) {
            return ((Exp) get(1)).calculate();
        }
        else if (get(0) instanceof Number) {
            return ((Number) get(0)).calculate();
        }
        //LVal
        else {
            return ((LVal) get(0)).calculate();
        }
    }

    public int getDimension() {
        if (get(0) instanceof Symbol) {
            return ((Exp) get(1)).getDimension();
        }
        else if (get(0) instanceof Number) {
            return 0;
        }
        else {
            return ((LVal) get(0)).getDimension();
        }
    }

    public Operand genMidCode() {
        if (get(0) instanceof Symbol) {
            return ((Exp) get(1)).genMidCode();
        }
        else if (get(0) instanceof Number) {
            return new ImmediateOperand((Number) get(0));
        }
        else {
            return ((LVal) get(0)).genMidCode();
        }
    }

    public boolean containsFunc() {
        if (get(0) instanceof Symbol) {
            return ((Exp) get(1)).containsFunc();
        }
        return false;
    }
}
