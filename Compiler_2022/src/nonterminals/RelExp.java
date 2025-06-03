package nonterminals;

import mcode.Arithmetic;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import tools.LexicalAnalyzer;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;

public class RelExp extends LeftRecursion {
    private final ArrayList<AddExp> addExps = new ArrayList<>();
    private final ArrayList<Symbol> operators = new ArrayList<>();

    public void parse() throws Exception {
        AddExp addExp = new AddExp();
        addExp.parse();
        putIn(addExp);
        addExps.add(addExp);
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (la.readSym().getType() == Word.LSS || la.readSym().getType() == Word.LEQ ||
                la.readSym().getType() == Word.GRE || la.readSym().getType() == Word.GEQ) {
            putIn(la.readSym());
            operators.add(la.readSym());
            la.nextSym();
            AddExp addExp1 = new AddExp();
            addExp1.parse();
            putIn(addExp1);
            addExps.add(addExp1);
        }
    }

    public Operand genMidCode() {
        if (size() == 1) {
            return addExps.get(0).genMidCode();
        }
        else {
            Operand operandA = addExps.get(0).genMidCode();
            for (int i = 1; i < addExps.size(); i++) {
                if (operandA.isInverted()) {
                    free(operandA);
                    Operand operandTmp = operandA;
                    operandA = alloc();
                    Arithmetic arithmetic = new Arithmetic('^', operandTmp,
                            new ImmediateOperand(1), operandA);
                    writeInMidCode(arithmetic);
                }
                Operand operandB = addExps.get(i).genMidCode();
                free(operandA, operandB);
                Operand operandTarget = alloc();
                Arithmetic arithmetic = new Arithmetic(operators.get(i - 1),
                        operandA, operandB, operandTarget);
                writeInMidCode(arithmetic);
                operandA = operandTarget;
            }
            return operandA;
        }
    }
}
