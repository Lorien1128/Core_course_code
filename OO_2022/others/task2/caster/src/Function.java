import java.util.HashMap;

public class Function {
    private String expression;
    private HashMap<String, Integer> hashMap = new HashMap<>();

    public Function(String string) {
        int position = -1;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ')') {
                position = i;
                break;
            }
        }
        expression = string.substring(position + 2);
        String[] characters = string.substring(2, position).split(",");
        for (int i = 0; i < characters.length; i++) {
            hashMap.put(characters[i], i);
        }
    }

    public String getFunction(String[] strings) {
        String ans = expression;
        if (hashMap.get("x") != null) {
            ans = ans.replace("x", strings[hashMap.get("x")]);
        }
        if (hashMap.get("y") != null) {
            ans = ans.replace("y", strings[hashMap.get("y")]);
        }
        if (hashMap.get("z") != null) {
            ans = ans.replace("z", strings[hashMap.get("z")]);
        }
        return ans;
    }
}
