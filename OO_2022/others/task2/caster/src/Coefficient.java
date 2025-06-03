import java.math.BigInteger;
import java.util.HashMap;

public class Coefficient {
    private HashMap<String, BigInteger> hashMap = new HashMap<>();

    public Coefficient() {}

    public Coefficient(BigInteger bigInteger) {
        this.hashMap.put("1", bigInteger);
    }
    
    public Coefficient(String string) {
        this.hashMap.put(string, new BigInteger("1"));
    }

    public Coefficient(HashMap<String, BigInteger> newHashMap) {
        this.hashMap = newHashMap;
    }

    public HashMap<String, BigInteger> getHashMap() {
        return hashMap;
    }

    public Coefficient addOrSub(Coefficient other, char c) {
        Coefficient ans = new Coefficient(hashMap);
        for (String i : other.hashMap.keySet()) {
            if (ans.hashMap.get(i) == null) {
                if (c == '+') {
                    ans.hashMap.put(i, other.hashMap.get(i));
                } else {
                    ans.hashMap.put(i, other.hashMap.get(i).multiply(new BigInteger("-1")));
                }
            } else {
                if (c == '+') {
                    ans.hashMap.replace(i, ans.hashMap.get(i).add(other.hashMap.get(i)));
                } else {
                    ans.hashMap.replace(i, ans.hashMap.get(i).subtract(other.hashMap.get(i)));
                }
            }
        }
        return ans;
    }

    public Coefficient multiply(Coefficient other) {
        Coefficient ans = new Coefficient();
        for (String i : hashMap.keySet()) {
            for (String j : other.hashMap.keySet()) {
                if ((i.equals("1") && ans.hashMap.get(j) == null)
                    || (j.equals("1") && ans.hashMap.get(i) == null)
                    || (!i.equals("1") && !j.equals("1") && ans.hashMap.get(i + "*" + j) == null)) {
                    if (i.equals("1")) {
                        ans.hashMap.put(j, hashMap.get(i).multiply(other.hashMap.get(j)));
                    } else if (j.equals("1")) {
                        ans.hashMap.put(i, hashMap.get(i).multiply(other.hashMap.get(j)));
                    } else {
                        ans.hashMap.put(i + "*" + j, hashMap.get(i).multiply(other.hashMap.get(j)));
                    }
                } else {
                    if (i.equals("1")) {
                        ans.hashMap.replace(j,
                                ans.hashMap.get(j).add(
                                        hashMap.get(i).multiply(other.hashMap.get(j))));
                    } else if (j.equals("1")) {
                        ans.hashMap.replace(i,
                                ans.hashMap.get(i).add(
                                        hashMap.get(i).multiply(other.hashMap.get(j))));
                    } else {
                        ans.hashMap.replace(i + "*" + j,
                                ans.hashMap.get(i + "*" + j).add(
                                        hashMap.get(i).multiply(other.hashMap.get(j))));
                    }
                }
                //System.out.println(i + " " + j + " " + hashMap);
            }
        }
        return ans;
    }
}
