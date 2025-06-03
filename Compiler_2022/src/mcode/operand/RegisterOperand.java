package mcode.operand;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RegisterOperand implements Operand {
    private final String type;
    private final int index;
    private boolean negative = false;
    private static final HashMap<String, Boolean> BIT_MAP = new HashMap<>();
    private boolean inverted = false;
    private boolean freed = false;

    @Override
    public boolean isInverted() {
        return inverted;
    }

    @Override
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    static {
        for (int i = 0; i <= 9; i++) {
            BIT_MAP.put("t" + i, false);
        }
        for (int i = 0; i <= 7; i++) {
            BIT_MAP.put("s" + i, false);
        }
    }

    public RegisterOperand(String name) {
        this.type = String.valueOf(name.charAt(0));
        this.index = Integer.parseInt(name.substring(1));
    }

    public RegisterOperand() {
        this.type = "";
        this.index = 0;
    }

    public String getMark() {
        return "$" + type + index;
    }

    public static RegisterOperand allocTRegister() {
        for (int index = 2; index <= 9; index++) {
            if (!BIT_MAP.get("t" + index)) {
                BIT_MAP.put("t" + index, true);
                return new RegisterOperand("t" + index);
            }
        }
        return null;
    }

    public static RegisterOperand allocSRegister() {
        for (int index = 0; index <= 7; index++) {
            if (!BIT_MAP.get("s" + index)) {
                BIT_MAP.put("s" + index, true);
                return new RegisterOperand("s" + index);
            }
        }
        return null;
    }

    public static RegisterOperand allocSRegister(int index) {
        BIT_MAP.put("s" + index, true);
        return new RegisterOperand("s" + index);
    }

    public static RegisterOperand allocT01() {
        for (int index = 0; index <= 1; index++) {
            if (!BIT_MAP.get("t" + index)) {
                BIT_MAP.put("t" + index, true);
                return new RegisterOperand("t" + index);
            }
        }
        return null;
    }

    public static void freeTRegister(RegisterOperand operand) {
        BIT_MAP.put(operand.getMark().substring(1), false);
    }

    public static int countTRemains() {
        int remain = 0;
        for (Map.Entry<String, Boolean> entry : BIT_MAP.entrySet()) {
            if (entry.getKey().contains("t") && !entry.getValue()) {
                remain++;
            }
        }
        return remain;
    }

    @Override
    public boolean isNegative() {
        return negative;
    }

    @Override
    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public static HashSet<String> needStore() {
        HashSet<String> store = new HashSet<>();
        for (Map.Entry<String, Boolean> entry : BIT_MAP.entrySet()) {
            if (entry.getKey().contains("t") && entry.getValue()) {
                store.add("$" + entry.getKey());
            }
        }
        return store;
    }

    public static void check() {
        for (Map.Entry<String, Boolean> entry : BIT_MAP.entrySet()) {
            if (entry.getValue()) {
                Exception exception = new Exception(entry.getKey() + "寄存器错误");
                exception.printStackTrace();
            }
        }
    }

    public boolean isFreed() {
        return freed;
    }

    public void setFreed(boolean freed) {
        this.freed = freed;
    }

    public static void showState() {
        System.out.println("未被释放的临时、全局寄存器：");
        for (Map.Entry<String, Boolean> entry : BIT_MAP.entrySet()) {
            if (entry.getValue()) {
                System.out.println(entry.getKey());
            }
        }
    }

    public int getIndex() {
        return index;
    }
}
