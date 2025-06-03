package nonterminals;

import mcode.Arithmetic;
import mcode.ArrayLoad;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import symboltabel.VarSymbol;
import tools.Item;
import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;

public class LVal extends Nonterminal implements Calculable {
    private Symbol ident;
    private final ArrayList<Exp> indexes = new ArrayList<>();

    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() == Word.IDENFR) {
            putIn(la.readSym());
            ident = la.readSym();
            getSymbolTable().getVar(la.readSym());
            while (la.nextSym().getType() == Word.LBRACK) {
                putIn(la.readSym());
                la.nextSym();
                Exp exp = new Exp();
                exp.parse();
                putIn(exp);
                indexes.add(exp);
                if (la.readSym().getType() == Word.RBRACK) {
                    putIn(la.readSym());
                }
                else {
                    MyIO.writeToErrorBuffer(la.preSym().getLine(), "k");
                    la.fallBack(1);
                }
            }
        }
        else {
            throw new Exception();
        }
    }

    public int getDimension() {
        int indexDimension = 0;
        for (Item item : getContents()) {
            if (item instanceof Exp) {
                indexDimension++;
            }
        }
        VarSymbol varSymbol = getSymbolTable().getVar((Symbol) get(0), false);
        if (varSymbol == null) {
            return Integer.MIN_VALUE;
        }
        return varSymbol.getDimension() - indexDimension;
    }

    public int calculate() {
        if (size() == 1) {
            return getSymbolTable().getVar((Symbol) get(0)).getValue(0);
        }
        if (size() == 4) {
            int index = ((Exp) get(2)).calculate();
            return getSymbolTable().getVar((Symbol) get(0)).getValue(index);
        }
        else {
            int indexRow = ((Exp) get(2)).calculate();
            int indexCol = ((Exp) get(5)).calculate();
            return getSymbolTable().getVar((Symbol) get(0)).getValue(indexRow, indexCol);
        }
    }

    public Operand genMidCode() {
        VarSymbol varSymbol = getSymbolTable().getVar(ident);
        if (indexes.size() == 0 && varSymbol.getDimension() == 0) {
            if (varSymbol.isConst()) {
                return new ImmediateOperand(varSymbol.getValue(0));
            }
            else {
                Operand operand = alloc();
                ArrayLoad arrayLoad = new ArrayLoad(varSymbol.getIdent(), new ImmediateOperand(0),
                        operand);
                writeInMidCode(arrayLoad);
                return operand;
            }
        }
        if (indexes.size() < varSymbol.getDimension()) {
            if (indexes.size() == 0) {
                Operand target = alloc();
                Arithmetic arithmetic = new Arithmetic('+', varSymbol,
                        new ImmediateOperand(0), target);
                writeInMidCode(arithmetic);
                return target;
            }
            else {
                Operand index = indexes.get(0).genMidCode();
                free(index);
                Operand offset = alloc();
                Arithmetic arithmetic = new Arithmetic('*', index,
                        new ImmediateOperand(4 * varSymbol.getRowLen()), offset);
                writeInMidCode(arithmetic);
                free(offset);
                Operand target = alloc();
                arithmetic = new Arithmetic('+', varSymbol, offset, target);
                writeInMidCode(arithmetic);
                return target;
            }
        }
        Operand index;
        VarSymbol var = getSymbolTable().getVar(ident);
        if (indexes.size() == 1) {
            index = indexes.get(0).genMidCode();
            if (index instanceof ImmediateOperand && var.isConst()) {
                int indexValue = ((ImmediateOperand) index).getValue();
                return new ImmediateOperand(var.getValue(indexValue));
            }
        }
        else {
            Operand indexRow = indexes.get(0).genMidCode();
            Operand indexCol = indexes.get(1).genMidCode();
            if (indexRow instanceof ImmediateOperand && indexCol instanceof ImmediateOperand &&
                    var.isConst()) {
                int rows = ((ImmediateOperand) indexRow).getValue();
                int cols = ((ImmediateOperand) indexCol).getValue();
                return new ImmediateOperand(var.getValue(rows, cols));
            }
            free(indexRow);
            Operand rowOffset = alloc();
            Operand rowLen = new ImmediateOperand(var.getRowLen());
            Arithmetic arithmetic = new Arithmetic('*', indexRow, rowLen, rowOffset);
            writeInMidCode(arithmetic);
            free(indexCol, rowOffset);
            index = alloc();
            arithmetic = new Arithmetic('+', rowOffset, indexCol, index);
            writeInMidCode(arithmetic);
        }
        free(index);
        Operand operandTarget = alloc();
        ArrayLoad arrayLoad = new ArrayLoad(ident, index, operandTarget);
        writeInMidCode(arrayLoad);
        return operandTarget;
    }

    public ArrayList<Operand> genMidCodeForSource() {
        VarSymbol varSymbol = getSymbolTable().getVar(ident);
        ArrayList<Operand> operands = new ArrayList<>();
        if (indexes.size() == 0) {
            operands.add(varSymbol);
            operands.add(new ImmediateOperand(0));
            return operands;
        }
        Operand index;
        if (indexes.size() == 1) {
            index = indexes.get(0).genMidCode();
        }
        else {
            Operand indexRow = indexes.get(0).genMidCode();
            free(indexRow);
            Operand rowOffset = alloc();
            Operand rowLen = new ImmediateOperand(getSymbolTable().getVar(ident).getRowLen());
            Arithmetic arithmetic = new Arithmetic('*', indexRow, rowLen, rowOffset);
            writeInMidCode(arithmetic);

            Operand indexCol = indexes.get(1).genMidCode();
            free(indexCol, rowOffset);
            index = alloc();
            arithmetic = new Arithmetic('+', rowOffset, indexCol, index);
            writeInMidCode(arithmetic);
        }
        operands.add(varSymbol);
        operands.add(index);
        return operands;
    }
}
