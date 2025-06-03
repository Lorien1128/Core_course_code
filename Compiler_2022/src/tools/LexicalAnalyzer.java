package tools;

import java.util.ArrayList;
import java.util.HashMap;

public class LexicalAnalyzer {
    private final ArrayList<Character> codes = new ArrayList<>();
    private final ArrayList<Symbol> words = new ArrayList<>();
    private String curWord = "";
    private int pointer = 0;
    private int startTag = 0;
    private State state = State.START;
    private static final LexicalAnalyzer LA = new LexicalAnalyzer();
    private static final HashMap<String, Word> RESERVED_WORDS = new HashMap<>();
    private int symPointer = 0;
    private int line = 1;

    public static LexicalAnalyzer getLa() {
        return LA;
    }

    private LexicalAnalyzer() {
    }

    public void init(String text) throws Exception {
        char[] array = text.toCharArray();
        for (char elem : array) {
            codes.add(elem);
        }
        codes.add(' ');
        RESERVED_WORDS.put("main", Word.MAINTK);
        RESERVED_WORDS.put("const", Word.CONSTTK);
        RESERVED_WORDS.put("int", Word.INTTK);
        RESERVED_WORDS.put("break", Word.BREAKTK);
        RESERVED_WORDS.put("continue", Word.CONTINUETK);
        RESERVED_WORDS.put("if", Word.IFTK);
        RESERVED_WORDS.put("else", Word.ELSETK);
        RESERVED_WORDS.put("while", Word.WHILETK);
        RESERVED_WORDS.put("getint", Word.GETINTTK);
        RESERVED_WORDS.put("printf", Word.PRINTFTK);
        RESERVED_WORDS.put("return", Word.RETURNTK);
        RESERVED_WORDS.put("void", Word.VOIDTK);
        parse();
    }

