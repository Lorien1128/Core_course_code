package nonterminals;

import tools.LexicalAnalyzer;

public class SyntacticParser {
    public String parse(String text) throws Exception {
        LexicalAnalyzer analyzer = LexicalAnalyzer.getLa();
        analyzer.init(text);
        CompUnit compUnit = new CompUnit();
        compUnit.parse();
        return compUnit.toString();
    }
}
