import java.math.BigInteger;
import java.util.HashMap;

public class Standard {
    private HashMap<Integer, Coefficient> hashMap = new HashMap<>();

    public Standard(String expression) {
        if (!expression.equals("")) {
            if (expression.equals("x")) {
                hashMap.put(1, new Coefficient(new BigInteger("1")));
            } else if (Character.isLetter(expression.charAt(0))) {
                hashMap.put(0, new Coefficient(expression));
            } else {
                hashMap.put(0, new Coefficient(new BigInteger(expression)));
            }
        }
    }

    public Standard(HashMap<Integer, Coefficient> newHashMap) {
        hashMap = newHashMap;
    }

    public String toString() {
        StringBuilder ans = new StringBuilder();
        boolean empty = true;
        for (int i : hashMap.keySet()) {
            //System.out.println(i + "\n" + hashMap.get(i).getHashMap());
            for (String j : hashMap.get(i).getHashMap().keySet()) {
                if (hashMap.get(i).getHashMap().get(j).equals(new BigInteger("0"))) {
                    continue;
                }
                if (j.equals("1")) {
                    ans.append(empty || hashMap.get(i).getHashMap().get(j).compareTo(
                            new BigInteger("0")) < 0 ? "" : "+");
                    if (hashMap.get(i).getHashMap().get(j).compareTo(new BigInteger("1")) > 0
                            || hashMap.get(i).getHashMap().get(j).compareTo(
                                    new BigInteger("-1")) < 0) {
                        ans.append(hashMap.get(i).getHashMap().get(j));
                    } else if (hashMap.get(i).getHashMap().get(j).equals(new BigInteger("-1"))) {
                        ans.append("-");
                    }
                    if (i >= 1) {
                        ans.append(hashMap.get(i).getHashMap().get(j).compareTo(
                                new BigInteger("1")) > 0 ||
                                hashMap.get(i).getHashMap().get(j).compareTo(
                                        new BigInteger("-1")) < 0 ? "*" : "").append("x");
                    }
                    if (i > 1) {
                        ans.append("**").append(i);
                    }
                    if (i == 0 && (hashMap.get(i).getHashMap().get(j).equals(new BigInteger("1"))
                            || hashMap.get(i).getHashMap().get(j).equals(new BigInteger("-1")))) {
                        ans.append("1");
                    }
                } else {
                    ans.append(empty || hashMap.get(i).getHashMap().get(j).compareTo(
                            new BigInteger("0")) < 0 ? "" : "+");
                    if (hashMap.get(i).getHashMap().get(j).compareTo(new BigInteger("1")) > 0
                            || hashMap.get(i).getHashMap().get(j).compareTo(
                            new BigInteger("-1")) < 0) {
                        ans.append(hashMap.get(i).getHashMap().get(j));
                    } else if (hashMap.get(i).getHashMap().get(j).equals(new BigInteger("-1"))) {
                        ans.append("-");
                    }
                    ans.append(hashMap.get(i).getHashMap().get(j).compareTo(
                            new BigInteger("1")) > 0 ||
                            hashMap.get(i).getHashMap().get(j).compareTo(
                                    new BigInteger("-1")) < 0 ? "*" : "").append(j);
                    if (i >= 1) {
                        ans.append("*x");
                    }
                    if (i > 1) {
                        ans.append("**").append(i);
                    }
                }
                empty = false;
            }
        }
        return empty ? "0" : ans.toString();
    }

    public Standard addOrSub(Standard other, char c) {
        Standard ans = new Standard(hashMap);
        for (int i : other.hashMap.keySet()) {
            if (ans.hashMap.get(i) == null) {
                ans.hashMap.put(i, new Coefficient());
            }
            ans.hashMap.replace(i, ans.hashMap.get(i).addOrSub(other.hashMap.get(i),c));
        }
        return ans;
    }

    public Standard multiply(Standard other) {
        Standard ans = new Standard("");
        for (int i : hashMap.keySet()) {
            for (int j : other.hashMap.keySet()) {
                if (ans.hashMap.get(i + j) == null) {
                    ans.hashMap.put(i + j, hashMap.get(i).multiply(other.hashMap.get(j)));
                } else {
                    ans.hashMap.replace(i + j,
                            ans.hashMap.get(i + j).addOrSub(
                                    hashMap.get(i).multiply(other.hashMap.get(j)), '+'));
                }
                //System.out.println(ans.hashMap.get(i + j).getHashMap());
            }
        }
        return ans;
    }
}
