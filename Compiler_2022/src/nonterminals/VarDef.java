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

import java.util.ArrayList;

public class VarDef extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() == Word.IDENFR) {
            putIn(la.readSym());
            VarSymbol varSymbol = new VarSymbol(la.readSym(), false);
            while (la.nextSym().getType() == Word.LBRACK) {
                putIn(la.readSym());
                la.nextSym();
                ConstExp constExp = new ConstExp();
                constExp.parse();
                putIn(constExp);
                varSymbol.addDimension(constExp);
                if (la.readSym().getType() == Word.RBRACK) {
                    putIn(la.readSym());
                }
                else {
                    MyIO.writeToErrorBuffer(la.preSym().getLine(), "k");
                    la.fallBack(1);
                }
            }
            getSymbolTable().addSymbol(varSymbol);
            if (la.readSym().getType() == Word.ASSIGN) {
                putIn(la.readSym());
                la.nextSym();
                InitVal initVal = new InitVal();
                initVal.parse();
                putIn(initVal);
            }
            genMidCode();
        }
        else {
            System.out.println(la.readSym().getLine());
            throw new Exception();
        }
    }

    private void genMidCode() {
        VarSymbol varSymbol = getSymbolTable().getVar((Symbol) get(0));
        writeInMidCode(new Declare(varSymbol));
        boolean hasInitVal = false;
        ArrayList<Exp> initVal = new ArrayList<>();
        if ((get(size() - 1) instanceof InitVal)) {
            hasInitVal = true;
            initVal = ((InitVal) get(size() - 1)).getInitList();
        }
        if (hasInitVal) {
            int length = varSymbol.getLen();
            for (int i = 0; i < length; i++) {
                Operand source = initVal.get(i).genMidCode();
                free(source);
                ArrayStore arrayStore = new ArrayStore((Symbol) get(0),
                        new ImmediateOperand(i), source);
                writeInMidCode(arrayStore);
            }
        }
        else {
            if (varSymbol.isGlobal() && varSymbol.getLen() == 1) {
                writeInMidCode(new ArrayStore((Symbol) get(0), new ImmediateOperand(0),
                        new ImmediateOperand(0)));
            }
        }
    }
}
