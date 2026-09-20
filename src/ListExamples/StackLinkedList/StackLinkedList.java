package ListExamples.StackLinkedList;

import ListExamples.Node;
import ListExamples.SimpleLinkedList.SimpleLinkedList;

public class StackLinkedList <T extends Comparable<T>> extends SimpleLinkedList<T> {
    private int maxSize;
    private int CurrentIndex;

    public StackLinkedList(int maxSize){
        this.maxSize = maxSize;
        this.CurrentIndex = 0;
    }

    public Node<T> pop(){
        //If stack is empty!!
        if (this.CurrentIndex == 0 ){
            return null;
        }
        Node<T> tempHead = this.First;
        this.First = this.First.getNext();
        // if First == Last -> hence, only one node
        if (this.First == null) {
            this.Last = null;
        }
        tempHead.setNext(null);
        return tempHead;
    }

    public void push(T newData) throws Exception{
        if (this.CurrentIndex < this.maxSize){
            this.insertDataAtBeginning(newData);
            this.CurrentIndex++;
            return;
        }
        throw new Exception("Stack is full!!");
    }

    public Node<T> peek(){
        //Let's do a deep copy to not be able to traverse the whole stack.
        Node<T> tempHead = new Node<>(this.First.getData());
        return this.First;
    }
}