    public Symbol nextSym() {
        symPointer++;
        try {
            return words.get(symPointer);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Symbol readSym() {
        return words.get(symPointer);
    }

    public Symbol preSym() {
        return words.get(symPointer - 1);
    }

    public void fallBack(int step) {
        symPointer -= step;
    }

    public boolean finish() {
        return symPointer >= words.size();
    }

    public int getSymPointer() {
        return symPointer;
    }

    public void setSymPointer(int symPointer) {
        this.symPointer = symPointer;
    }

    public ArrayList<Symbol> parse(String text) throws Exception {
        char[] array = text.toCharArray();
        for (char elem : array) {
            codes.add(elem);
        }
        codes.add(' ');
        boolean flag = true;
        while (flag) {
            flag = parseOneLetter();
        }
        return words;
    }

    private void parse() throws Exception {
        boolean flag = true;
        while (flag) {
            flag = parseOneLetter();
        }
    }

    private boolean parseOneLetter() throws Exception {
        if (pointer >= codes.size()) {
            return false;
        }
        switch (state) {
            case START: {
                startTag = pointer;
                handleStart();
                break;
            }
            case IDENT: {
                handleIdent();
                break;
            }
            case INT_CONST: {
                handleIntConst();
                break;
            }
            case DEC_CONST: {
                handleDecConst();
                break;
            }
            case ENTER_FORMAT: {
                handleEnterFormat();
                break;
            }
            case IN_NOTE_LINE:
            case IN_NOTE_BLOCK: {
                handleInNote();
                break;
            }
            default: break;
        }
        if (codes.get(pointer) == '\n') {
            line++;
        }
        pointer++;
        return true;
    }

    private void parseFail() {
        curWord = "";
        pointer = startTag;
        state = State.START;
    }

    private void parseSucceed() {
        curWord = "";
        state = State.START;
    }

    private void handleStart() throws Exception {
        Character ch = codes.get(pointer);
        if (Character.isLetter(ch) || ch == '_') {
            state = State.IDENT;
            curWord += ch;
        }
        else if (ch == '0') {
            state = State.INT_CONST;
            curWord += ch;
        }
        else if (Character.isDigit(ch)) {
            state = State.DEC_CONST;
            curWord += ch;
        }
        else if (ch == '"') {
            state = State.ENTER_FORMAT;
            curWord += ch;
        }
        else {
            switch (ch) {
                case '!' : handleNot();
                break;
                case '&' : handleAnd();
                break;
                case '|' : handleOr();
                break;
                case '/' : handleDiv();
                break;
                case '<' : handleLess();
                break;
                case '>' : handleGreater();
                break;
                case '=' : handleAssign();
                break;
                default : handleSingleSymbol();
            }
        }
    }

    private void handleSingleSymbol() throws Exception {
        Character ch = codes.get(pointer);
        if (Character.isWhitespace(ch)) {
            parseSucceed();
            return;
        }
        switch (ch) {
            case '+' : {
                words.add(new Symbol(line, Word.PLUS, "+"));
                parseSucceed();
                break;
            } case '-' : {
                words.add(new Symbol(line, Word.MINU, "-"));
                parseSucceed();
                break;
            } case '*' : {
                words.add(new Symbol(line, Word.MULT, "*"));
                parseSucceed();
                break;
            } case '%' : {
                words.add(new Symbol(line, Word.MOD, "%"));
                parseSucceed();
                break;
            } case ';' : {
                words.add(new Symbol(line, Word.SEMICN, ";"));
                parseSucceed();
                break;
            } case ',' : {
                words.add(new Symbol(line, Word.COMMA, ","));
                parseSucceed();
                break;
            } case '(' : {
                words.add(new Symbol(line, Word.LPARENT, "("));
                parseSucceed();
                break;
            } case ')' : {
                words.add(new Symbol(line, Word.RPARENT, ")"));
                parseSucceed();
                break;
            } case '[' : {
                words.add(new Symbol(line, Word.LBRACK, "["));
                parseSucceed();
                break;
            } case ']' : {
                words.add(new Symbol(line, Word.RBRACK, "]"));
                parseSucceed();
                break;
            } case '{' : {
                words.add(new Symbol(line, Word.LBRACE, "{"));
                parseSucceed();
                break;
            } case '}' : {
                words.add(new Symbol(line, Word.RBRACE, "}"));
                parseSucceed();
                break;
            } default : throw new Exception("WRONG CHARACTER");
        }
    }

    private void handleIdent() {
        char ch = codes.get(pointer);
        if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_') {
            curWord += ch;
        }
        else {
            words.add(new Symbol(line, RESERVED_WORDS.getOrDefault(curWord, Word.IDENFR), curWord));
            pointer--;
            parseSucceed();
        }
    }

    private void handleIntConst() {
        words.add(new Symbol(line, Word.INTCON, "0"));
        pointer--;
        parseSucceed();
    }

    private void handleDecConst() {
        char ch = codes.get(pointer);
        if (Character.isDigit(ch)) {
            curWord += ch;
        }
        else {
            words.add(new Symbol(line, Word.INTCON, curWord));
            pointer--;
            parseSucceed();
        }
    }

    private void handleEnterFormat() {
        char ch = codes.get(pointer);
        char nch = codes.get(pointer + 1);
        if (ch == '\\' && nch == 'n') {
            curWord += "\n";
            pointer++;
        }
        else if (ch == 32 || ch == 33 || (ch >= 40 && ch <= 126 && ch != '\\')) {
            curWord += ch;
        }
        else if (ch == '%' && nch == 'd') {
            curWord += "%d";
            pointer++;
        }
        else if (ch == '"') {
            curWord += ch;
            words.add(new Symbol(line, Word.STRCON, curWord));
            parseSucceed();
        }
        else {
            MyIO.writeToErrorBuffer(line, "a");
        }
    }

    private void handleNot() {
        char nch = codes.get(pointer + 1);
        if (nch == '=') {
            words.add(new Symbol(line, Word.NEQ, "!="));
            pointer++;
            parseSucceed();
        }
        else {
            words.add(new Symbol(line, Word.NOT, "!"));
            parseSucceed();
        }
    }

    private void handleAnd() {
        char nch = codes.get(pointer + 1);
        if (nch == '&') {
            words.add(new Symbol(line, Word.AND, "&&"));
            pointer++;
            parseSucceed();
        }
        else {
            parseFail();
        }
    }

    private void handleOr() {
        char nch = codes.get(pointer + 1);
        if (nch == '|') {
            words.add(new Symbol(line, Word.OR, "||"));
            pointer++;
            parseSucceed();
        }
        else {
            parseFail();
        }
    }

    private void handleLess() {
        char nch = codes.get(pointer + 1);
        if (nch == '=') {
            words.add(new Symbol(line, Word.LEQ, "<="));
            pointer++;
            parseSucceed();
        }
        else {
            words.add(new Symbol(line, Word.LSS, "<"));
            parseSucceed();
        }
    }

    private void handleGreater() {
        char nch = codes.get(pointer + 1);
        if (nch == '=') {
            words.add(new Symbol(line, Word.GEQ, ">="));
            pointer++;
            parseSucceed();
        }
        else {
            words.add(new Symbol(line, Word.GRE, ">"));
            parseSucceed();
        }
    }

    private void handleAssign() {
        char nch = codes.get(pointer + 1);
        if (nch == '=') {
            words.add(new Symbol(line, Word.EQL, "=="));
            pointer++;
            parseSucceed();
        }
        else {
            words.add(new Symbol(line, Word.ASSIGN, "="));
            parseSucceed();
        }
    }

    private void handleDiv() {
        char nch = codes.get(pointer + 1);
        if (nch == '/') {
            state = State.IN_NOTE_LINE;
            pointer++;
        }
        else if (nch == '*') {
            state = State.IN_NOTE_BLOCK;
            pointer++;
        }
        else {
            words.add(new Symbol(line, Word.DIV, "/"));
            parseSucceed();
        }
    }

    private void handleInNote() {
        char ch = codes.get(pointer);
        if (state == State.IN_NOTE_LINE) {
            if (ch == '\n') {
                state = State.START;
            }
        }
        else {
            char nch = codes.get(pointer + 1);
            if (ch == '*' && nch == '/') {
                state = State.START;
                pointer++;
            }
        }
    }
}
