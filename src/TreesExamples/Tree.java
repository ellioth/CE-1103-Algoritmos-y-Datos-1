package TreesExamples;

public abstract class Tree<T extends Comparable<T>> {
    protected TreeNode<T> root;

    protected abstract void insertData(T newData);

    protected abstract boolean deleteData(T newData);

    protected abstract TreeNode<T> getTreeNode(T newData);

    protected abstract boolean searchData(T newData);

    public void printTreeContent(){
        printTreeContentAux(this.root);
    }

    private void printTreeContentAux(TreeNode<T> node){
        if (node == null){
            return;
        } else if (node.getLeft()!=null){
            printTreeContentAux(node.getLeft());
        } else if (node.getRight()!=null){
            printTreeContentAux(node.getRight());
        }
        System.out.println(".-.-.-.-LEAF.-.-.-.");
        System.out.println(node.getData());
        System.out.println(".-.-.-.-.-.-.-.-.-.");
    }
}
