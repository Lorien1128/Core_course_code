package optimize;

import mcode.Arithmetic;
import mcode.ArrayLoad;
import mcode.ArrayStore;
import mcode.Branch;
import mcode.Declare;
import mcode.FuncCall;
import mcode.Jump;
import mcode.MidCode;
import mcode.None;
import mcode.ParaDeclare;
import mcode.operand.GetIntOperand;
import mcode.operand.ImmediateOperand;
import mcode.operand.Operand;
import mcode.operand.RegisterOperand;
import mcode.operand.TempOperand;
import symboltabel.VarSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class BaseBlock {
    private static int count = -1;
    private final int id;
    private ArrayList<MidCode> midCodes;
    private final FlowGraph flowGraph;
    private final ArrayList<BaseBlock> inBlocks = new ArrayList<>();
    private final ArrayList<BaseBlock> outBlocks = new ArrayList<>();
    private final ArrayList<DagNode> dag = new ArrayList<>();
    private final HashMap<Operand, Integer> nodeMap = new HashMap<>();
    private final ArrayList<MidCode> midCodesAfterDag = new ArrayList<>();
    private final HashMap<String, Integer> lastIndexOf = new HashMap<>();
    private final HashMap<String, ArrayList<Integer>> assigns = new HashMap<>();
    private final HashMap<String, ArrayList<Integer>> uses = new HashMap<>();
    private final HashMap<String, HashSet<AssignMessage>> assignMessageIn = new HashMap<>();
    private final HashMap<String, HashSet<AssignMessage>> assignMessageOut = new HashMap<>();
    private final HashSet<VarSymbol> activeVarIn = new HashSet<>();
    private final HashSet<VarSymbol> activeVarOut = new HashSet<>();
    private final HashMap<String, AssignMessage> generates = new HashMap<>();
    private final HashSet<VarSymbol> useBeforeDef = new HashSet<>();
    private final HashSet<VarSymbol> defBeforeUse = new HashSet<>();
    private final ArrayList<Integer> useAndDefIndexes = new ArrayList<>();
    private boolean hasInitGene = false;
    private boolean hasInitUseDef = false;

    public BaseBlock(ArrayList<MidCode> midCodes, FlowGraph flowGraph) {
        this.midCodes = midCodes;
        this.flowGraph = flowGraph;
        id = count;
        count++;
        if (midCodes != null) {
            midCodes.add(new None());
            initIndex();
        }
    }

    public static void init() {
        count = 0;
    }

    private void initIndex() {
        for (int i = 0; i < midCodes.size(); i++) {
            MidCode midCode = midCodes.get(i);
            ArrayList<String> gets = midCode.getSourceIds();
            for (String id : gets) {
                lastIndexOf.put(id, i);
            }
            ArrayList<String> sets = midCode.getTargetIds();
            for (String id : sets) {
                lastIndexOf.put(id, i);
            }
        }
    }

    public void eliminateCommonSubExp() {
        for (MidCode midCode : midCodes) {
            if (midCode instanceof Arithmetic && !((Arithmetic) midCode).aisAddress()
                    && !((Arithmetic) midCode).bisAddress()) {
                Arithmetic arithmetic = (Arithmetic) midCode;
                DagNode left = handleOperand(arithmetic.getOperandA());
                DagNode right = handleOperand(arithmetic.getOperandB());
                String op = arithmetic.getOperator();
                Operand target = arithmetic.getOperandTarget();
                handleOperator(left, right, op, target);
            }
            else if (midCode instanceof ArrayLoad && !((ArrayLoad) midCode).isArray()) {
                ArrayLoad arrayLoad = (ArrayLoad) midCode;
                DagNode sonNode = handleOperand(arrayLoad.getArrayIdent());
                if (sonNode.getLeft() == null || sonNode.getLeft().getLeft() == null ||
                        !(sonNode.getLeft().getMark().charAt(0) == '.')) {
                    if (sonNode.getLeft() == null) {
                        midCodesAfterDag.add(midCode);
                    }
                    String op = "|->";
                    Operand target = arrayLoad.getOperandTarget();
                    handleOperator(sonNode, op, target);
                }
                else {
                    nodeMap.put(arrayLoad.getOperandTarget(), sonNode.getLeft().getIndex());
                }
            }
            else if (midCode instanceof ArrayStore && ((ArrayStore) midCode).isNotArray() &&
                    !((ArrayStore) midCode).getArrayTarget().isSpreadAddress()
                    && !((((ArrayStore) midCode).getOperandSource()) instanceof GetIntOperand)) {
                ArrayStore arrayStore = (ArrayStore) midCode;
                DagNode sonNode = handleOperand(arrayStore.getOperandSource());
                String op = "|<-";
                Operand target = arrayStore.getArrayTarget();
                handleOperator(sonNode, op, target);
            }
            else if (!(midCode instanceof Declare)) {
                exportFromDag(midCodes.indexOf(midCode));
                if (!(midCode instanceof Branch)) {
                    midCodesAfterDag.add(midCode);
                }
                dag.clear();
                nodeMap.clear();
            }
            else {
                midCodesAfterDag.add(midCode);
            }
        }
        midCodes = midCodesAfterDag;
    }

    private DagNode handleOperand(Operand operand) {
        int index;
        if (nodeMap.containsKey(operand)) {
            index = nodeMap.get(operand);
        }
        else {
            dag.add(new DagNode(operand, dag.size()));
            index = dag.size() - 1;
            nodeMap.put(operand, index);
        }
        return dag.get(index);
    }

    private void handleOperator(DagNode left, DagNode right, String op, Operand target) {
        int index = -1;
        for (int i = 0; i < dag.size(); i++) {
            DagNode dagNode = dag.get(i);
            if (dagNode.getMark().equals(op) && left.equals(dagNode.getLeft())
                    && right.equals(dagNode.getRight())) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            dag.add(new DagNode(op, left, right, dag.size()));
            index = dag.size() - 1;
        }
        nodeMap.put(target, index);
    }

    private void handleOperator(DagNode sonNode, String op, Operand target) {
        int index = -1;
        for (int i = 0; i < dag.size(); i++) {
            DagNode dagNode = dag.get(i);
            if (dagNode.getMark().equals(op) && sonNode.equals(dagNode.getLeft())) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            dag.add(new DagNode(op, sonNode, dag.size()));
            index = dag.size() - 1;
        }
        nodeMap.put(target, index);
    }

    public void exportFromDag(int dagEndIndex) {
        ArrayList<ArrayList<Operand>> node2Operands = new ArrayList<>();
        for (int i = 0; i < dag.size(); i++) {
            node2Operands.add(new ArrayList<>());
        }
        for (Map.Entry<Operand, Integer> entry : nodeMap.entrySet()) {
            Operand operand = entry.getKey();
            if ((operand instanceof VarSymbol || operand instanceof TempOperand) &&
                    lastIndexOf.get(entry.getKey().getMark()) >= dagEndIndex) {
                node2Operands.get(entry.getValue()).add(0, entry.getKey());
            }
            else {
                node2Operands.get(entry.getValue()).add(entry.getKey());
            }
        }
        for (int i = 0; i < dag.size(); i++) {
            if (node2Operands.get(i).isEmpty()) {
                node2Operands.get(i).add(new TempOperand());
            }
        }
        ArrayList<DagNode> sequence = new ArrayList<>();
        while (true) {
            boolean allLeaf = true;
            for (DagNode dagNode : dag) {
                if (dagNode.hasNoFather() && dagNode.getLeft() != null) {
                    sequence.add(dagNode);
                    dag.remove(dagNode);
                    dagNode.removeFromDag();
                    dagNode = dagNode.getLeft();
                    while (dagNode != null && dagNode.getLeft() != null && dagNode.hasNoFather()) {
                        sequence.add(dagNode);
                        dag.remove(dagNode);
                        dagNode.removeFromDag();
                        dagNode = dagNode.getLeft();
                    }
                    allLeaf = false;
                    break;
                }
            } if (allLeaf) {
                break;
            }
        }
        for (int i = sequence.size() - 1; i >= 0; i--) {
            DagNode dagNode = sequence.get(i);
            for (Operand operand : node2Operands.get(dagNode.getIndex())) {
                switch (dagNode.getMark()) {
                    case "|<-": {
                        if (operand instanceof VarSymbol) {
                            midCodesAfterDag.add(new ArrayStore(operand,
                                    new ImmediateOperand(0),
                                    node2Operands.get(dagNode.getLeft().getIndex()).get(0)));
                        }
                        else {
                            midCodesAfterDag.add(new Arithmetic('+',
                                    new ImmediateOperand(0),
                                    node2Operands.get(dagNode.getLeft().getIndex()).get(0),
                                    operand));
                        }
                        break;
                    }
                    case "[]<-": {
                        if (operand instanceof VarSymbol) {
                            midCodesAfterDag.add(new ArrayStore(operand,
                                    node2Operands.get(dagNode.getLeft().getIndex()).get(0),
                                    node2Operands.get(dagNode.getRight().getIndex()).get(0)));
                        }
                        else {
                            midCodesAfterDag.add(new Arithmetic('+',
                                    node2Operands.get(dagNode.getLeft().getIndex()).get(0),
                                    node2Operands.get(dagNode.getRight().getIndex()).get(0),
                                    operand));
                        }
                        break;
                    }
                    case "|->" : {
                        if (dagNode.getLeft().getLeft() != null) {
                            if (node2Operands.get(dagNode.getLeft().getIndex()).get(0)
                                    instanceof VarSymbol) {
                                midCodesAfterDag.add(new ArrayLoad(
                                        node2Operands.get(dagNode.getLeft().getIndex()).get(0),
                                        new ImmediateOperand(0), operand));
                            }
                            else {
                                midCodesAfterDag.add(new Arithmetic('+',
                                        node2Operands.get(dagNode.getLeft().getIndex()).get(0),
                                        new ImmediateOperand(0), operand));
                            }
                        }
                        break;
                    }
                    case "[]->" : {
                        midCodesAfterDag.add(new ArrayLoad(
                                node2Operands.get(dagNode.getLeft().getIndex()).get(0),
                                node2Operands.get(dagNode.getRight().getIndex()).get(0), operand));
                        break;
                    }
                    default: {
                        Operand left = node2Operands.get(dagNode.getLeft().getIndex()).get(0);
                        Operand right = node2Operands.get(dagNode.getRight().getIndex()).get(0);
                        if (left.equals(right) && ("-/%><^".contains(dagNode.getMark()))) {
                            simplifyArithmetic(dagNode.getMark(), operand);
                        }
                        else {
                            midCodesAfterDag.add(new Arithmetic(dagNode.getMark(),
                                    left, right, operand));
                        }
                    }
                }
            }
        }
        if (midCodes.get(dagEndIndex) instanceof Branch) {
            Branch branch = (Branch) midCodes.get(dagEndIndex);
            Operand operandA = branch.getOperandA();
            if (nodeMap.containsKey(operandA)) {
                operandA = node2Operands.get(nodeMap.get(operandA)).get(0);
            }
            Operand operandB = branch.getOperandB();
            if (nodeMap.containsKey(operandB)) {
                operandB = node2Operands.get(nodeMap.get(operandB)).get(0);
            }
            if (operandA.equals(operandB)) {
                if (branch.isEqual()) {
                    midCodesAfterDag.add(new Jump(branch.getTerminal()));
                }
            }
            else {
                midCodesAfterDag.add(new Branch(branch.isEqual(), operandA, operandB,
                        branch.getTerminal()));
            }
        }
    }

    private void simplifyArithmetic(String op, Operand target) {
        switch (op) {
            case "-":
            case "%":
            case "<":
            case ">":
            case "^": {
                midCodesAfterDag.add(new Arithmetic('+', new RegisterOperand(),
                        new ImmediateOperand(0), target));
                break;
            }
            default: {
                midCodesAfterDag.add(new Arithmetic('+', new RegisterOperand(),
                        new ImmediateOperand(1), target));
            }
        }
    }

    public void initAssignAndUse() {
        uses.clear();
        assigns.clear();
        for (int i = 0; i < midCodes.size(); i++) {
            MidCode midCode = midCodes.get(i);
            ArrayList<String> gets = midCode.getSourceIds();
            for (String id : gets) {
                if (uses.containsKey(id)) {
                    uses.get(id).add(i);
                }
                else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(i);
                    uses.put(id, arrayList);
                }
            }
            ArrayList<String> sets = midCode.getTargetIds();
            for (String id : sets) {
                if (midCode instanceof ArrayStore &&
                        ((ArrayStore) midCode).getArrayTarget().isSpreadAddress()) {
                    break;
                }
                if (assigns.containsKey(id)) {
                    assigns.get(id).add(i);
                }
                else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(i);
                    assigns.put(id, arrayList);
                }
            }
        }
    }

    private int sizeOfHashmap(HashMap<String, HashSet<AssignMessage>> map) {
        int sum = 0;
        for (Map.Entry<String, HashSet<AssignMessage>> entry : map.entrySet()) {
            sum += entry.getValue().size();
        }
        return sum;
    }

    public boolean initAssignMessage() {
        boolean change = false;
        int preIn = sizeOfHashmap(assignMessageIn);
        assignMessageIn.clear();
        for (BaseBlock in : inBlocks) {
            Tools.unionAssignMessage(in.assignMessageOut, assignMessageIn);
        }
        if (sizeOfHashmap(assignMessageIn) != preIn) {
            change = true;
        }
        if (!change && !assignMessageOut.isEmpty()) {
            return false;
        }
        if (!hasInitGene) {
            for (Map.Entry<String, ArrayList<Integer>> entry : assigns.entrySet()) {
                if (entry.getKey().charAt(0) != '.') {
                    ArrayList<Integer> indexes = entry.getValue();
                    if (midCodes.get(indexes.get(indexes.size() - 1)) instanceof ParaDeclare) {
                        ParaDeclare paraDeclare = (ParaDeclare) midCodes.
                                get(indexes.get(indexes.size() - 1));
                        AssignMessage assignMessage = new AssignMessage(this,
                                indexes.get(indexes.size() - 1), paraDeclare.getPara());
                        generates.put(entry.getKey(), assignMessage);
                        flowGraph.addAssignUseChain(assignMessage);
                    }
                    else {
                        ArrayStore arrayStore =
                                (ArrayStore) midCodes.get(indexes.get(indexes.size() - 1));
                        VarSymbol var = arrayStore.getArrayTarget();
                        // 非数组局部变量
                        if (var.isRegAble() && !var.isGlobal() && !var.isSpreadAddress()) {
                            if (arrayStore.getOperandSource() instanceof ImmediateOperand) {
                                ImmediateOperand value =
                                        (ImmediateOperand) arrayStore.getOperandSource();
                                AssignMessage assignMessage = new AssignMessage(this,
                                        indexes.get(indexes.size() - 1), var, value);
                                generates.put(entry.getKey(), assignMessage);
                                flowGraph.addAssignUseChain(assignMessage);
                            }
                            else {
                                AssignMessage assignMessage = new AssignMessage(this,
                                        indexes.get(indexes.size() - 1), var);
                                generates.put(entry.getKey(), assignMessage);
                                flowGraph.addAssignUseChain(assignMessage);
                            }
                        }
                    }
                }
            }
            hasInitGene = true;
        }
        assignMessageOut.clear();
        for (Map.Entry<String, HashSet<AssignMessage>> entry : assignMessageIn.entrySet()) {
            if (!generates.containsKey(entry.getKey())) {
                assignMessageOut.put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<String, AssignMessage> message : generates.entrySet()) {
            HashSet<AssignMessage> assignMessages = new HashSet<>();
            assignMessages.add(message.getValue());
            assignMessageOut.put(message.getKey(), assignMessages);
        }
        return change;
    }

    public void spreadConst() {
        for (Map.Entry<String, HashSet<AssignMessage>> entry : assignMessageIn.entrySet()) {
            if (entry.getValue().size() == 1) {
                AssignMessage assignMessage = entry.getValue().iterator().next();
                if (assignMessage.isImm()) {
                    spreadConst(assignMessage.getVar(), assignMessage.getValue());
                }
            }
        }
    }

    private void spreadConst(Operand from, Operand to) {
        int assignIndex = Integer.MAX_VALUE;
        if (assigns.containsKey(from.getMark())) {
            assignIndex = assigns.get(from.getMark()).get(0);
        }
        if (uses.containsKey(from.getMark())) {
            Iterator<Integer> iterator = uses.get(from.getMark()).iterator();
            while (iterator.hasNext()) {
                int index = iterator.next();
                if (index > assignIndex) {
                    break;
                }
                ArrayLoad arrayLoad = (ArrayLoad) midCodes.get(index);
                midCodes.set(index, new Arithmetic('+',
                        to, new ImmediateOperand(0), arrayLoad.getOperandTarget()));
                iterator.remove();
            }
        }
    }

    public void spreadConstWithinBlock() {
        for (Map.Entry<String, ArrayList<Integer>> entry : assigns.entrySet()) {
            if (entry.getKey().charAt(0) != '.' && uses.containsKey(entry.getKey())) {
                MidCode midCode = midCodes.get(entry.getValue().get(0));
                VarSymbol var;
                if (midCode instanceof ArrayStore) {
                    var = ((ArrayStore) midCode).getArrayTarget();
                }
                else {
                    var = ((ParaDeclare) midCode).getPara();
                }
                if (var.isRegAble() && !var.isGlobal() && !var.isSpreadAddress()) {
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        int startIndex = entry.getValue().get(i);
                        int endIndex = i == entry.getValue().size() - 1 ? Integer.MAX_VALUE :
                                entry.getValue().get(i + 1);
                        if (midCodes.get(startIndex) instanceof ParaDeclare) {
                            continue;
                        }
                        ArrayStore arrayStore = (ArrayStore) midCodes.get(startIndex);
                        if (arrayStore.getOperandSource() instanceof ImmediateOperand) {
                            Iterator<Integer> iterator = uses.get(entry.getKey()).iterator();
                            while (iterator.hasNext()) {
                                int index = iterator.next();
                                if (index > endIndex) {
                                    break;
                                }
                                if (index > startIndex) {
                                    if (midCodes.get(index) instanceof ArrayLoad) {
                                        ArrayLoad arrayLoad = (ArrayLoad) midCodes.
                                                get(index);
                                        midCodes.set(index, new Arithmetic('+',
                                                arrayStore.getOperandSource(),
                                                new ImmediateOperand(0),
                                                arrayLoad.getOperandTarget()));
                                        iterator.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void initAssignUseChain() {
        HashMap<String, AssignMessage> var2assign = new HashMap<>();
        for (Map.Entry<String, HashSet<AssignMessage>> entry : assignMessageIn.entrySet()) {
            var2assign.put(entry.getKey(), null);
        }
        for (int i = 0; i < midCodes.size(); i++) {
            MidCode midCode = midCodes.get(i);
            if (midCode instanceof ParaDeclare || midCode instanceof ArrayLoad ||
                    midCode instanceof ArrayStore || midCode instanceof Arithmetic) {
                if (midCode instanceof ParaDeclare || midCode instanceof ArrayStore) {
                    VarSymbol var;
                    if (midCode instanceof ParaDeclare) {
                        var = ((ParaDeclare) midCode).getPara();
                    }
                    else {
                        var = ((ArrayStore) midCode).getArrayTarget();
                    }
                    if (var.isRegAble() && !var.isGlobal()) {
                        if (!var.isSpreadAddress() || midCode instanceof ParaDeclare) {
                            if (i == generates.get(var.getMark()).getIndex()) {
                                var2assign.put(var.getMark(), generates.get(var.getMark()));
                            }
                            else {
                                AssignMessage assignMessage = new AssignMessage(this, i, var);
                                flowGraph.addAssignUseChain(assignMessage);
                                var2assign.put(var.getMark(), assignMessage);
                            }
                        }
                        else {
                            if (var2assign.get(var.getMark()) == null) {
                                flowGraph.unionChain(assignMessageIn.get(var.getMark()));
                                AssignMessage assignMessage = assignMessageIn.
                                        get(var.getMark()).iterator().next().getRoot();
                                var2assign.put(var.getMark(), assignMessage);
                                flowGraph.addUseMessage(assignMessage, this, i);
                            }
                            else {
                                flowGraph.addUseMessage(var2assign.get(var.getMark()), this, i);
                            }
                        }
                    }
                }
                else {
                    VarSymbol var;
                    if (midCode instanceof ArrayLoad) {
                        var = ((ArrayLoad) midCode).getArrayIdent();
                    }
                    else {
                        Arithmetic arithmetic = (Arithmetic) midCode;
                        if (arithmetic.containsAddress() &&
                                arithmetic.getAddressVar().isSpreadAddress()) {
                            var = arithmetic.getAddressVar();
                        }
                        else {
                            continue;
                        }
                    }
                    if (var.isRegAble() && !var.isGlobal()) {
                        if (!var2assign.containsKey(var.getMark())) {
                            try {
                                throw new Exception();
                            } catch (Exception e) {
                                System.out.println(id + "." + i);
                                System.out.println(var.getMark());
                                e.printStackTrace();
                            }
                        }
                        else if (var2assign.get(var.getMark()) == null) {
                            flowGraph.unionChain(assignMessageIn.get(var.getMark()));
                            AssignMessage assignMessage =
                                    assignMessageIn.get(var.getMark()).iterator().next().getRoot();
                            var2assign.put(var.getMark(), assignMessage);
                            flowGraph.addUseMessage(assignMessage, this, i);
                        }
                        else {
                            flowGraph.addUseMessage(var2assign.get(var.getMark()), this, i);
                        }
                    }
                }
            }
        }
    }

    public boolean initActiveVarMessage() {
        boolean change = false;
        int preOut = activeVarOut.size();
        activeVarOut.clear();
        for (BaseBlock out : outBlocks) {
            activeVarOut.addAll(out.activeVarIn);
        }
        if (activeVarOut.size() != preOut) {
            change = true;
        }
        if (!change && !activeVarIn.isEmpty()) {
            return false;
        }
        if (!hasInitUseDef) {
            for (int i = 0; i < midCodes.size(); i++) {
                MidCode midCode = midCodes.get(i);
                if (midCode instanceof FuncCall) {
                    useAndDefIndexes.add(i);
                }
                else if (midCode instanceof Arithmetic) {
                    Arithmetic arithmetic = (Arithmetic) midCode;
                    if (arithmetic.containsAddress() &&
                            arithmetic.getAddressVar().isSpreadAddress()) {
                        useAndDefIndexes.add(i);
                        VarSymbol varSymbol = arithmetic.getAddressVar();
                        if (!defBeforeUse.contains(varSymbol)) {
                            useBeforeDef.add(varSymbol);
                        }
                    }
                }
                else if (midCode instanceof ParaDeclare || midCode instanceof ArrayStore) {
                    VarSymbol var;
                    if (midCode instanceof ParaDeclare) {
                        var = ((ParaDeclare) midCode).getPara();
                    } else {
                        var = ((ArrayStore) midCode).getArrayTarget();
                    }
                    if (var.isRegAble() && !var.isGlobal()) {
                        if (!useBeforeDef.contains(var)) {
                            if (!var.isSpreadAddress() || midCode instanceof ParaDeclare) {
                                defBeforeUse.add(var);
                            }
                            else if (!defBeforeUse.contains(var)) {
                                useBeforeDef.add(var);
                            }
                        }
                        useAndDefIndexes.add(i);
                    }
                } else if (midCode instanceof ArrayLoad) {
                    VarSymbol var = ((ArrayLoad) midCode).getArrayIdent();
                    if (var.isRegAble() && !var.isGlobal()) {
                        if (!defBeforeUse.contains(var)) {
                            useBeforeDef.add(var);
                        }
                        useAndDefIndexes.add(i);
                    }
                }
            }
            hasInitUseDef = true;
        }
        activeVarIn.addAll(activeVarOut);
        activeVarIn.addAll(useBeforeDef);
        for (VarSymbol def : defBeforeUse) {
            activeVarIn.remove(def);
        }
        return change;
    }

    public void initConflictMap() {
        HashSet<VarSymbol> nextIsUse = new HashSet<>();
        HashSet<VarSymbol> nextIsDef = new HashSet<>();
        for (int i = useAndDefIndexes.size() - 1; i >= 0; i--) {
            int index = useAndDefIndexes.get(i);
            MidCode midCode = midCodes.get(index);
            char ud;
            VarSymbol var;
            if (midCode instanceof FuncCall) {
                FuncCall funcCall = (FuncCall) midCode;
                for (VarSymbol varSymbol : nextIsUse) {
                    funcCall.addUse(varSymbol);
                }
                for (VarSymbol varSymbol : activeVarOut) {
                    if (!nextIsDef.contains(varSymbol) && !nextIsUse.contains(varSymbol)) {
                        funcCall.addUse(varSymbol);
                    }
                }
            }
            else {
                if (midCode instanceof ParaDeclare) {
                    ud = 'd';
                    var = ((ParaDeclare) midCode).getPara();
                }
                else if (midCode instanceof ArrayStore) {
                    var = ((ArrayStore) midCode).getArrayTarget();
                    if (var.isSpreadAddress()) {
                        ud = 'u';
                    }
                    else {
                        ud = 'd';
                    }
                }
                else if (midCode instanceof Arithmetic) {
                    var = ((Arithmetic) midCode).getAddressVar();
                    ud = 'u';
                }
                else {
                    var = ((ArrayLoad) midCode).getArrayIdent();
                    ud = 'u';
                }
                flowGraph.addNode(var);
                if (ud == 'd') {
                    for (VarSymbol varSymbol : nextIsUse) {
                        flowGraph.addConflict(var, varSymbol);
                    }
                    for (VarSymbol varSymbol : activeVarOut) {
                        if (!nextIsDef.contains(varSymbol) && !nextIsUse.contains(varSymbol)) {
                            flowGraph.addConflict(var, varSymbol);
                        }
                    }
                    nextIsUse.remove(var);
                    nextIsDef.add(var);
                }
                else {
                    nextIsDef.remove(var);
                    nextIsUse.add(var);
                }
            }
        }
    }

    public void removeCode(int index) {
        midCodes.set(index, new None());
    }

    public void addInBlock(BaseBlock baseBlock) {
        inBlocks.add(baseBlock);
    }

    public void addOutBlock(BaseBlock baseBlock) {
        outBlocks.add(baseBlock);
    }

    public String getId() {
        if (id == 0) {
            return "startBlock";
        }
        else if (id == 1) {
            return "endBlock";
        }
        return "block" + id;
    }

    public MidCode get(int index) {
        return midCodes.get(index);
    }

    public ArrayList<MidCode> getMidCodes() {
        return midCodes;
    }

    public void fillUseRegs() {
        for (MidCode midCode : midCodes) {
            if (midCode instanceof FuncCall) {
                HashSet<String> regs = ((FuncCall) midCode).getWillUseInFunc();
                for (String reg : regs) {
                    flowGraph.addReg(reg);
                }
            }
        }
    }

    public boolean noIn() {
        return inBlocks.isEmpty();
    }

    public void removeMeForNextBlocks() {
        for (BaseBlock baseBlock : outBlocks) {
            baseBlock.inBlocks.remove(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder codes = new StringBuilder();
        int i = 0;
        for (MidCode midCode : midCodes) {
            codes.append(i++).append(": ").append(midCode.toString());
        }
        StringBuilder in = new StringBuilder("  in: ");
        for (BaseBlock baseBlock : inBlocks) {
            in.append(baseBlock.getId()).append("; ");
        }
        StringBuilder out = new StringBuilder("  out: ");
        for (BaseBlock baseBlock : outBlocks) {
            out.append(baseBlock.getId()).append("; ");
        }
        StringBuilder assignIn = new StringBuilder("assignIn:  ");
        for (Map.Entry<String, HashSet<AssignMessage>> entry : assignMessageIn.entrySet()) {
            for (AssignMessage assignMessage : entry.getValue()) {
                assignIn.append(assignMessage.toString()).append(";  ");
            }
        }
        assignIn.append("\n");
        StringBuilder assignOut = new StringBuilder("assignOut:  ");
        for (Map.Entry<String, HashSet<AssignMessage>> entry : assignMessageOut.entrySet()) {
            for (AssignMessage assignMessage : entry.getValue()) {
                assignOut.append(assignMessage.toString()).append(";  ");
            }
        }
        StringBuilder activeIn = new StringBuilder("activeIn:  ");
        for (VarSymbol varSymbol : activeVarIn) {
            activeIn.append(varSymbol.getMark()).append(";");
        }
        activeIn.append("\n");
        StringBuilder activeOut = new StringBuilder("activeOut:  ");
        for (VarSymbol varSymbol : activeVarOut) {
            activeOut.append(varSymbol.getMark()).append(";");
        }
        activeOut.append("\n");
        assignOut.append("\n");
        return getId() + ":" + in + out + "\n" +
                assignIn + assignOut + activeIn + activeOut + codes + "\n\n";
    }
}
