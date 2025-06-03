package nonterminals;

public class ConstExp extends Nonterminal implements Calculable {
    public void parse() throws Exception {
        AddExp addExp = new AddExp();
        addExp.parse();
        putIn(addExp);
    }

    public int calculate() {
        return ((AddExp) get(0)).calculate();
    }
}
