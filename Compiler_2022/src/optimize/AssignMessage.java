package optimize;

import mcode.operand.ImmediateOperand;
import symboltabel.VarSymbol;

public class AssignMessage {
    private final BaseBlock baseBlock;
    private AssignMessage father;
    private int height = 0;
    private final int index;
    private VarSymbol var;
    private final boolean isImm;
    private final ImmediateOperand value;

    public AssignMessage(BaseBlock baseBlock, int index, VarSymbol target) {
        this.baseBlock = baseBlock;
        this.index = index;
        var = target;
        isImm = false;
        value = null;
        father = this;
    }

    public AssignMessage(BaseBlock baseBlock, int index, VarSymbol target, ImmediateOperand value) {
        this.baseBlock = baseBlock;
        this.index = index;
        var = target;
        isImm = true;
        this.value = value;
        father = this;
    }

    public void setVar(VarSymbol var) {
        this.var = var;
    }

    public String getMark() {
        return var.getMark();
    }

    public boolean isImm() {
        return isImm;
    }

    public ImmediateOperand getValue() {
        return value;
    }

    public VarSymbol getVar() {
        return var;
    }

    public void setFather(AssignMessage father) {
        this.father = father;
    }

    public AssignMessage getRoot() {
        AssignMessage message = this;
        while (!message.father.equals(message)) {
            message = message.father;
        }
        return message;
    }

    public int getHeight() {
        return height;
    }

    public void addHeight() {
        height += 1;
    }

    public int getIndex() {
        return index;
    }

    public BaseBlock getBaseBlock() {
        return baseBlock;
    }

    @Override
    public String toString() {
        if (isImm) {
            assert value != null;
            return baseBlock.getId() + "." + index + ": " + var.getMark() + " <- " +
                    value.getValue();
        }
        return baseBlock.getId() + "." + index + ": " + var.getMark();
    }
}
