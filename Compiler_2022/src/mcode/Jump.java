package mcode;

import mcode.operand.Operand;

import java.util.ArrayList;

public class Jump implements MidCode {
    private final Label terminal;

    public Jump(Label terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "GOTO " + terminal.getName() + "\n";
    }

    @Override
    public ArrayList<String> getSourceIds() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<String> getTargetIds() {
        return new ArrayList<>();
    }

    @Override
    public void replaceSource(Operand from, Operand to) {

    }

    public Label getTerminal() {
        return terminal;
    }
}
