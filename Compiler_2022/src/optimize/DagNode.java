package optimize;

import mcode.operand.Operand;

public class DagNode {
    private final DagNode left;
    private final DagNode right;
    private final String mark;
    private int curFathers = 0;
    private final int index;

    public DagNode(Operand operand, int index) {
        this.mark = operand.getMark();
        left = null;
        right = null;
        this.index = index;
    }

    public DagNode(String mark, DagNode node, int index) {
        this.mark = mark;
        left = node;
        left.addAFather();
        right = null;
        this.index = index;
    }

    public DagNode(String mark, DagNode left, DagNode right, int index) {
        this.mark = mark;
        this.left = left;
        left.addAFather();
        this.right = right;
        right.addAFather();
        this.index = index;
    }

    private void addAFather() {
        curFathers += 1;
    }

    public void removeAFather() {
        curFathers -= 1;
    }

    public void removeFromDag() {
        if (left != null) {
            left.removeAFather();
        }
        if (right != null) {
            right.removeAFather();
        }
    }

    public boolean hasNoFather() {
        return curFathers == 0;
    }

    public String getMark() {
        return mark;
    }

    public DagNode getLeft() {
        return left;
    }

    public DagNode getRight() {
        return right;
    }

    public int getIndex() {
        return index;
    }
}
