package parse;

import exp.Expression;
import exp.Tree;
import exp.SignedInt;
import exp.VarTerm;
import exp.Factor;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Factory {

    //根据传入的 String 创建 Tree
    private static final String VAR_PATTERN =
        "^([-+ \\t]*(?<varName>[a-zA-Z]+)([ \\t]?\\*\\*[ \\t]?(?<index>[+]?[0-9]+))?)";
    private static final String CONST_PATTERN = "^([-+ \\t]*(?<const>[0-9]+))";
    private static final String EXPR_FACTOR_PATTERN = "^[-+ \\t]*\\(";
    private static final String FACTOR_PATTERN = "^(\\*\\*[ \\t]?(?<index>[+]?[0-9]+))";
    private static final String TERM_PATTERN = "^(\\*)";
    private static final String EXPR_PATTERN = "^([\\+\\-])";

    public static Tree constructOpTree(String op, Tree left, Tree right) {
        Tree ret = new Tree(op);
        ret.setLeftNode(left);
        ret.setRightNode(right);
        return ret;
    }

    public static Tree constructPowerTree(Lexer lexer, Tree tree) {
        Tree ret = tree;
        while (StringPatternUtil.stringContains(lexer.sub(), FACTOR_PATTERN)) {

            Matcher factorMatcher = Pattern.compile(FACTOR_PATTERN).matcher(lexer.sub());
            if (!factorMatcher.find()) {
                System.out.println(lexer.sub());
            }

            String index = factorMatcher.group("index");
            Expression e1 = new Expression();
            e1.addTerm(new SignedInt(index));

            ret = Factory.constructOpTree("**", ret, new Tree(e1));

            String input = factorMatcher.group();
            lexer.nextEle(input);
        }
        return ret;
    }

    public static boolean isNeg(Lexer lexer) {
        boolean isNeg = false;
        String sub = lexer.sub();
        while (sub.matches("[-+\\s].*")) {
            isNeg = ((sub.charAt(0) != '-') == isNeg);
            sub = sub.substring(1);
        }
        return isNeg;
    }

    public static BigInteger toVarIndex(String string) {
        BigInteger ret;
        if (string == null) {
            ret = BigInteger.ONE;
        } else {
            ret = new BigInteger(string);
        }
        return ret;
    }

    public static Tree constFactor(Lexer lexer) {

        Matcher matcher = Pattern.compile(CONST_PATTERN).matcher(lexer.sub());
        if (!matcher.find()) {
            System.out.println(lexer.sub());
        }

        SignedInt value = new SignedInt(matcher.group("const"));
        Expression e = new Expression();
        e.addTerm(value);
        Tree ret = new Tree(e);

        boolean isNeg = isNeg(lexer);
        String input = matcher.group();
        lexer.nextEle(input);

        ret = constructPowerTree(lexer, ret);
        //        while (StringPatternUtil.stringContains(lexer.sub(), FACTOR_PATTERN)) {
        //
        //            // ""空和无"**"都会返回false 然后退出
        //            Matcher factorMatcher = Pattern.compile(FACTOR_PATTERN).matcher(lexer.sub());
        //            if (!factorMatcher.find()) {
        //                System.out.println(lexer.sub());
        //            }
        //
        //            String index = factorMatcher.group("index");
        //            SignedInt constIndex = new SignedInt(index);
        //            Expression e1 = new Expression();
        //            e1.addTerm(constIndex);
        //
        //            ret = constructOpTree("**", ret, new Tree(e1));
        //
        //            input = factorMatcher.group();
        //            lexer.nextEle(input);
        //        }
        if (isNeg) {
            SignedInt negCoeff = new SignedInt("-1");
            Expression e1 = new Expression();
            e1.addTerm(negCoeff);
            ret = constructOpTree("*", ret, new Tree(e1));
        }
        return ret;
    }

    public static Tree valFactor(Lexer lexer) {

        Matcher matcher = Pattern.compile(VAR_PATTERN).matcher(lexer.sub());
        if (!matcher.find()) {
            System.out.println(lexer.sub());
        }

        String index = matcher.group("index");
        BigInteger varIndex = toVarIndex(index);

        VarTerm var = new VarTerm(new SignedInt("1"), new Factor(varIndex));
        Expression e = new Expression();
        e.addTerm(var);
        Tree ret = new Tree(e);

        boolean isNeg = isNeg(lexer);
        String input = matcher.group();
        lexer.nextEle(input);

        ret = constructPowerTree(lexer, ret);

        //        while (StringPatternUtil.stringContains(lexer.sub(), FACTOR_PATTERN)) {
        //
        //            Matcher factorMatcher = Pattern.compile(FACTOR_PATTERN).matcher(lexer.sub());
        //            if (!factorMatcher.find()) {
        //                System.out.println(lexer.sub());
        //            }
        //
        //            index = factorMatcher.group("index");
        //            Expression e1 = new Expression();
        //            e1.addTerm(new SignedInt(index));
        //
        //            ret = constructOpTree("**", ret, new Tree(e1));
        //
        //            input = factorMatcher.group();
        //            lexer.nextEle(input);
        //        }

        if (isNeg) {
            SignedInt negCoeff = new SignedInt("-1");
            Expression e1 = new Expression();
            e1.addTerm(negCoeff);
            ret = constructOpTree("*", ret, new Tree(e1));
        }
        return ret;
    }

}
