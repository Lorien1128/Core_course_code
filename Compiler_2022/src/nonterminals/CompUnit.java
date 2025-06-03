package nonterminals;

import tools.LexicalAnalyzer;
import tools.Word;

public class CompUnit extends Nonterminal {

    public void parse() throws Exception {
        LexicalAnalyzer la = LexicalAnalyzer.getLa();
        while (!la.finish()) {
            if (la.readSym().getType() == Word.CONSTTK) {
                ConstDecl constDecl = new ConstDecl();
                constDecl.parse();
                putIn(constDecl);
            }
            else if (la.readSym().getType() == Word.INTTK) {
                Word type = la.nextSym().getType();
                if (type == Word.MAINTK) {
                    MainFuncDef mainFuncDef = new MainFuncDef();
                    la.fallBack(1);
                    mainFuncDef.parse();
                    putIn(mainFuncDef);
                }
                else if (type == Word.IDENFR) {
                    Word neType = la.nextSym().getType();
                    if (neType == Word.LBRACK || neType == Word.ASSIGN ||
                            neType == Word.COMMA || neType == Word.SEMICN) {
                        VarDecl varDecl = new VarDecl();
                        la.fallBack(2);
                        varDecl.parse();
                        putIn(varDecl);
                    }
                    else if (neType == Word.LPARENT) {
                        FuncDef funcDef = new FuncDef();
                        la.fallBack(2);
                        funcDef.parse();
                        putIn(funcDef);
                    }
                    else {
                        throw new Exception();
                    }
                }
                else {
                    throw new Exception();
                }
            }
            else if (la.readSym().getType() == Word.VOIDTK) {
                FuncDef funcDef = new FuncDef();
                funcDef.parse();
                putIn(funcDef);
            }
            else {
                throw new Exception();
            }
        }
    }
}
