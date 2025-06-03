package nonterminals;

import mcode.operand.Operand;

public class Cond extends Nonterminal {
    public void parse() throws Exception {
        LOrExp lorExp = new LOrExp();
        lorExp.parse();
        putIn(lorExp);
    }

    public Operand genMidCode() {
        return ((LOrExp) get(0)).genMidCode();
    }
}
