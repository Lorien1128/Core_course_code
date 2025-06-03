package exp;

import arithtool.Arith;

public class Tree {
    private Tree leftNode;
    private Tree rightNode;
    private Expression value;
    private String op;

    public Tree(Expression value) {
        this.value = value;
    }

    public Tree(String op) {
        this.op = op;
    }

    private void clear() {
        op = null;
        leftNode = null;
        rightNode = null;
        value = null;
    }

    public Tree getResult() {
        if (op == null) {
            return this;
        } else if (op.equals("+")) {
            Expression rightExpr = rightNode.getResult().getValue();
            Expression leftExpr = leftNode.getResult().getValue();
            Expression ans = Arith.plus(leftExpr, rightExpr);
            clear();
            value = ans;
            return this;
        } else if (op.equals("-")) {
            Expression rightExpr = rightNode.getResult().getValue();
            Expression leftExpr = leftNode.getResult().getValue();
            rightExpr.reverse();
            Expression ans = Arith.plus(leftExpr, rightExpr);
            clear();
            value = ans;
            return this;
        } else if (op.equals("*")) {
            Expression rightExpr = rightNode.getResult().getValue();
            Expression leftExpr = leftNode.getResult().getValue();
            Expression ans = Arith.multiply(leftExpr, rightExpr);
            clear();
            value = ans;
            return this;
        } else if (op.equals("**")) {
            Expression rightExpr = rightNode.getResult().getValue();
            Expression leftExpr = leftNode.getResult().getValue();
            Expression ans = Arith.power(leftExpr, rightExpr.getTerms().get(0));
            clear();
            value = ans;
            return this;
        } else {
            return this;
        }
    }

    public Tree getLeftNode() {
        return leftNode;
    }

    public Tree getRightNode() {
        return rightNode;
    }

    public Expression getValue() {
        return value;
    }

    public void setLeftNode(Tree leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Tree rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
