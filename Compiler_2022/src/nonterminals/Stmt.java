package nonterminals;

import mcode.Arithmetic;
import mcode.ArrayStore;
import mcode.Exit;
import mcode.Jump;
import mcode.Label;
import mcode.Output;
import mcode.Return;
import mcode.operand.CharOperand;
import mcode.operand.GetIntOperand;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import mcode.operand.RetValueOperand;
import symboltabel.VarSymbol;
import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

import java.util.ArrayList;

public class Stmt extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        Word type = la.readSym().getType();
        Symbol symbol = la.readSym();
        if (type == Word.IDENFR) {
            final int pointer = la.getSymPointer();
            MyIO.closeErrorBuffer();
            LVal lval = new LVal();
            lval.parse();
            MyIO.openErrorBuffer();
            Symbol sym = la.readSym();
            la.setSymPointer(pointer);
            if (sym.getType() == Word.ASSIGN) {
                LVal val1 = new LVal();
                val1.parse();
                Symbol ident = (Symbol) val1.get(0);
                VarSymbol varSymbol = getSymbolTable().getVar(ident, false);
                if (varSymbol != null && varSymbol.isConst()) {
                    MyIO.writeToErrorBuffer(ident.getLine(), "h");
                }
                putIn(val1);
                putIn(la.readSym());
                if (la.nextSym().getType() == Word.GETINTTK) {
                    handleGetInt(val1);
                }
                else {
                    Exp exp = new Exp();
                    exp.parse();
                    putIn(exp);
                    if (la.readSym().getType() == Word.SEMICN) {
                        putIn(la.readSym());
                        la.nextSym();
                    } else {
                        MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
                    }
                    ArrayList<Operand> pair = val1.genMidCodeForSource();
                    Operand operandSource = exp.genMidCode();
                    free(pair.get(1), operandSource);
                    ArrayStore arrayStore = new ArrayStore(((VarSymbol)pair.get(0)).getIdent(),
                            pair.get(1), operandSource);
                    writeInMidCode(arrayStore);
                }
            }
            else {
                Exp exp = new Exp();
                exp.parse();
                putIn(exp);
                if (exp.containsFunc()) {
                    exp.genMidCode();
                }
                if (la.readSym().getType() == Word.SEMICN) {
                    putIn(la.readSym());
                    la.nextSym();
                } else {
                    MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
                }
            }
        } else {
            handleOthers(symbol);
        }
    }

    private void handleGetInt(LVal lval) throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        putIn(la.readSym());
        if (la.nextSym().getType() == Word.LPARENT) {
            putIn(la.readSym());
        } else {
            throw new Exception();
        }
        if (la.nextSym().getType() == Word.RPARENT) {
            putIn(la.readSym());
        } else {
            MyIO.writeToErrorBuffer(la.preSym().getLine(), "j");
            la.fallBack(1);
        }
        if (la.nextSym().getType() == Word.SEMICN) {
            putIn(la.readSym());
        } else {
            MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
            return;
        }
        la.nextSym();
        ArrayList<Operand> pair = lval.genMidCodeForSource();
        free(pair.get(1));
        ArrayStore arrayStore = new ArrayStore(((VarSymbol) pair.get(0)).getIdent(), pair.get(1),
                new GetIntOperand());
        writeInMidCode(arrayStore);
    }

    private void handleOthers(Symbol symbol) throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        switch (symbol.getType()) {
            case SEMICN: {
                putIn(la.readSym());
                la.nextSym();
                break;
            } case LBRACE: {
                getSymbolTable().addPile();
                Block block = new Block();
                block.parse();
                putIn(block);
                getSymbolTable().removePile();
                break;
            } case IFTK: {
                handleIW("if");
                break;
            } case WHILETK: {
                handleIW("while");
                break;
            } case BREAKTK:
            case CONTINUETK: {
                if (!getSymbolTable().isInLoop()) {
                    MyIO.writeToErrorBuffer(symbol.getLine(), "m");
                }
                putIn(la.readSym());
                if (la.nextSym().getType() == Word.SEMICN) {
                    putIn(la.readSym());
                    la.nextSym();
                } else {
                    MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
                }
                Jump jump;
                if (symbol.getType() == Word.CONTINUETK) {
                    jump = new Jump(getSymbolTable().getCurLoopStart());
                }
                else {
                    jump = new Jump(getSymbolTable().getCurLoopEnd());
                }
                writeInMidCode(jump);
                break;
            } case RETURNTK: {
                handleReturn();
                break;
            } case PRINTFTK: {
                handlePrintf();
                break;
            } default: {
                Exp exp = new Exp();
                exp.parse();
                putIn(exp);
                if (exp.containsFunc()) {
                    exp.genMidCode();
                }
                if (la.readSym().getType() == Word.SEMICN) {
                    putIn(la.readSym());
                    la.nextSym();
                } else {
                    MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
                }
            }
        }
    }

    private void handleIW(String s) throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        putIn(la.readSym());
        if (la.nextSym().getType() == Word.LPARENT) {
            putIn(la.readSym());
        }
        else {
            throw new Exception();
        }
        Label loop = new Label("loop");
        if (s.equals("while")) {
            writeInMidCode(loop);
        }
        la.nextSym();
        Cond cond = new Cond();
        cond.parse();
        putIn(cond);
        Label end = (Label) cond.genMidCode();
        if (s.equals("while")) {
            getSymbolTable().pushLoopLabel(loop, end);
            end.setName("endloop");
        }
        if (la.readSym().getType() == Word.RPARENT) {
            putIn(la.readSym());
            la.nextSym();
        }
        else {
            MyIO.writeToErrorBuffer(la.preSym().getLine(), "j");
        }
        Stmt stmt = new Stmt();
        stmt.parse();
        putIn(stmt);
        if (s.equals("while")) {
            Jump jump = new Jump(loop);
            writeInMidCode(jump);
            getSymbolTable().popLoopLabel();
        }
        if (la.readSym().getType() == Word.ELSETK && s.equals("if")) {
            Label end2 = new Label();
            Jump jump = new Jump(end2);
            writeInMidCode(jump);
            writeInMidCode(end);
            putIn(la.readSym());
            la.nextSym();
            Stmt stmt1 = new Stmt();
            stmt1.parse();
            putIn(stmt1);
            writeInMidCode(end2);
        }
        else {
            writeInMidCode(end);
        }
    }

    private void handleReturn() {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        Symbol ret = la.readSym();
        putIn(la.readSym());
        if (la.nextSym().getType() == Word.SEMICN) {
            putIn(la.readSym());
            la.nextSym();
            writeInMidCode(new Return());
        }
        else {
            int symPoint = la.getSymPointer();
            Exp exp = new Exp();
            try {
                exp.parse();
            } catch (Exception e) {
                la.setSymPointer(symPoint);
                MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
                return;
            }
            if (getSymbolTable().getFuncType() == Word.VOIDTK) {
                MyIO.writeToErrorBuffer(ret.getLine(), "f");
            }
            putIn(exp);
            Operand operand = exp.genMidCode();
            free(operand);
            Arithmetic arithmetic = new Arithmetic('+', operand,
                    new ImmediateOperand(0), new RetValueOperand());
            if (!getSymbolTable().isInMainFunc()) {
                writeInMidCode(arithmetic);
                writeInMidCode(new Return());
            }
            else {
                writeInMidCode(new Exit());
            }
            if (la.readSym().getType() == Word.SEMICN) {
                putIn(la.readSym());
                la.nextSym();
            }
            else {
                MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
            }
        }
    }

    private void handlePrintf() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        Symbol printf = la.readSym();
        putIn(la.readSym());
        if (la.nextSym().getType() == Word.LPARENT) {
            putIn(la.readSym());
        } else {
            throw new Exception();
        }
        if (la.nextSym().getType() == Word.STRCON) {
            putIn(la.readSym());
        } else {
            throw new Exception();
        }
        int formatChars = la.readSym().numOfFormatChar();
        final char[] chars = la.readSym().getContent().replace("%d", "$")
                .replace("\"", "").toCharArray();
        int exps = 0;
        la.nextSym();
        ArrayList<Exp> expArrayList = new ArrayList<>();
        while (la.readSym().getType() == Word.COMMA) {
            putIn(la.readSym());
            la.nextSym();
            Exp exp = new Exp();
            exp.parse();
            putIn(exp);
            expArrayList.add(exp);
            exps++;
        }
        if (formatChars != exps) {
            MyIO.writeToErrorBuffer(printf.getLine(), "l");
        }
        if (la.readSym().getType() == Word.RPARENT) {
            putIn(la.readSym());
        } else {
            MyIO.writeToErrorBuffer(la.preSym().getLine(), "j");
            la.fallBack(1);
        }
        if (la.nextSym().getType() == Word.SEMICN) {
            putIn(la.readSym());
        } else {
            MyIO.writeToErrorBuffer(la.preSym().getLine(), "i");
            return;
        }
        int expIndex = 0;
        ArrayList<Output> outputs = new ArrayList<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            if (c == '$') {
                Operand operand = expArrayList.get(expArrayList.size() - 1 - expIndex).genMidCode();
                Output output = new Output(operand);
                expIndex++;
                outputs.add(output);
            }
            else {
                Output output = new Output(new CharOperand(c));
                outputs.add(output);
            }
        }
        for (int i = outputs.size() - 1; i >= 0; i--) {
            writeInMidCode(outputs.get(i));
        }
        la.nextSym();
    }
}
