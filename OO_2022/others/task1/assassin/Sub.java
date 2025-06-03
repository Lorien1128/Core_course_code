import java.math.BigDecimal;
import java.util.HashMap;

public class Sub extends Operate {
    public Sub(Operate left, Operate right) {
        super(left, right);
    }

    @Override
    public HashMap<Integer, BigDecimal> getResults() {
        HashMap<Integer, BigDecimal> tableLeft = getLeft().getResults();
        HashMap<Integer, BigDecimal> tableRight = getRight().getResults();
        HashMap<Integer, BigDecimal> res = new HashMap<>(tableLeft);
        for (Integer key : tableRight.keySet()) {
            if (res.containsKey(key)) {
                BigDecimal value = res.get(key);
                res.put(key, value.subtract(tableRight.get(key)));
            }
            else {
                res.put(key, new BigDecimal(0).subtract(tableRight.get(key)));
            }
        }
        return res;
    }
}
