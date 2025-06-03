package nonterminals;

import tools.Item;

import java.util.ArrayList;

public class LeftRecursion extends Nonterminal {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Item> contents = getContents();
        for (int i = 0; i < contents.size(); i++) {
            Item item = contents.get(i);
            stringBuilder.append(item.toString());
            if (i % 2 == 0) {
                stringBuilder.append("<").append(getClass().getSimpleName())
                        .append(">").append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
