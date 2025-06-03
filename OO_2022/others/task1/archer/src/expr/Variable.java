package expr;

import java.math.BigInteger;
import java.util.ArrayList;

public class Variable implements Factor {
    private BigInteger coe;
    private int pow;

    public Variable(BigInteger c, int pow) {
        this.coe = c;
        this.pow = pow;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }

    public void setC(BigInteger c) {
        this.coe = c;
    }

    @Override
    public ArrayList<Variable> use() {
        Variable variable = new Variable(this.coe, this.pow);
        ArrayList<Variable> arrayList = new ArrayList<>();
        arrayList.add(variable);
        return arrayList;
    }

    @Override
    public int getPow() {
        return this.pow;
    }

    public BigInteger getC() {
        return this.coe;
    }
}
