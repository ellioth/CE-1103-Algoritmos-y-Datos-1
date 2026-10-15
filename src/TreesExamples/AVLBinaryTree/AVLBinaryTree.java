package TreesExamples.AVLBinaryTree;

import TreesExamples.Tree;
import TreesExamples.TreeNode;

public class AVLBinaryTree <T extends Comparable<T>> extends Tree<T> {

    @Override
    public void insertData(T newData) {
        super.insertData(newData);
        // Let's check if we need to balance the tree
        this.root = checkAndRotate(this.root);
    }

    private int getBalanceFactor(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int rightHeight = getHeight(node.getRight());
        int leftHeight = getHeight(node.getLeft());
        // Let's check if we need to balance the tree.
        return rightHeight - leftHeight;
    }

    private int getHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private TreeNode<T> checkAndRotateAux(TreeNode<T> node){
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1){
            // Let's do a rotation to the Left!!
            // Let's check if the balance factor of my right child is also positive
            if (getBalanceFactor(node.getRight()) >= 0){
                // we need to do a RR rotate.
                return rotateRR(node);
            }
            // We need to do a RL rotation
            return rotateRL(node);
        } else if (balanceFactor < -1){
            // Let's do a rotation to the Right!!
            // Let's check if the balance factor of my left child is also negative
            if (getBalanceFactor(node.getLeft()) <= 0){
                // we need to do a LL rotate.
                return rotateLL(node);
            }
            // We need to do a LR rotation
            return rotateLR(node);
        }
        // No rotation was needed.
        return node;
    }

    private TreeNode<T> checkAndRotate(TreeNode<T> node){
        if (node == null){
            return null;
        }
        // Let's traversee the tree bottom to top and check if we need to do a rotation
        node.setLeft(checkAndRotate(node.getLeft()));
        node.setRight(checkAndRotate(node.getRight()));

        return checkAndRotateAux(node);
    }

    private TreeNode<T> rotateLL(TreeNode<T> tempParent){
        // We need to roate to the right because my child nodes are heavier on the left - left side
        TreeNode<T> firstNode = tempParent.getLeft();
        tempParent.setLeft(firstNode.getRight());
        firstNode.setRight(tempParent);
        return firstNode;
    }

    private TreeNode<T> rotateRR(TreeNode<T> tempParent){
        // We need to roate to the left because my child nodes are heavier on the right - right side
        TreeNode<T> firstNode = tempParent.getRight();
        tempParent.setRight(firstNode.getLeft());
        firstNode.setLeft(tempParent);
        return firstNode;
    }

    private TreeNode<T> rotateLR(TreeNode<T> tempParent){
        // We need to rotate to the right because my child nodes are heavier on the left - right side
        TreeNode<T> leftNode = tempParent.getLeft();
        TreeNode<T> rightLeftNode = leftNode.getRight(); //aka new head
        // Let's connect the childs of leftRightNode to other nodes.
        tempParent.setLeft(rightLeftNode.getRight());
        leftNode.setRight(rightLeftNode.getLeft());
        // Let's connect the tempParent and rightNode to the new head.
        rightLeftNode.setRight(tempParent);
        rightLeftNode.setLeft(leftNode);
        // Let's return the new head
        return rightLeftNode;
    }

    private TreeNode<T> rotateRL(TreeNode<T> tempParent){
        // We need to roate to the right because my child nodes are heavier on the right - left side
        TreeNode<T> rightNode = tempParent.getRight();
        TreeNode<T> leftRightNode = rightNode.getLeft(); // aka new head
        // Let's connect the childs of leftRightNode to other nodes.
        tempParent.setRight(leftRightNode.getLeft());
        rightNode.setLeft(leftRightNode.getRight());
        // Let's connect the tempParent and rightNode to the new head.
        leftRightNode.setLeft(tempParent);
        leftRightNode.setRight(rightNode);
        // Let's return the new head
        return leftRightNode;
    }

    @Override
    protected TreeNode<T> getTreeNode(T newData) {
        return null;
    }
}
