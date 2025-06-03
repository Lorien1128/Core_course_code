import java.math.BigDecimal;
import java.util.HashMap;

public class Operate {
    private final Operate left;
    private final Operate right;

    public Operate(Operate left, Operate right) {
        this.left = left;
        this.right = right;
    }

    public HashMap<Integer, BigDecimal> getResults() {
        return new HashMap<>();
    }

    public Operate getLeft() {
        return left;
    }

    public Operate getRight() {
        return right;
    }
}
