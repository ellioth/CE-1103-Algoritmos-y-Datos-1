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

    private TreeNode<T> rotateZig(TreeNode<T> parent){
        TreeNode<T> left = parent.getLeft();
        parent.setLeft(left.getRight());
        left.setRight(parent);
        return left;
    }

    private TreeNode<T> rotateZag(TreeNode<T> parent){
        TreeNode<T> right = parent.getRight();
        parent.setRight(right.getLeft());
        right.setLeft(parent);
        return right;
    }

    private TreeNode<T> splay(TreeNode<T> tempNode, T key){
        if(tempNode == null){
            return null;
        }
        int cmp = tempNode.getData().compareTo(key);
        if(cmp == 0){
            return tempNode;
        }

        // LEFT SIDE
        if(cmp > 0){
            if(tempNode.getLeft() == null){
                return tempNode;
            }
            int leftCmp =
                    tempNode.getLeft()
                            .getData()
                            .compareTo(key);
            // Zig-(Zig)
            if(leftCmp > 0){
                tempNode.getLeft().setLeft(
                        splay(tempNode.getLeft().getLeft(),key));
                tempNode = rotateZig(tempNode);
            }
            // Zig-(Zag)
            else if(leftCmp < 0){
                tempNode.getLeft().setRight(
                        splay(tempNode.getLeft().getRight(),key));

                if(tempNode.getLeft().getRight()!= null){
                    tempNode.setLeft(rotateZag(tempNode.getLeft()));
                }
            }
            if(tempNode.getLeft() == null){
                return tempNode;
            }
            // Zig
            return rotateZig(tempNode);
        }

        // RIGHT SIDE
        else{
            if(tempNode.getRight() == null){
                return tempNode;
            }
            int rightCmp = tempNode.getRight()
                            .getData()
                            .compareTo(key);

            // Zag-(Zag)
            if(rightCmp < 0){
                tempNode.getRight().setRight(
                        splay(tempNode.getRight().getRight(),key)
                );
                tempNode = rotateZag(tempNode);
            }

            // Zag-(Zig)
            else if(rightCmp > 0){
                tempNode.getRight().setLeft(
                        splay(tempNode.getRight().getLeft(),key)
                );
                if(tempNode.getRight().getLeft()!= null){
                    tempNode.setRight(rotateZig(tempNode.getRight()));
                }
            }
            if(tempNode.getRight() == null){
                return tempNode;
            }
            // Zag
            return rotateZag(tempNode);
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
