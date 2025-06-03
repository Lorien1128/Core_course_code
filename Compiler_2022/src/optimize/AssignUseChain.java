package optimize;

import mcode.ArrayStore;
import mcode.MidCode;
import mcode.ParaDeclare;
import symboltabel.VarSymbol;

import java.util.HashSet;

public class AssignUseChain {
    private VarSymbol var;
    private final FlowGraph flowGraph;
    private final HashSet<AssignMessage> assignMessages = new HashSet<>();
    private final HashSet<UseMessage> useMessages = new HashSet<>();

    public AssignUseChain(AssignMessage assignMessage, FlowGraph flowGraph) {
        assignMessages.add(assignMessage);
        var = assignMessage.getVar();
        this.flowGraph = flowGraph;
    }

    public void addAll(AssignUseChain chain) {
        assignMessages.addAll(chain.assignMessages);
        useMessages.addAll(chain.useMessages);
    }

    public void add(UseMessage useMessage) {
        useMessages.add(useMessage);
    }

    public boolean noUse() {
        return useMessages.isEmpty();
    }

    public void removeAssigns() {
        for (AssignMessage assignMessage : assignMessages) {
            assignMessage.getBaseBlock().removeCode(assignMessage.getIndex());
        }
    }

    public VarSymbol getVar() {
        return var;
    }

    public void replaceAllVar(int subId) {
        VarSymbol target = var.clone();
        target.setSubId(subId);
        for (AssignMessage assignMessage : assignMessages) {
            MidCode midCode = assignMessage.getBaseBlock().get(assignMessage.getIndex());
            if (midCode instanceof ParaDeclare) {
                ((ParaDeclare) midCode).replacePara(target);
            }
            else {
                ((ArrayStore) midCode).replaceTarget(target);
            }
            assignMessage.setVar(target);
        }
        for (UseMessage useMessage : useMessages) {
            MidCode midCode = useMessage.getBaseBlock().get(useMessage.getIndex());
            midCode.replaceSource(var, target);
        }
        var = target;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append(var.getMark()).append(": { ");
        for (AssignMessage assignMessage : assignMessages) {
            stringBuilder.append(assignMessage).append("; ");
        }
        stringBuilder.append(" >>>  ");
        for (UseMessage useMessage : useMessages) {
            stringBuilder.append(useMessage.getBaseBlock().getId()).append(".").
                    append(useMessage.getIndex()).append("; ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
