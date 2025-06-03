package expr;

import java.util.ArrayList;

public interface Factor {
    ArrayList<Variable> use();

    int getPow();

    void setPow(int pow);
}
