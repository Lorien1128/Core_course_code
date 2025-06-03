package task;

import java.util.ArrayList;

public class Pow extends Mult {
    public Pow(String left, String right) {
        super(left, right);
    }

    private int pow = Integer.parseInt(this.getRight());

    @Override
    public String getResult() {
        String left = this.getLeft();
        String result = this.getLeft();
        if (pow == 0) {
            return "1";
        }
        else {
            int k;
            ArrayList<String> op1 = new ArrayList<>();
            ArrayList<String> op2 = new ArrayList<>();
            ArrayList<Integer> locat1 = new ArrayList<>();
            ArrayList<Integer> locat2 = new ArrayList<>();
            for (k = 0; k < pow - 1; k++) {
                findAddMult(result, locat1, '+');
                findAddMult(left, locat2,'+');
                getData(locat1, op1, result);
                getData(locat2, op2, left);
                result = mult(op1, op2);
            }
        }
        return result;
    }
}
