import java.util.ArrayList;

public class ExprClass {
    private ArrayList<TermClass> termClasses;

    public ExprClass() {
        this.termClasses = new ArrayList<>();
    }

    public ArrayList<TermClass> getTermClasses() {
        return termClasses;
    }

    @Override
    public String toString() {
        String result = "";
        for (TermClass term : this.termClasses) {
            result = result.concat(term.toString());
        }
        if (result.equals("")) {
            return  "0";
        } else {
            if (result.charAt(0) == '+') {
                return result.substring(1);
            } else {
                return result;
            }
        }
    }

    public boolean equal(ExprClass expr1, ExprClass expr2) {
        int cnt = expr1.getTermClasses().size();
        if (cnt != expr2.getTermClasses().size()) {
            return false;
        } else {
            int num = 0;
            for (int i = 0; i < cnt; i++) {
                for (int j = 0; j < cnt; j++) {
                    if (new TermClass().equal(expr1.getTermClasses().get(i),
                            expr2.getTermClasses().get(j))) {
                        num++;
                    }
                }
            }
            if (cnt == num) {
                return true;
            } else {
                return false;
            }
        }
    }

    public ExprClass expr(String str, ArrayList<Function> functions) {
        int sign = 1;
        int pos = 0;
        int posInitial = 0;
        if (str.charAt(0) == '-') {
            sign = -1;
            posInitial++;
        } else if (str.charAt(0) == '+') {
            posInitial++;
        }
        while (str.charAt(pos) == '+' || str.charAt(pos) == '-' ||
                str.charAt(pos) == ' ' || str.charAt(pos) == '\t') {
            pos++;
        }
        int posAddSub = findAddSub(str, pos);
        if (posAddSub == -1) {
            ExprClass temp = new TermClass().term(str.substring(posInitial).trim(), functions);
            if (sign == 1) {
                return temp;
            } else {
                return new Compute().exprSub(new ExprClass(), temp);
            }
        } else {
            ExprClass temp = new TermClass()
                    .term(str.substring(posInitial, posAddSub).trim(), functions);
            ExprClass firstTerm = temp;
            if (sign == -1) {
                firstTerm = new Compute().exprSub(new ExprClass(), temp);
            }
            return new Compute().exprAdd(firstTerm, new ExprClass()
                    .expr(str.substring(posAddSub).trim(), functions));
        }
    }

    public int findAddSub(String str, int start) {
        int result = -1;
        int flag1 = 0;
        int flag2 = 0;
        for (int pos = start; pos < str.length(); pos++) {
            if (str.charAt(pos) == '(') {
                flag1++;
            } else if (str.charAt(pos) == ')') {
                flag2 = 0;
                if (flag1 > 0) {
                    flag1--;
                } else {
                    throw new RuntimeException("Wrong Format!");
                }
            } else if (flag1 == 0) {
                if (str.charAt(pos) == '*') {
                    flag2 = 1;
                } else if (str.charAt(pos) == 'x' ||
                        str.charAt(pos) >= '0' && str.charAt(pos) <= '9') {
                    flag2 = 0;
                }
                if (flag2 == 0) {
                    if (str.charAt(pos) == '+' || str.charAt(pos) == '-') {
                        result = pos;
                        break;
                    }
                }
            }
        }
        return result;
    }
}


