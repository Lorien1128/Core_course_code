package nonterminals;

import mcode.Arithmetic;
import mcode.operand.Operand;
import tools.LexicalAnalyzer;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;

public class MulExp extends LeftRecursion implements Calculable {
    private final ArrayList<UnaryExp> unaryExps = new ArrayList<>();
    private final ArrayList<Symbol> operators = new ArrayList<>();

    public void parse() throws Exception {
        UnaryExp unaryExp = new UnaryExp();
        unaryExp.parse();
        putIn(unaryExp);
        unaryExps.add(unaryExp);
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (la.readSym().getType() == Word.MULT || la.readSym().getType() == Word.DIV
                || la.readSym().getType() == Word.MOD) {
            putIn(la.readSym());
            operators.add(la.readSym());
            la.nextSym();
            UnaryExp unaryExp1 = new UnaryExp();
            unaryExp1.parse();
            putIn(unaryExp1);
            unaryExps.add(unaryExp1);
        }
    }

    public int calculate() {
        int prod = ((UnaryExp) get(0)).calculate();
        int index = 1;
        while (index < size()) {
            int next = ((UnaryExp) get(index + 1)).calculate();
            if (((Symbol) get(index)).getType() == Word.MULT) {
                prod *= next;
            }
            else if (((Symbol) get(index)).getType() == Word.DIV) {
                prod /= next;
            }
            else {
                prod %= next;
            }
            index += 2;
        }
        return prod;
    }

    public int getDimension() {
        if (size() > 1) {
            return 0;
        }
        else {
            return ((UnaryExp) get(0)).getDimension();
        }
    }

    public Operand genMidCode() {
        if (size() == 1) {
            return unaryExps.get(0).genMidCode();
        }
        else {
            Operand operandA = unaryExps.get(0).genMidCode();
            for (int i = 1; i < unaryExps.size(); i++) {
                Operand operandB = unaryExps.get(i).genMidCode();
                free(operandA, operandB);
                Operand operandTarget = alloc();
                operandTarget.setNegative(operandA.isNegative() ^ operandB.isNegative());
                Arithmetic arithmetic = new Arithmetic(operators.get(i - 1),
                        operandA, operandB, operandTarget);
                writeInMidCode(arithmetic);
                operandA = operandTarget;
            }
            return operandA;
        }
        // TODO: 2022/11/10 全为常量直接计算
    }

    public boolean containsFunc() {
        for (UnaryExp unaryExp : unaryExps) {
            if (unaryExp.containsFunc()) {
                return true;
            }
        }
        return false;
    }
}
