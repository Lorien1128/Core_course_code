package exp;

import java.math.BigInteger;

public class Factor {
    private SignedInt index;

    public Factor(BigInteger index) {
        this.index = new SignedInt(index.toString());
    }

    public boolean equals(Factor factor) {
        return index.getNum().compareTo(factor.getIndex().getNum()) == 0;
    }

    public Factor add(Factor factor) {
        BigInteger newIndex = factor.getIndex().getNum().add(index.getNum());
        return new Factor(newIndex);
    }

    public SignedInt getIndex() {
        return index;
    }
    
}
