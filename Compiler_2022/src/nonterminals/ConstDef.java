package nonterminals;

import mcode.ArrayStore;
import mcode.Declare;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import symboltabel.VarSymbol;
import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

public class ConstDef extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() != Word.IDENFR) {
            throw new Exception();
        }
        else {
            putIn(la.readSym());
        }
        VarSymbol varSymbol = new VarSymbol(la.readSym(), true);
        while (la.nextSym().getType() == Word.LBRACK) {
            putIn(la.readSym());
            la.nextSym();
            ConstExp constExp = new ConstExp();
            constExp.parse();
            putIn(constExp);
            varSymbol.addDimension(constExp);
            if (la.readSym().getType() != Word.RBRACK) {
                MyIO.writeToErrorBuffer(la.preSym().getLine(), "k");
                la.fallBack(1);
            } else {
                putIn(la.readSym());
            }
        }
        getSymbolTable().addSymbol(varSymbol);
        if (la.readSym().getType() == Word.ASSIGN) {
            putIn(la.readSym());
            la.nextSym();
            ConstInitVal constInitVal = new ConstInitVal();
            constInitVal.parse();
            putIn(constInitVal);
            constInitVal.fill(varSymbol);
            genMidCode();
        }
        else {
            throw new Exception();
        }
    }

    private void genMidCode() {
        VarSymbol varSymbol = getSymbolTable().getVar((Symbol) get(0));
        int length = varSymbol.getLen();
        if (length == 1) {
            return;
        }
        writeInMidCode(new Declare(varSymbol));
        for (int i = 0; i < length; i++) {
            Operand source = new ImmediateOperand(varSymbol.getValue(i));
            ArrayStore arrayStore = new ArrayStore((Symbol) get(0),
                    new ImmediateOperand(i), source);
            writeInMidCode(arrayStore);
        }
    }
}
