package ListExamples.QueueLinkedList;

import ListExamples.DoublyLinkedList.DoublyLinkedList;
import ListExamples.Node;

public class QueueLinkedList <T extends Comparable<T>> extends DoublyLinkedList<T> {
    protected int maxSize;
    protected int currentIndex;

    public QueueLinkedList(){

    }

    public QueueLinkedList(int maxSize){
        this.maxSize = maxSize;
        currentIndex = 0;
    }

    public Node<T> dequeue(){
        //Lets keep a reference to the previous and last
        Node<T> tempLast = this.Last;
        Node<T> tempPrev = this.Last.getPrevious();
        tempPrev.setNext(null);
        //Lets unlinked the last node.
        tempLast.setPrevious(null);
        this.Last = tempPrev;
        return tempLast;
    }

    public void enqueue(T newData) throws Exception{
        if (this.currentIndex < this.maxSize){
            this.insertDataAtBeginning(newData);
            this.currentIndex++;
            return;
        }
        throw new Exception("Queue is full!!");
    }

    public Node<T> peek(){
        //Let's do a deep copy to not be able to traverse the whole queue.
        return new Node<>(this.Last.getData());
    }
}
