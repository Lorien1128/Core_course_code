import java.math.BigDecimal;
import java.util.HashMap;

public class Exp extends Operate {
    public Exp(Operate left, Operate right) {
        super(left, right);
    }

    @Override
    public HashMap<Integer, BigDecimal> getResults() {
        HashMap<Integer, BigDecimal> tableLeft = getLeft().getResults();
        HashMap<Integer, BigDecimal> tableRight = getRight().getResults();
        HashMap<Integer, BigDecimal> res = new HashMap<>();
        BigDecimal times = tableRight.get(0);   // pow<=8
        if (times.equals(new BigDecimal(0))) {
            res.put(0, new BigDecimal(1));
        }
        else {
            res = new HashMap<>(tableLeft);
            times = times.subtract(new BigDecimal(1));
            for (BigDecimal i = new BigDecimal(0); i.compareTo(times) < 0; i = i.add(new BigDecimal(1))) {
                res = new HashMap<>(mul(tableLeft, res));
            }
        }
        return res;
    }

    public HashMap<Integer, BigDecimal> mul(HashMap<Integer, BigDecimal> left, HashMap<Integer, BigDecimal> right) {
        HashMap<Integer, BigDecimal> res = new HashMap<>();
        for (Integer key1 : left.keySet()) {
            for (Integer key2 : right.keySet()) {
                BigDecimal value1 = left.get(key1);
                BigDecimal value2 = right.get(key2);
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
