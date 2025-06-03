package mcode;

import mcode.operand.Operand;
import mcode.operand.TempOperand;
import symboltabel.VarSymbol;

import java.util.ArrayList;

public interface MidCode {
    ArrayList<String> getSourceIds();

    ArrayList<String> getTargetIds();

    default ArrayList<String> getTargetVarMips() {
        return new ArrayList<>();
    }

    default boolean isMipsVar(Operand operand) {
        return operand instanceof VarSymbol
                && ((VarSymbol) operand).hasAllocToReg();
    }

    default boolean hasId(Operand operand) {
        return operand instanceof TempOperand | operand instanceof VarSymbol;
    }

    void replaceSource(Operand from, Operand to);
}
