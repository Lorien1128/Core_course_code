package nonterminals;

import mcode.operand.Operand;

public class Exp extends Nonterminal implements Calculable {
    private AddExp addExp;

    public void parse() throws Exception {
        AddExp addExp = new AddExp();
        addExp.parse();
        putIn(addExp);
        this.addExp = addExp;
    }

    public int calculate() {
        return ((AddExp) get(0)).calculate();
    }

    public int getDimension() {
        return ((AddExp) get(0)).getDimension();
    }

    public Operand genMidCode() {
        return addExp.genMidCode();
    }

    public boolean containsFunc() {
        return addExp.containsFunc();
    }
}
