package optimize;

import mcode.Branch;
import mcode.Declare;
import mcode.Exit;
import mcode.FuncDeclare;
import mcode.Jump;
import mcode.Label;
import mcode.MidCode;
import mcode.ParaDeclare;
import mcode.Return;
import symboltabel.FuncSymbol;
import symboltabel.VarSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class FlowGraph {
    private final FuncSymbol function;
    private final FuncDeclare funcDeclare;
    private final ArrayList<Declare> additionalDeclare = new ArrayList<>();
    private final ArrayList<BaseBlock> baseBlocks = new ArrayList<>();
    private final HashMap<AssignMessage, AssignUseChain> assignUseMap = new HashMap<>();
    private final HashMap<VarSymbol, HashSet<VarSymbol>> conflictMap = new HashMap<>();

    public FlowGraph(ArrayList<MidCode> midCodes) {
        function = ((FuncDeclare) midCodes.get(0)).getFuncSymbol();
        funcDeclare = (FuncDeclare) midCodes.remove(0);
        int startIndex = 0;
        BaseBlock.init();
        BaseBlock startBlock = new BaseBlock(null, this);
        BaseBlock endBlock = new BaseBlock(null, this);
        HashMap<String, ArrayList<BaseBlock>> label2SourceBlocks = new HashMap<>();
        HashMap<String, BaseBlock> label2TargetBlock = new HashMap<>();
        for (int i = 0; i < midCodes.size(); i++) {
            MidCode midCode = midCodes.get(i);
            if ((midCode instanceof Label || midCode instanceof Jump ||
                    midCode instanceof Return || midCode instanceof Branch
                    || midCode instanceof Exit) && startIndex >= 0) {
                ArrayList<MidCode> subCodes = new ArrayList<>(
                        midCodes.subList(startIndex, i + (midCode instanceof Label ? 0 : 1)));
                if (subCodes.isEmpty()) {
                    if (midCode instanceof Label) {
                        startIndex = i;
                    }
                    else if ((midCode instanceof Branch || midCode instanceof Jump
                            || midCode instanceof Return)) {
                        startIndex = i + 1;
                    }
                    continue;
                }
                BaseBlock baseBlock = new BaseBlock(subCodes, this);
                if (midCode instanceof Branch || midCode instanceof Jump) {
                    Label label;
                    if (midCode instanceof Branch) {
                        label = ((Branch) midCode).getTerminal();
                    }
                    else {
                        label = ((Jump) midCode).getTerminal();
                    }
                    if (label2SourceBlocks.containsKey(label.getName())) {
                        label2SourceBlocks.get(label.getName()).add(baseBlock);
                    }
                    else {
                        ArrayList<BaseBlock> baseBlocks = new ArrayList<>();
                        baseBlocks.add(baseBlock);
                        label2SourceBlocks.put(label.getName(), baseBlocks);
                    }
                }
                else if (midCode instanceof Return || midCode instanceof Exit) {
                    baseBlock.addOutBlock(endBlock);
                    endBlock.addInBlock(baseBlock);
                }
                if (startIndex > 0 && midCodes.get(startIndex - 1) instanceof Branch) {
                    baseBlocks.get(baseBlocks.size() - 1).addOutBlock(baseBlock);
                    baseBlock.addInBlock(baseBlocks.get(baseBlocks.size() - 1));
                }
                if (midCodes.get(startIndex) instanceof Label) {
                    Label label = ((Label) midCodes.get(startIndex));
                    label2TargetBlock.put(label.getName(), baseBlock);
                    if (startIndex > 0 && !(midCodes.get(startIndex - 1) instanceof Jump ||
                            midCodes.get(startIndex - 1) instanceof Return)) {
                        baseBlocks.get(baseBlocks.size() - 1).addOutBlock(baseBlock);
                        baseBlock.addInBlock(baseBlocks.get(baseBlocks.size() - 1));
                    }
                }
                baseBlocks.add(baseBlock);
            }
            if (midCode instanceof Label) {
                startIndex = i;
            }
            else if ((midCode instanceof Branch || midCode instanceof Jump
                    || midCode instanceof Return || midCode instanceof Exit)) {
                startIndex = i + 1;
            }
        }
        startBlock.addOutBlock(baseBlocks.get(0));
        baseBlocks.get(0).addInBlock(startBlock);
        for (Map.Entry<String, ArrayList<BaseBlock>> entry : label2SourceBlocks.entrySet()) {
            String labelName = entry.getKey();
            ArrayList<BaseBlock> sources = entry.getValue();
            BaseBlock targetBlock = label2TargetBlock.get(labelName);
            for (BaseBlock sourceBlock : sources) {
                sourceBlock.addOutBlock(targetBlock);
                targetBlock.addInBlock(sourceBlock);
            }
        }
        boolean find = true;
        while (find) {
            find = false;
            Iterator<BaseBlock> iterator = baseBlocks.iterator();
            while (iterator.hasNext()) {
                BaseBlock baseBlock = iterator.next();
                if (baseBlock.noIn()) {
                    iterator.remove();
                    baseBlock.removeMeForNextBlocks();
                    find = true;
                }
            }
        }
    }

    public void optimize() {
        for (BaseBlock baseBlock : baseBlocks) {
            // 消除公共子表达式
            baseBlock.eliminateCommonSubExp();
            baseBlock.initAssignAndUse();
            baseBlock.spreadConstWithinBlock();
        }
        // 到达定义数据流分析
        boolean change = true;
        while (change) {
            change = false;
            for (BaseBlock baseBlock : baseBlocks) {
                if (baseBlock.initAssignMessage()) {
                    change = true;
                }
            }
        }
        for (BaseBlock baseBlock : baseBlocks) {
            // 常量传播
            baseBlock.spreadConst();
            baseBlock.spreadConstWithinBlock();
            // 建立定义-使用链
            baseBlock.initAssignUseChain();
        }
        // 删除死代码 + 按网重新分配变量
        Iterator<Map.Entry<AssignMessage, AssignUseChain>> it = assignUseMap.entrySet().iterator();
        HashMap<String, Integer> varCount = new HashMap<>();
        while (it.hasNext()) {
            Map.Entry<AssignMessage, AssignUseChain> chains = it.next();
            AssignUseChain chain = chains.getValue();
            if (chain.noUse()) {
                it.remove();
                chain.removeAssigns();
            }
            else {
                if (!varCount.containsKey(chains.getKey().getMark())) {
                    varCount.put(chains.getKey().getMark(), 0);
                }
                else {
                    int subId = varCount.get(chains.getKey().getMark()) + 1;
                    varCount.put(chains.getKey().getMark(), subId);
                    chain.replaceAllVar(subId);
                }
                addDeclare(new Declare(chain.getVar()));
            }
        }
        // 活跃变量数据流分析
        change = true;
        while (change) {
            change = false;
            for (int i = baseBlocks.size() - 1; i >= 0; i--) {
                BaseBlock baseBlock = baseBlocks.get(i);
                if (baseBlock.initActiveVarMessage()) {
                    change = true;
                }
            }
        }
        // 建立冲突图
        for (BaseBlock baseBlock : baseBlocks) {
            baseBlock.initConflictMap();
        }
        // 分配全局寄存器
        allocReg();

        for (BaseBlock baseBlock : baseBlocks) {
            baseBlock.fillUseRegs();
        }
    }

    public void addAssignUseChain(AssignMessage assignMessage) {
        if (!assignUseMap.containsKey(assignMessage)) {
            assignUseMap.put(assignMessage, new AssignUseChain(assignMessage, this));
        }
    }

    public void addDeclare(Declare declare) {
        additionalDeclare.add(declare);
    }

    public void addUseMessage(AssignMessage assignMessage, BaseBlock baseBlock, int index) {
        assignUseMap.get(assignMessage.getRoot()).add(new UseMessage(baseBlock, index));
    }

    public void unionChain(HashSet<AssignMessage> assignMessages) {
        int i = 0;
        AssignMessage chain1 = null;
        for (AssignMessage assignMessage : assignMessages) {
            if (i == 0) {
                chain1 = assignMessage.getRoot();
            }
            else {
                assert chain1 != null;
                AssignMessage chain2 = assignMessage.getRoot();
                if (!chain1.equals(chain2)) {
                    if (chain1.getHeight() >= chain2.getHeight()) {
                        chain2.setFather(chain1);
                        if (chain1.getHeight() == chain2.getHeight()) {
                            chain1.addHeight();
                        }
                        assignUseMap.get(chain1).addAll(assignUseMap.get(chain2));
                        assignUseMap.remove(chain2);
                    }
                    else {
                        chain1.setFather(chain2);
                        assignUseMap.get(chain2).addAll(assignUseMap.get(chain1));
                        assignUseMap.remove(chain1);
                        chain1 = chain2;
                    }
                }
            }
            i = 1;
        }
    }

    public void addConflict(VarSymbol varSymbol1, VarSymbol varSymbol2) {
        if (varSymbol1.equals(varSymbol2)) {
            return;
        }
        addNode(varSymbol1);
        addNode(varSymbol2);
        conflictMap.get(varSymbol1).add(varSymbol2);
        conflictMap.get(varSymbol2).add(varSymbol1);
    }

    public void addNode(VarSymbol varSymbol) {
        if (!conflictMap.containsKey(varSymbol)) {
            conflictMap.put(varSymbol, new HashSet<>());
        }
    }

    // s0-s7
    private void allocReg() {
        final int regs = 8;
        ArrayList<VarSymbol> list = new ArrayList<>();
        ArrayList<HashSet<VarSymbol>> list2neighbors = new ArrayList<>();
        while (true) {
            boolean found = true;
            while (found) {
                Iterator<Map.Entry<VarSymbol, HashSet<VarSymbol>>> iterator
                        = conflictMap.entrySet().iterator();
                found = false;
                while (iterator.hasNext()) {
                    Map.Entry<VarSymbol, HashSet<VarSymbol>> entry = iterator.next();
                    HashSet<VarSymbol> neighbors = entry.getValue();
                    if (neighbors.size() < regs) {
                        found = true;
                        list.add(entry.getKey());
                        list2neighbors.add(neighbors);
                        iterator.remove();
                        for (VarSymbol neighbor : neighbors) {
                            conflictMap.get(neighbor).remove(entry.getKey());
                        }
                    }
                }
            }
            if (conflictMap.isEmpty()) {
                break;
            }
            else {
                Iterator<Map.Entry<VarSymbol, HashSet<VarSymbol>>> iterator
                        = conflictMap.entrySet().iterator();
                VarSymbol toRemove = null;
                int minDepth = 100;
                int minCount = 1000000;
                while (iterator.hasNext()) {
                    Map.Entry<VarSymbol, HashSet<VarSymbol>> entry = iterator.next();
                    if (entry.getKey().getLoopDepth() < minDepth ||
                            (entry.getKey().getLoopDepth() == minDepth
                                    && entry.getKey().getReferCount() < minCount)) {
                        toRemove = entry.getKey();
                        minDepth = entry.getKey().getLoopDepth();
                        minCount = entry.getKey().getReferCount();
                    }
                }
                HashSet<VarSymbol> neighbors = conflictMap.get(toRemove);
                conflictMap.remove(toRemove);
                for (VarSymbol neighbor : neighbors) {
                    conflictMap.get(neighbor).remove(toRemove);
                }
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            HashSet<VarSymbol> neighbors = list2neighbors.get(i);
            ArrayList<Boolean> freeRegs = new ArrayList<>();
            for (int j = 0; j < regs; j++) {
                freeRegs.add(true);
            }
            for (VarSymbol neighbor : neighbors) {
                if (neighbor.hasAllocToReg()) {
                    freeRegs.set(neighbor.getSRegIndex(), false);
                }
            }
            int index = -1;
            int start = new Random().nextInt(regs);
            for (int j = start; j < regs + start; j++) {
                if (freeRegs.get(j % regs)) {
                    index = j % regs;
                    break;
                }
            }
            assert index != -1;
            VarSymbol varSymbol = list.get(i);
            varSymbol.allocSReg(index);
            function.addReg("$s" + index);
        }
    }

    public void addReg(String reg) {
        function.setLeafFunc(false);
        function.addReg(reg);
    }

    public ArrayList<MidCode> exportMidCodes() {
        ArrayList<MidCode> midCodes = new ArrayList<>();
        midCodes.add(funcDeclare);
        boolean hasAdd = false;
        for (BaseBlock baseBlock : baseBlocks) {
            for (MidCode midCode : baseBlock.getMidCodes()) {
                if (!hasAdd && !(midCode instanceof ParaDeclare)) {
                    midCodes.addAll(additionalDeclare);
                    hasAdd = true;
                }
                midCodes.add(midCode);
            }
        }
        return midCodes;
    }

    @Override
    public String toString() {
        StringBuilder blocks = new StringBuilder();
        blocks.append(function.getName()).append("\n");
        for (Declare declare : additionalDeclare) {
            blocks.append(declare);
        }
        blocks.append("\n");
        for (BaseBlock baseBlock : baseBlocks) {
            blocks.append(baseBlock.toString());
        }
        StringBuilder chains = new StringBuilder();
        for (Map.Entry<AssignMessage, AssignUseChain> entry : assignUseMap.entrySet()) {
            chains.append(entry.getValue()).append("\n");
        }
        StringBuilder conflict = new StringBuilder().append("conflict:\n");
        for (Map.Entry<VarSymbol, HashSet<VarSymbol>> entry : conflictMap.entrySet()) {
            conflict.append(entry.getKey().getMark()).append(": ");
            for (VarSymbol varSymbol : entry.getValue()) {
                conflict.append(varSymbol.getMark()).append(";");
            }
            conflict.append("\n");
        }
        return "FlowGraph:\n" + blocks + chains + conflict + "\n";
    }
}
