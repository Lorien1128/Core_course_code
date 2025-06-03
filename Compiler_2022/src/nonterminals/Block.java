package nonterminals;

import mcode.Return;
import tools.LexicalAnalyzer;
import tools.MyIO;
import tools.Symbol;
import tools.Word;

public class Block extends Nonterminal {
    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        if (la.readSym().getType() == Word.LBRACE) {
            putIn(la.readSym());
            la.nextSym();
            while (true) {
                Word type = la.readSym().getType();
                if (type == Word.CONSTTK) {
                    ConstDecl constDecl = new ConstDecl();
                    constDecl.parse();
                    putIn(constDecl);
                }
                else if (type == Word.INTTK) {
                    VarDecl varDecl = new VarDecl();
                    varDecl.parse();
                    putIn(varDecl);
                }
                else if (type == Word.RBRACE) {
                    putIn(la.readSym());
                    la.nextSym();
                    break;
                }
                else {
                    Stmt stmt = new Stmt();
                    stmt.parse();
                    putIn(stmt);
                }
            }
            if (getSymbolTable().getFuncType() == Word.INTTK && getSymbolTable().getPile() == 2) {
                Symbol rce = (Symbol) get(size() - 1);
                if (!(get(size() - 2) instanceof Stmt)) {
                    MyIO.writeToErrorBuffer(rce.getLine(), "g");
                }
                else {
                    Stmt stmt = (Stmt) get(size() - 2);
                    if (!(stmt.get(0) instanceof Symbol)) {
                        MyIO.writeToErrorBuffer(rce.getLine(), "g");
                    }
                    else if (((Symbol) stmt.get(0)).getType() != Word.RETURNTK) {
                        MyIO.writeToErrorBuffer(rce.getLine(), "g");
                    }
                }
            }
            else if (getSymbolTable().getFuncType() == Word.VOIDTK
                    && getSymbolTable().getPile() == 2 &&
                    !(MyIO.getLastMidCode() instanceof Return)) {
                writeInMidCode(new Return());
            }
        }
    }
}
