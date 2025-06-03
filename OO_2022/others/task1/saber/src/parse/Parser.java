package parse;

import exp.Expression;
import exp.Tree;
import exp.SignedInt;

public class Parser {
    private final Lexer lexer;
    private final String varPattern =
        "^([-+ \\t]*(?<varName>[a-zA-Z]+)([ \\t]?\\*\\*[ \\t]?(?<index>[+]?[0-9]+))?)";
    private final String constPattern = "^([-+ \\t]*(?<const>[0-9]+))";
    private final String exprFactorPattern = "^[-+ \\t]*\\(";
    private final String factorPattern = "^(\\*\\*[ \\t]?(?<index>[+]?[0-9]+))";
    private final String termPattern = "^(\\*)";
    private final String exprPattern = "^([\\+\\-])";

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Tree parseExpr() {
        // TODO:return 项(Tree) + 项(Tree)
        Tree ret = parseTerm();
        if (lexer.peek().equals("")) {
            return ret;
        }
        while (StringPatternUtil.stringContains(lexer.sub(), exprPattern)) {
            Tree newTree = new Tree(lexer.peek());
            lexer.next();
            newTree.setRightNode(parseTerm());
            newTree.setLeftNode(ret);
            ret = newTree;
            if (lexer.peek().equals("")) {
                return ret;
            }
        }
        if (lexer.peek().equals(")")) {
            lexer.next();
        }
        return ret;
    }

    public Tree parseTerm() {
        // TODO:return factor(Tree) * factor (Tree)
        Tree ret = parseFactor();
        if (lexer.peek().equals("")) {
            return ret;
        }
        while (StringPatternUtil.stringContains(lexer.sub(), termPattern)) {
            lexer.next();
            Tree newTree = new Tree("*");
            newTree.setRightNode(parseFactor());
            newTree.setLeftNode(ret);
            ret = newTree;
            if (lexer.peek().equals("")) {
                return ret;
            }
        }
        return ret;
    }

    public Tree parseFactor() {
        // TODO:return Expr | const | var
        if (isExpr()) {
            //Expr
            boolean isNeg = Factory.isNeg(lexer);

            while (!lexer.peek().equals("(")) {
                lexer.next();
            }
            lexer.next();

            boolean newIsNeg = isNeg;// 为了应付check_style

            Tree ret = parseExpr();

            ret = Factory.constructPowerTree(lexer, ret);

            if (newIsNeg) {
                SignedInt negCoeff = new SignedInt("-1");
                Expression e1 = new Expression();
                e1.addTerm(negCoeff);
                ret = Factory.constructOpTree("*", ret, new Tree(e1));
            }
            return ret;
        } else if (isVar()) {
            //Var
            return Factory.valFactor(lexer);
        } else if (isConst()) {
            //Const
            return Factory.constFactor(lexer);
        }
        return null;
    }

    public boolean isExpr() {
        return StringPatternUtil.stringContains(lexer.sub(), exprFactorPattern);
    }

    public boolean isVar() {
        return StringPatternUtil.stringContains(lexer.sub(), varPattern);
    }

    public boolean isConst() {
        return StringPatternUtil.stringContains(lexer.sub(), constPattern);
    }

}
