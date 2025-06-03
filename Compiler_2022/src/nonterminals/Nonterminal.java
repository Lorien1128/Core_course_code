package nonterminals;

import mcode.MidCode;
import mcode.operand.Operand;
import mcode.operand.RegisterOperand;
import mcode.operand.TempOperand;
import symboltabel.SymbolTable;
import tools.Item;
import tools.MyIO;

import java.util.ArrayList;

public class Nonterminal implements Item {
    private final ArrayList<Item> contents = new ArrayList<>();

    public void putIn(Item item) {
        contents.add(item);
    }

    public Item get(int index) {
        return contents.get(index);
    }

    public int size() {
        return contents.size();
    }

    public void remove(int index) {
        contents.remove(index);
    }

    public ArrayList<Item> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item : contents) {
            stringBuilder.append(item.toString());
        }
        stringBuilder.append("<").append(getClass().getSimpleName())
                .append(">").append("\n");
        return stringBuilder.toString();
    }

    public SymbolTable getSymbolTable() {
        return SymbolTable.getSymbolTable();
    }

    public void writeInMidCode(MidCode midCode) {
        MyIO.writeInMidCode(midCode);
    }

    public RegisterOperand allocTRegister() {
        return RegisterOperand.allocTRegister();
    }

    public void freeTRegister(RegisterOperand operand) {
        RegisterOperand.freeTRegister(operand);
    }

    public void free(Operand operandA, Operand operandB) {
        free(operandA);
        free(operandB);
    }

    public void free(Operand operand) {

    }

    public int countTRemains() {
        return RegisterOperand.countTRemains();
    }

    public Operand alloc() {
        return new TempOperand();
    }

    public boolean forShow() {
        return false;
    }
}
