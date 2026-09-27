package TreesExamples.AVLBinaryTree;

import TreesExamples.Tree;
import TreesExamples.TreeNode;

public class AVLBinaryTree <T extends Comparable<T>> extends Tree<T> {

    @Override
    public void insertData(T newData) {
        super.insertData(newData);
        // Let's check if we need to balance the tree
    }

    @Override
    protected TreeNode<T> getTreeNode(T newData) {
        return null;
    }
}
