package mcode.operand;

import symboltabel.SymbolTable;

public class TempOperand implements Operand {
    private static int count = 0;
    private final String name;
    private boolean negative = false;
    private boolean inverted = false;
    private int address;
    private RegisterOperand reg;
    private boolean isReg;
    private boolean freed = false;
    private boolean alloc = false;

    @Override
    public boolean isInverted() {
        return inverted;
    }

    @Override
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public TempOperand() {
        name = ".temp" + count;
        count++;
    }

    public void alloc() {
        RegisterOperand operand = RegisterOperand.allocTRegister();
        if (operand == null) {
            isReg = false;
            address = SymbolTable.getSymbolTable().allocSp(1);
        }
        else {
            reg = operand;
            isReg = true;
        }
        alloc = true;
    }

    public void free() {
        if (freed) {
            return;
        }
        if (isReg) {
            RegisterOperand.freeTRegister(reg);
        }
        freed = true;
    }

    @Override
    public String getMark() {
        return name;
    }

    @Override
    public boolean isNegative() {
        return negative;
    }

    @Override
    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    @Override
    public String getMipsSym() {
        return reg.getMark();
    }

    public int getAddress() {
        return address;
    }

    public boolean isReg() {
        return isReg;
    }

    public boolean isAlloc() {
        return alloc;
    }
}
