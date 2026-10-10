package TreesExamples.AVLBinaryTree;

import TreesExamples.Tree;
import TreesExamples.TreeNode;

public class AVLBinaryTree <T extends Comparable<T>> extends Tree<T> {

    @Override
    public void insertData(T newData) {
        super.insertData(newData);
        // Let's check if we need to balance the tree
        checkAndRotate(this.root, getBalanceFactor(this.root));
    }

    private int getBalanceFactor(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int rightHeight = getHeight(node.getRight());
        int leftHeight = getHeight(node.getLeft());
        int tempBalanceFactor = rightHeight - leftHeight;
        // Let's check if we need to balance the tree.
        return getBalanceFactor(checkAndRotate(node, tempBalanceFactor));
    }

    private int getHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private TreeNode<T> checkAndRotate(TreeNode<T> node, int balanceFactor){
        if (balanceFactor == 2){
            // Let's do a rotation to the right!!
            // TODO: implement the rotation and calculate the heigh once again to double check
        } else if (balanceFactor == -2){
            // Let's do a rotation to the left!!
            // TODO: implement the rotation and calculate the heigh once again to double check
        }
        return node;
    }

    private TreeNode<T> rotateLL(TreeNode<T> tempParent){
        // We need to roate to the right because my child nodes are heavier on the left - left side
        TreeNode<T> newRoot = tempParent;

        return newRoot;
    }

    private TreeNode<T> rotateRR(TreeNode<T> tempParent){
        // We need to roate to the left because my child nodes are heavier on the right - right side
        TreeNode<T> newRoot = tempParent;

        return newRoot;
    }

    private TreeNode<T> rotateLR(TreeNode<T> tempParent){
        // We need to roate to the right because my child nodes are heavier on the left - right side
        TreeNode<T> newRoot = tempParent;

        return newRoot;
    }

    private TreeNode<T> rotateRL(TreeNode<T> tempParent){
        // We need to roate to the right because my child nodes are heavier on the right - left side
        TreeNode<T> newRoot = tempParent;

        return newRoot;
    }

    @Override
    protected TreeNode<T> getTreeNode(T newData) {
        return null;
    }
}
