import java.math.BigDecimal;
import java.util.HashMap;

public class Number extends Operate {
    private final HashMap<Integer, BigDecimal> results;

    public Number(HashMap<Integer,BigDecimal> results) {
        super(null,null);
        this.results = results;
    }

    @Override
    public HashMap<Integer, BigDecimal> getResults() {
        return results;
    }

}
