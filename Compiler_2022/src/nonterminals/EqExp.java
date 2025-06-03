package nonterminals;

import mcode.Arithmetic;
import mcode.Branch;
import mcode.Jump;
import mcode.Label;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import mcode.operand.RegisterOperand;
import tools.LexicalAnalyzer;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;

public class EqExp extends LeftRecursion {
    private final ArrayList<RelExp> relExps = new ArrayList<>();
    private final ArrayList<Symbol> operators = new ArrayList<>();
    private Label terminal;
    private boolean whenBranch;

    public void parse() throws Exception {
        RelExp relExp = new RelExp();
        relExp.parse();
        putIn(relExp);
        relExps.add(relExp);
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (la.readSym().getType() == Word.EQL ||
                la.readSym().getType() == Word.NEQ) {
            putIn(la.readSym());
            operators.add(la.readSym());
            la.nextSym();
            RelExp relExp1 = new RelExp();
            relExp1.parse();
            putIn(relExp1);
            relExps.add(relExp1);
        }
    }

    public void setTerminal(Label terminal) {
        this.terminal = terminal;
    }

    public void setWhenBranch(boolean whenBranch) {
        this.whenBranch = whenBranch;
    }

    public Operand genMidCode() {
        if (relExps.size() == 1) {
            Operand operand = relExps.get(0).genMidCode();
            free(operand);
            boolean cond = !whenBranch ^ operand.isInverted();
            if (operand instanceof ImmediateOperand) {
                if (whenBranch != (((ImmediateOperand) operand).getValue() == 0)) {
                    Jump jump = new Jump(terminal);
                    writeInMidCode(jump);
                }
                return null;
            }
            Branch branch = new Branch(cond, operand, new ImmediateOperand(0), terminal);
            writeInMidCode(branch);
        }
        else {
            Operand operandA = relExps.get(0).genMidCode();
            for (int i = 1; i < relExps.size(); i++) {
                Operand operandB = relExps.get(i).genMidCode();
                if (operandA.isInverted() != operandB.isInverted() && i == 1) {
                    if (operandA.isInverted()) {
                        free(operandA);
                        Operand operandTmp = operandA;
                        operandA = alloc();
                        Arithmetic arithmetic = new Arithmetic('^', operandTmp,
                                new ImmediateOperand(1), operandA);
                        writeInMidCode(arithmetic);
                    }
                    else {
                        free(operandB);
                        Operand operandTmp = operandB;
                        operandB = alloc();
                        Arithmetic arithmetic = new Arithmetic('^', operandTmp,
                                new ImmediateOperand(1), operandB);
                        writeInMidCode(arithmetic);
                    }
                }
                if (i == relExps.size() - 1) {
                    free(operandA, operandB);
                    boolean cond = !whenBranch ^ (operators.get(i - 1).getContent().equals("==")) ^
                            operandA.isInverted() ^ operandB.isInverted();
                    Branch branch = new Branch(cond, operandA, operandB, terminal);
                    writeInMidCode(branch);
                }
                else {
                    Operand operandTarget = alloc();
                    Arithmetic arithmetic = new Arithmetic('+', new RegisterOperand(),
                            new ImmediateOperand(0), operandTarget);
                    writeInMidCode(arithmetic);
                    free(operandA, operandB);
                    boolean cond = !operators.get(i - 1).getContent().equals("==") ^
                            operandA.isInverted() ^ operandB.isInverted();
                    Label label = new Label();
                    Branch branch = new Branch(cond, operandA, operandB, label);
                    writeInMidCode(branch);
                    arithmetic = new Arithmetic('+', new RegisterOperand(),
                            new ImmediateOperand(1), operandTarget);
                    writeInMidCode(arithmetic);
                    writeInMidCode(label);
                    operandA = operandTarget;
                }
            }
        }
        return null;
    }
}
