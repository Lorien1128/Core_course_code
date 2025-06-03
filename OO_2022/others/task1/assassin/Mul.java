import java.math.BigDecimal;
import java.util.HashMap;

public class Mul extends Operate {
    public Mul(Operate left, Operate right) {
        super(left, right);
    }

    @Override
    public HashMap<Integer, BigDecimal> getResults() {
        HashMap<Integer, BigDecimal> tableLeft = getLeft().getResults();
        HashMap<Integer, BigDecimal> tableRight = getRight().getResults();
        HashMap<Integer, BigDecimal> res = new HashMap<>();

        for (Integer key1 : tableLeft.keySet()) {
            for (Integer key2 : tableRight.keySet()) {
                BigDecimal value1 = tableLeft.get(key1);
                BigDecimal value2 = tableRight.get(key2);
                if (res.containsKey(key1 + key2)) {
                    BigDecimal value = res.get(key1 + key2);
                    res.put(key1 + key2, value.add(value1.multiply(value2)));
                }
                else {
                    res.put(key1 + key2, value1.multiply(value2));
                }
            }
        }

        return res;
    }
}
