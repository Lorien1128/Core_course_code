package tools;

public enum Word {
    IDENFR,
    INTCON,
    STRCON,
    MAINTK,
    CONSTTK,
    INTTK,
    BREAKTK,
    CONTINUETK,
    IFTK,
    ELSETK,
    NOT,
    AND,
    OR,
    WHILETK,
    GETINTTK,
    PRINTFTK,
    RETURNTK,
    PLUS,
    MINU,
    VOIDTK,
    MULT,
    DIV,
    MOD,
    LSS,
    LEQ,
    GRE,
    GEQ,
    EQL,
    NEQ,
    ASSIGN,
    SEMICN,
    COMMA,
    LPARENT,
    RPARENT,
    LBRACK,
    RBRACK,
    LBRACE,
    RBRACE;

    public String toOperator() {
        switch (this) {
            case PLUS:
                return "+";
            case MINU:
                return "-";
            case MULT:
                return "*";
            case DIV:
                return "/";
            case MOD:
                return "%";
            case LSS:
                return "<";
            case LEQ:
                return "<=";
            case GRE:
                return ">";
            case GEQ:
                return ">=";
            case EQL:
                return "==";
            case NEQ:
                return "!=";
            default:
                return " ";
        }
    }
}
