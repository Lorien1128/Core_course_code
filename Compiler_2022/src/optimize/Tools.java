package optimize;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Tools {
    public static void unionAssignMessage(
            HashMap<String, HashSet<AssignMessage>> messageFrom,
            HashMap<String, HashSet<AssignMessage>> messageTo) {
        for (Map.Entry<String, HashSet<AssignMessage>> entry : messageFrom.entrySet()) {
            String varName = entry.getKey();
            if (messageTo.containsKey(varName)) {
                messageTo.get(varName).addAll(entry.getValue());
            }
            else {
                HashSet<AssignMessage> hashSet = new HashSet<>(entry.getValue());
                messageTo.put(varName, hashSet);
            }
        }
    }
}
