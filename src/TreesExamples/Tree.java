package TreesExamples;

public abstract class Tree<T extends Comparable<T>> {
    protected TreeNode<T> root;

    public void insertData(T newData){
        if (this.root == null){
            this.root = new TreeNode<>(newData);
            return;
        }
        insertData(newData, this.root);
    }

    private void insertData(T newData, TreeNode<T> tempNode){
        int comparison = tempNode.getData().compareTo(newData);
        if(comparison > 0){
            // lets check if the tempNode data is greater than newdata
            // It means that we insert at the left!!
            if (tempNode.getLeft() == null){
                // lets insert the new data as a new node at the left
                tempNode.setLeft(new TreeNode<>(newData));
                return;
            }
            insertData(newData, tempNode.getLeft());
        } else if (comparison < 0) {
            // since tempNode data is less than newData, we are going to
            // insert the node at the right!!
            if (tempNode.getRight() == null){
                // lets insert the new data as a new node at the right
                tempNode.setRight(new TreeNode<>(newData));
                return;
            }
            insertData(newData, tempNode.getRight());
        }
    }

    protected abstract TreeNode<T> getTreeNode(T newData);

    public boolean deleteData(T dataToSearch){
        // Let's look for the node that we need to delete
        TreeNode<T> tempNode = this.root;
        TreeNode<T> tempPrevNode = null;
        boolean isLeftChild = false;
        while(tempNode!=null){
            int comparison = tempNode.getData().compareTo(dataToSearch);
            if (comparison < 0) {
                tempPrevNode = tempNode;
                tempNode = tempNode.getRight();
                isLeftChild = false;
            } else if (comparison > 0) {
                tempPrevNode = tempNode;
                tempNode = tempNode.getLeft();
                isLeftChild = true;
            } else {
                // It has to be the node that we're looking for.
                break;
            }
        }
        //Case #1: Either Tree is empty or the node was not found
        if (tempNode == null){
            return false;
        }
        TreeNode<T> replaceNode = null;
        if ( tempNode.getRight() != null && tempNode.getLeft() != null){
            // Case #2: The node have both child.
            // Let's get the MinMax or MaxMin
            replaceNode = getMinMax(tempNode, true);
            // Let's check if the replaceNode is the direct child
            if (tempNode.getRight() == replaceNode){
                replaceNode.setLeft(tempNode.getLeft());
            } else {
                // Let's link the right and left from the node where we were
                // to the new node that is going to replace the deleted one.
                replaceNode.setRight(tempNode.getRight());
                replaceNode.setLeft(tempNode.getLeft());
            }
        }
        else if (tempNode.getRight() != null || tempNode.getLeft() != null) {
            // Case #3: The node has at least 1 child.
            // Let's set the only child left to the previous node
            replaceNode = tempNode.getRight() != null ? tempNode.getRight() : tempNode.getLeft();
        }
        // Case #4: The node doesn't have any children.
        // This step is also used by all cases to
        if (tempPrevNode != null) {
            if (isLeftChild){
                tempPrevNode.setLeft(replaceNode);
            } else {
                tempPrevNode.setRight(replaceNode);
            }
        } else {
            // we're about to delete the root
            this.root = replaceNode;
        }
        return true;
    }

    public TreeNode<T> getMinMax(TreeNode<T> tempNode, boolean unLinkNode){
        return this.getMinMin(tempNode.getRight(), unLinkNode);
    }

    public TreeNode<T> getMinMin(){
        if (this.root == null ){
            return null;
        }
        return this.getMinMin(this.root, false);
    }

    private TreeNode<T> getMinMin(TreeNode<T> tempNode, boolean unLinkNode){
        TreeNode<T> tempPrevNode = null;
        while (tempNode.getLeft() != null){
            tempPrevNode = tempNode;
            tempNode = tempNode.getLeft();
        }
        if (tempPrevNode !=null && unLinkNode){
            // this is to unlink the tempNode that we're going to return
            // and link the right nodes to the left
            tempPrevNode.setLeft(tempNode.getRight());
        }
        return tempNode;
    }

    protected TreeNode<T> getMaxMin(TreeNode<T> tempNode){
        return this.getMaxMax(tempNode.getLeft(),true);
    }

    public TreeNode<T> getMaxMax(){
        if (this.root == null ){
            return null;
        }
        return this.getMaxMax(this.root, false);
    }

    private TreeNode<T> getMaxMax(TreeNode<T> tempNode, boolean unLinkNode){
        TreeNode<T> tempPrevNode = null;
        while (tempNode.getRight() != null){
            tempPrevNode = tempNode;
            tempNode = tempNode.getRight();
        }
        if (tempPrevNode != null && unLinkNode){
            // this is to unlink the tempNode that we're going to return
            tempPrevNode.setRight(tempNode.getLeft());
        }
        return tempNode;
    }

    protected boolean searchData(T newData){
        if (this.root == null){
            return false;
        }
        return this.searchData(newData, this.root);
    }

    private boolean searchData(T newData, TreeNode<T> tempNode){
        if (tempNode == null){
            return false;
        }
        int comparison = tempNode.getData().compareTo(newData);
        if (comparison < 0){
            return searchData(newData, tempNode.getRight());
        } else if (comparison > 0){
            return searchData(newData, tempNode.getLeft());
        }
        // Lets check if my current node is the node that I'm looking for
        return tempNode.getData().equals(newData);
    }

    public void printTreeContent(){
        printTreeContentLeftToRight(this.root);
    }

    private void printTreeContentLeftToRight(TreeNode<T> node){
        // this print is done from Left to Right order
        if (node == null){
            return;
        } else if (node.getLeft()!=null){
            printTreeContentLeftToRight(node.getLeft());
        } else if (node.getRight()!=null){
            printTreeContentLeftToRight(node.getRight());
        }
        System.out.println(".-.-.-.-LEAF.-.-.-.");
        System.out.println(node.getData());
        System.out.println(".-.-.-.-.-.-.-.-.-.");
    }

    private void printTreeContentInDepth(TreeNode<T> node){
        // this print is done from Left to Right order
        if (node == null){
            return;
        }
        System.out.println(".-.-.-.-LEAF.-.-.-.");
        System.out.println(node.getData());
        System.out.println(".-.-.-.-.-.-.-.-.-.");
        if (node.getLeft()!=null){
            printTreeContentInDepth(node.getLeft());
        } else if (node.getRight()!=null){
            printTreeContentInDepth(node.getRight());
        }
    }
}
