package nonterminals;

import mcode.Arithmetic;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import mcode.operand.RegisterOperand;
import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;
import java.util.Iterator;

public class AddExp extends LeftRecursion implements Calculable {
    private final ArrayList<MulExp> mulExps = new ArrayList<>();
    private final ArrayList<Symbol> operators = new ArrayList<>();

    public void parse() throws Exception {
        MulExp mulExp = new MulExp();
        mulExp.parse();
        putIn(mulExp);
        mulExps.add(mulExp);
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (la.readSym().getType() == Word.PLUS ||
                la.readSym().getType() == Word.MINU) {
            putIn(la.readSym());
            operators.add(la.readSym());
            la.nextSym();
            MulExp mulExp1 = new MulExp();
            mulExp1.parse();
            putIn(mulExp1);
            mulExps.add(mulExp1);
        }
        operators.add(0, new Symbol(0, Word.PLUS, "+"));
    }

    public int calculate() {
        int sum = ((MulExp) get(0)).calculate();
        int index = 1;
        while (index < size()) {
            int next = ((MulExp) get(index + 1)).calculate();
            if (((Symbol) get(index)).getType() == Word.PLUS) {
                sum += next;
            }
            else {
                sum -= next;
            }
            index += 2;
        }
        return sum;
    }

    public int getDimension() {
        if (size() > 1) {
            return 0;
        }
        else {
            return ((MulExp) get(0)).getDimension();
        }
    }

    public Operand genMidCode() {
        ArrayList<Operand> operands = new ArrayList<>();
        boolean flag = MyIO.isMidCodeBufferOn();
        MyIO.closeMidCodeBuffer();
        for (MulExp mulExp : mulExps) {
            operands.add(mulExp.genMidCode());
        }
        if (flag) {
            MyIO.openMidCodeBuffer();
        }
        int sum = 0;
        if (flag) {
            Iterator<Operand> iterator = operands.iterator();
            Iterator<Symbol> symbolIterator = operators.iterator();
            Iterator<MulExp> mulExpIterator = mulExps.iterator();
            while (iterator.hasNext()) {
                Operand operand = iterator.next();
                Symbol symbol = symbolIterator.next();
                mulExpIterator.next();
                if (operand instanceof ImmediateOperand) {
                    int val = ((ImmediateOperand) operand).getValue();
                    if (operand.isNegative() == (symbol.getType() == Word.PLUS)) {
                        sum -= val;
                    }
                    else {
                        sum += val;
                    }
                    iterator.remove();
                    symbolIterator.remove();
                    mulExpIterator.remove();
                }
            }
            if (operands.isEmpty()) {
                return new ImmediateOperand(sum);
            }
        }

        Operand operandA = mulExps.get(0).genMidCode();
        if (operandA.isNegative() == (operators.get(0).getType() == Word.PLUS)) {
            free(operandA);
            RegisterOperand operand = new RegisterOperand();
            Operand operandTarget = alloc();
            Arithmetic arithmetic = new Arithmetic('-', operand, operandA, operandTarget);
            writeInMidCode(arithmetic);
            operandA = operandTarget;
        }
        if (mulExps.size() != 1) {
            for (int i = 1; i < mulExps.size(); i++) {
                Operand operandB = mulExps.get(i).genMidCode();
                free(operandA, operandB);
                Operand operandTarget = alloc();
                Arithmetic arithmetic = new Arithmetic(operators.get(i),
                        operandA, operandB, operandTarget);
                if (operandB.isNegative()) {
                    arithmetic.reversalOperator();
                }
                writeInMidCode(arithmetic);
                operandA = operandTarget;
            }
        }
        if (sum != 0) {
            Operand operandTarget = alloc();
            Arithmetic arithmetic = new Arithmetic('+',
                    operandA, new ImmediateOperand(sum), operandTarget);
            writeInMidCode(arithmetic);
            operandA = operandTarget;
        }
        return operandA;
    }

    public boolean containsFunc() {
        for (MulExp mulExp : mulExps) {
            if (mulExp.containsFunc()) {
                return true;
            }
        }
        return false;
    }
}
