package ListExamples.DoublyLinkedList;

import ListExamples.LinkedList;
import ListExamples.Node;

public class DoublyLinkedList<T extends Comparable<T>> extends LinkedList<T>{

    @Override
    public void insertDataAtBeginning(T newData) {
        Node<T> newNode = new Node<>(newData);
        if (this.insertInitialElement(newNode)){
            return;
        }
        Node<T> tempHead = this.First;
        newNode.setNext(tempHead);
        tempHead.setPrevious(newNode);
        this.First = newNode;
    }

    @Override
    public void insertDataAtEnd(T newData) {
        Node<T> newNode = new Node<>(newData);
        if (this.insertInitialElement(newNode)){
            return;
        }
        Node<T> tempLast = this.Last;
        tempLast.setNext(newNode);
        newNode.setPrevious(tempLast);
        this.Last = newNode;
    }

    /**
     * This method will insert the data in ascending order.
     * @param newData Node<T>
     */
    @Override
    public void insertDataSorted(T newData) {
        // 1: List is empty
        if (this.First == null) {
            this.insertDataAtBeginning(newData);
            return;
        }

        // 2: At least one item, it's the smallest, goes to the head/first.
        if (newData.compareTo(this.First.getData()) <= 0) {
            this.insertDataAtBeginning(newData);
            return;
        }

        // 3: At least one item, it's the biggest, goes to the tail/last.
        if (newData.compareTo(this.Last.getData()) >= 0) {
            this.insertDataAtEnd(newData);
            return;
        }
        // 4: Insert at the middle, at least two nodes, it will in between First and Last.
        Node<T> tempPreviousNode = this.First;
        Node<T> tempCurrentNode = this.First.getNext();

        // Let's get the node before the one that we're inserting.
        while (tempCurrentNode != this.First) {
            if (tempCurrentNode.getData().compareTo(newData) > 0) {
                break; // By breaking here: tempPreviousNode < newData < tempCurrentNode
            }
            tempPreviousNode = tempCurrentNode;
            tempCurrentNode = tempCurrentNode.getNext();
        }

        // Let's do the connection among the nodes
        Node<T> newNode = new Node<>(newData);
        newNode.setNext(tempCurrentNode);
        newNode.setPrevious(tempPreviousNode);
        tempPreviousNode.setNext(newNode);
        tempCurrentNode.setPrevious(newNode);
        this.Size++;
    }

    @Override
    public void deleteData(T dataToBeDeleted) {
        Node<T> tempCurrentNode = this.First;
        while(tempCurrentNode!=null){
            if(tempCurrentNode.getData().equals(dataToBeDeleted)){
                break;
            }
            tempCurrentNode = tempCurrentNode.getNext();
        }
        //We have four cases
        if (tempCurrentNode == null){
            // No node was found, early exit
            return;
        }else if(tempCurrentNode == this.First){
            // Node to be deleted is the head;
            this.First = this.First.getNext();
            if (this.First == null)
                this.Last = null;
            else
                this.First.setPrevious(null);
        }
        else if(tempCurrentNode == this.Last){
            // Node to be deleted is the tail;
            this.Last = tempCurrentNode.getPrevious();
            this.Last.setNext(null);
        }
        else{
            // Node to be deleted is in between;
            tempCurrentNode.getPrevious().setNext(tempCurrentNode.getNext());
            tempCurrentNode.getNext().setPrevious(tempCurrentNode.getPrevious());
        }
        this.Size--;
    }
}
