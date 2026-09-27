package TreesExamples;

public class TreeNode<T> {
    private TreeNode<T> right;
    private TreeNode<T> left;
    private T data;

    public TreeNode(T element) {
        this(element, null, null);
    }

    public TreeNode(T element, TreeNode<T> left, TreeNode<T> right) {
        this.data = element;
        this.left = left;
        this.right = right;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
