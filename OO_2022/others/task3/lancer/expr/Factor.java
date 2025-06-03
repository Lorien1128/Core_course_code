package expr;

import java.util.ArrayList;

public interface Factor extends Comparable<Factor> {

    @Override
    public String toString();

    default String toString(boolean isFirst) {
        if (isFirst) {
            return toString();
        }
        else {
            return "*" + this;
        }
    }

    public Factor clone();

    public Factor substitute(ArrayList<String> parameters, ArrayList<Expr> arguments);

    @Override
    default int compareTo(Factor other) {
        if (this instanceof VarFact) {
            if (!(other instanceof VarFact)) {
                return -1;
            }
            if (!((VarFact) this).getName().equals(((VarFact) other).getName())) {
                return ((VarFact) this).getName().compareTo(((VarFact) other).getName());
            }
            return ((VarFact) this).getExp().compareTo(((VarFact) other).getExp());
        }
        if (this instanceof TriFact) {
            if (other instanceof VarFact) {
                return 1;
            }
            if (!(other instanceof TriFact)) {
                return -1;
            }
            if (!((TriFact) this).getName().equals(((TriFact) other).getName())) {
                return -(((TriFact) this).getName().compareTo(((TriFact) other).getName()));
            }
            if (((TriFact) this).getTriExpr().compareTo(((TriFact) other).getTriExpr()) != 0) {
                return ((TriFact) this).getTriExpr().compareTo(((TriFact) other).getTriExpr());
            }
            return ((TriFact) this).getExp().compareTo(((TriFact) other).getExp());
        }
        if (this instanceof ExprFact) {
            if (!(other instanceof  ExprFact)) {
                return 1;
            }
            if (!((((ExprFact) this).getExpr().compareTo(((ExprFact) other).getExpr())) == 0)) {
                return  ((ExprFact) this).getExpr().compareTo(((ExprFact) other).getExpr());
            }
            return ((ExprFact) this).getExp().compareTo(((ExprFact) other).getExp());
        }
        return 0;
    }
}
