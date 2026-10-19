package TreesExamples.SplayBinaryTree;

import TreesExamples.Tree;
import TreesExamples.TreeNode;

public class SplayBinaryTree<T extends Comparable<T>> extends Tree<T> {

   @Override
    public void insertData(T newData){
        this.root = insertData(newData, this.root);
        this.root = splay(this.root, newData);
    }

    private TreeNode<T> insertData(T newData, TreeNode<T> node){
        if(node == null){
            return new TreeNode<>(newData);
        }
        int comparison = node.getData().compareTo(newData);
        if(comparison > 0){
            node.setLeft(insertData(newData,node.getLeft()));
        }else if(comparison < 0){
            node.setRight(insertData(newData,node.getRight()));
        }
        return node;
    }

    private TreeNode<T> rotateRight(TreeNode<T> parent){
        TreeNode<T> left = parent.getLeft();
        parent.setLeft(left.getRight());
        left.setRight(parent);
        return left;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> parent){
        TreeNode<T> right = parent.getRight();
        parent.setRight(right.getLeft());
        right.setLeft(parent);
        return right;
    }

    private TreeNode<T> splay(TreeNode<T> root, T key){
        if(root == null){
            return null;
        }
        int cmp = root.getData().compareTo(key);
        if(cmp == 0){
            return root;
        }

        // LEFT SIDE
        if(cmp > 0){
            if(root.getLeft() == null){
                return root;
            }
            int leftCmp =
                    root.getLeft()
                            .getData()
                            .compareTo(key);
            // Zig-Zig
            if(leftCmp > 0){
                root.getLeft().setLeft(
                        splay(root.getLeft().getLeft(),key));
                root = rotateRight(root);
            }
            // Zig-Zag
            else if(leftCmp < 0){
                root.getLeft().setRight(
                        splay(root.getLeft().getRight(),key));

                if(root.getLeft().getRight()!= null){
                    root.setLeft(rotateLeft(root.getLeft()));
                }
            }
            if(root.getLeft() == null){
                return root;
            }
            return rotateRight(root);
        }

        // RIGHT SIDE
        else{
            if(root.getRight() == null){
                return root;
            }
            int rightCmp = root.getRight()
                            .getData()
                            .compareTo(key);

            // Zag-Zag
            if(rightCmp < 0){
                root.getRight().setRight(
                        splay(root.getRight().getRight(),key)
                );
                root = rotateLeft(root);
            }

            // Zag-Zig
            else if(rightCmp > 0){
                root.getRight().setLeft(
                        splay(root.getRight().getLeft(),key)
                );
                if(root.getRight().getLeft()!= null){
                    root.setRight(rotateRight(root.getRight()));
                }
            }
            if(root.getRight() == null){
                return root;
            }
            return rotateLeft(root);
        }
    }

    @Override
    protected boolean searchData(T data){
        this.root = splay(this.root, data);

        if( this.root == null ){
            return false;
        }

        return this.root
                .getData()
                .equals(data);
    }

    @Override
    protected TreeNode<T> getTreeNode(T newData) {
        return null;
    }
}
