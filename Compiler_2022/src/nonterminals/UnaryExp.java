package nonterminals;

import mcode.Arithmetic;
import mcode.Branch;
import mcode.FuncCall;
import mcode.Label;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import mcode.operand.RegisterOperand;
import mcode.operand.RetValueOperand;
import symboltabel.FuncSymbol;
import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

public class UnaryExp extends Nonterminal implements Calculable {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        Word type = la.readSym().getType();
        if (type == Word.PLUS || type == Word.MINU || type == Word.NOT) {
            UnaryOp unaryOp = new UnaryOp();
            unaryOp.parse();
            putIn(unaryOp);
            UnaryExp unaryExp = new UnaryExp();
            unaryExp.parse();
            putIn(unaryExp);
        }
        else if (type == Word.IDENFR) {
            Symbol ident = la.readSym();
            putIn(la.readSym());
            if (la.nextSym().getType() == Word.LPARENT) {
                FuncSymbol funcSymbol = getSymbolTable().getFunc(ident);
                putIn(la.readSym());
                if (la.nextSym().getType() == Word.RPARENT) {
                    putIn(la.readSym());
                    la.nextSym();
                    if (funcSymbol != null && funcSymbol.getParamLen() != 0) {
                        MyIO.writeToErrorBuffer(ident.getLine(), "d");
                    }
                } else {
                    int pointer = la.getSymPointer();
                    FuncRParams funcRParams = new FuncRParams();
                    try {
                        funcRParams.parse();
                    } catch (Exception e) {
                        la.setSymPointer(pointer);
                        MyIO.writeToErrorBuffer(la.preSym().getLine(), "j");
                        return;
                    }
                    putIn(funcRParams);
                    if (funcSymbol != null) {
                        funcSymbol.checkParam(funcRParams, ident.getLine());
                    }
                    if (la.readSym().getType() == Word.RPARENT) {
                        putIn(la.readSym());
                        la.nextSym();
                    }
                    else {
                        MyIO.writeToErrorBuffer(la.preSym().getLine(), "j");
                    }
                }
            }
            else {
                la.fallBack(1);
                remove(size() - 1);
                PrimaryExp primaryExp = new PrimaryExp();
                primaryExp.parse();
                putIn(primaryExp);
            }
        }
        else {
            PrimaryExp primaryExp = new PrimaryExp();
            primaryExp.parse();
            putIn(primaryExp);
        }
    }

    public int calculate() {
        if (get(0) instanceof UnaryOp) {
            if (((UnaryOp) get(0)).getType() == Word.PLUS) {
                return ((UnaryExp) get(1)).calculate();
            }
            else if (((UnaryOp) get(0)).getType() == Word.MINU) {
                return -((UnaryExp) get(1)).calculate();
            }
            else {
                return 0;
                //System.err.println("error");
            }
        }
        else if (get(0) instanceof Symbol &&
                ((Symbol) get(0)).getType() == Word.IDENFR) {
            return 0;
            //System.err.println("error");
        }
        else {
            return ((PrimaryExp) get(0)).calculate();
        }
    }

    public int getDimension() {
        if (get(0) instanceof UnaryOp) {
            return 0;
        }
        else if (get(0) instanceof Symbol) {
            FuncSymbol funcSymbol = getSymbolTable().getFunc((Symbol) get(0));
            if (funcSymbol.getReturnMode() == Word.VOIDTK) {
                return -1;
            }
            else {
                return 0;
            }
        }
        else {
            return ((PrimaryExp) get(0)).getDimension();
        }
    }

    public Operand genMidCode() {
        if (get(0) instanceof UnaryOp) {
            if (((UnaryOp) get(0)).getType() == Word.PLUS) {
                Operand operand = ((UnaryExp) get(1)).genMidCode();
                operand.setNegative(operand.isNegative());
                return operand;
            }
            else if (((UnaryOp) get(0)).getType() == Word.MINU) {
                Operand operand = ((UnaryExp) get(1)).genMidCode();
                if (operand instanceof ImmediateOperand) {
                    ((ImmediateOperand) operand).reversal();
                    return operand;
                }
                operand.setNegative(!operand.isNegative());
                return operand;
            }
            else {
                Operand operand = ((UnaryExp) get(1)).genMidCode();
                if (operand instanceof ImmediateOperand) {
                    ((ImmediateOperand) operand).boolReversal();
                    return operand;
                }
                Operand tmp = alloc();
                Arithmetic arithmetic = new Arithmetic('+', new RegisterOperand(),
                        new ImmediateOperand(0), tmp);
                writeInMidCode(arithmetic);
                Label end = new Label();
                Branch branch = new Branch(false, operand, new RegisterOperand(), end);
                writeInMidCode(branch);
                arithmetic = new Arithmetic('+', new RegisterOperand(),
                        new ImmediateOperand(1), tmp);
                writeInMidCode(arithmetic);
                writeInMidCode(end);
                return tmp;
            }
        }
        else if (get(0) instanceof Symbol &&
                ((Symbol) get(0)).getType() == Word.IDENFR) {
            if (get(2) instanceof FuncRParams) {
                ((FuncRParams) get(2)).genMidCode();
            }
            FuncCall call = new FuncCall((Symbol) get(0));
            writeInMidCode(call);
            Operand target = alloc();
            Arithmetic arithmetic = new Arithmetic('+', new RetValueOperand(),
                    new ImmediateOperand(0), target);
            writeInMidCode(arithmetic);
            return target;
        }
        else {
            return ((PrimaryExp) get(0)).genMidCode();
        }
    }

    public boolean containsFunc() {
        if (get(0) instanceof UnaryOp) {
            return ((UnaryExp) get(1)).containsFunc();
        }
        if (get(0) instanceof Symbol &&
                ((Symbol) get(0)).getType() == Word.IDENFR) {
            return true;
        }
        return ((PrimaryExp) get(0)).containsFunc();
    }
}
