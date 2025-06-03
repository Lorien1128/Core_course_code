package optimize;

import mcode.FuncDeclare;
import mcode.MidCode;
import tools.MyIO;

import java.util.ArrayList;

public class Optimize {
    private final ArrayList<FlowGraph> flowGraphs = new ArrayList<>();
    private BaseBlock globalVarBlock;

    public Optimize() {
        ArrayList<MidCode> midCodes = MyIO.getMidCodes();
        int funcStart = -1;
        for (int i = 0; i < midCodes.size(); i++) {
            if (midCodes.get(i) instanceof FuncDeclare) {
                if (funcStart >= 0) {
                    flowGraphs.add(
                            new FlowGraph((new ArrayList<>(midCodes.subList(funcStart, i)))));
                }
                else {
                    globalVarBlock = new BaseBlock(new ArrayList<>(midCodes.subList(0, i)), null);
                }
                funcStart = i;
            }
            else if (i == midCodes.size() - 1) {
                flowGraphs.add(
                        new FlowGraph(new ArrayList<>(midCodes.subList(funcStart, i + 1))));
            }
        }
    }

    public void optimize() {
        globalVarBlock.eliminateCommonSubExp();
        globalVarBlock.initAssignAndUse();
        globalVarBlock.spreadConstWithinBlock();
        for (FlowGraph flowGraph : flowGraphs) {
            flowGraph.optimize();
        }
        MyIO.clearMidCode();
        for (MidCode midCode : globalVarBlock.getMidCodes()) {
            MyIO.writeInMidCode(midCode);
        }
        for (FlowGraph flowGraph : flowGraphs) {
            ArrayList<MidCode> midCodes = flowGraph.exportMidCodes();
            for (MidCode midCode : midCodes) {
                MyIO.writeInMidCode(midCode);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder graphs = new StringBuilder();
        for (FlowGraph flowGraph : flowGraphs) {
            graphs.append(flowGraph.toString());
        }
        return graphs.toString();
    }
}
