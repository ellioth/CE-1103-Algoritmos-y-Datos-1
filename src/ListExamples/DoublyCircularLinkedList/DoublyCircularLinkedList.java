package ListExamples.DoublyCircularLinkedList;

import ListExamples.LinkedList;
import ListExamples.Node;

public class DoublyCircularLinkedList<T extends Comparable<T>> extends LinkedList<T> {
    @Override
    public void insertDataAtBeginning(T newData) {
        Node<T> newNode = new Node<>(newData);
        if (this.insertInitialElement(newNode)){
            return;
        }
        Node<T> tempHead = this.First;
        newNode.setNext(tempHead);
        newNode.setPrevious(this.Last);
        tempHead.setPrevious(newNode);
        this.Last.setNext(newNode);
        this.First = newNode;
    }

    @Override
    public void insertDataAtEnd(T newData) {
        Node<T> newNode = new Node<>(newData);
        if (this.insertInitialElement(newNode)){
            return;
        }
        Node<T> tempLast = this.Last;
        newNode.setPrevious(tempLast);
        newNode.setNext(this.First);
        this.First.setPrevious(newNode);
        tempLast.setNext(newNode);
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
        if (this.First == null){
            // when list is empty
            return;
        } if (this.First == this.Last){
            // If only one node and node can be or not.
            if (this.First.getData().equals(dataToBeDeleted)){
                this.First = null;
                this.Last = null;
                this.Size--;
            }
            return;
        } if (this.First.getData().equals(dataToBeDeleted)) {
            // Node to be deleted is the head
            Node<T> tempHead = this.First.getNext();
            tempHead.setPrevious(this.Last);
            this.Last.setNext(tempHead);
            this.First = tempHead;
            this.Size--;
            return;
        }
        // When two or more nodes!!
        boolean found = false;
        Node<T> tempCurrentNode = this.First.getNext();
        while(tempCurrentNode!=this.First){
            if(tempCurrentNode.getData().equals(dataToBeDeleted)){
                found = true;
                break;
            }
            tempCurrentNode = tempCurrentNode.getNext();
        }
        //We have three cases
        if (!found){
            //No node was found!!
            return;
        } else if(tempCurrentNode == this.Last){
            // Node to be deleted is the tail;
            tempCurrentNode.getPrevious().setNext(this.First);
            this.First.setPrevious(tempCurrentNode.getPrevious());
            this.Last = tempCurrentNode.getPrevious();
        } else{
            // Node to be deleted is in between;
            tempCurrentNode.getPrevious().setNext(tempCurrentNode.getNext());
            tempCurrentNode.getNext().setPrevious(tempCurrentNode.getPrevious());

        }
        this.Size--;
    }
}
