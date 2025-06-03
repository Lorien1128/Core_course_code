package middle;

import organize.Expr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
    private ArrayList<String> args = new ArrayList<>();
    private String expr;
    private ArrayList<Integer> posInExpr = new ArrayList<>();

    public Function(String s) {
        //s ： 去掉空格后的整个函数定义的表达式
        Pattern p = Pattern.compile("[fgh]\\((.*?)\\)");
        Matcher m = p.matcher(s);
        if (!m.find()) {
            return;
        }
        String arg = m.group(1);
        args.addAll(Arrays.asList(arg.split(",")));

        p = Pattern.compile("=(.+)");
        m = p.matcher(s);
        m.find();
        expr = m.group(1);

        StringBuilder pat = new StringBuilder();
        for (String a : args) {
            pat.append("(").append(a).append(")");
        }

        p = Pattern.compile("[" + pat + "]");
        m = p.matcher(expr);
        while (m.find()) {
            posInExpr.add(m.start());
        }
    }

    public Expr substitution(String argsIn) {
        //args : 调用函数的时候括号里面的所有东西，实参以逗号分割
        ArrayList<String> argslist = new ArrayList<>();
        int bracketDepth = 0;
        int l = 0;
        for (int i = 0; i < argsIn.length(); i++) {
            if (argsIn.charAt(i) == '(') {
                bracketDepth++;
            } else if (argsIn.charAt(i) == ')') {
                bracketDepth--;
            } else if (bracketDepth == 0 && argsIn.charAt(i) == ',') {
                argslist.add(argsIn.substring(l, i));
                l = i + 1;
            }
        }
        argslist.add(argsIn.substring(l));
        assert argslist.size() == args.size();
        String result = expr;
        if (args.contains("x")) {
            result = result.replaceAll("x", "(" + argslist.get(args.indexOf("x")) + ")");
        }
        for (int i = 0; i < argslist.size(); i++) {
            if (!args.get(i).equals("x")) {
                result = result.replaceAll(args.get(i), "(" + argslist.get(i) + ")");
            }
        }
        return Parser.parser(result);
    }
}
