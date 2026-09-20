package ListExamples.CircularLinkedList;

import ListExamples.LinkedList;
import ListExamples.Node;

public class CircularLinkedList<T extends Comparable<T>> extends LinkedList<T> {

    @Override
    public void insertDataAtBeginning(T newData) {
        Node<T> newNode = new Node<>(newData);
        if (this.insertInitialElement(newNode)){
            return;
        }
        Node<T> tempHead = this.First;
        newNode.setNext(tempHead);
        this.First = newNode;
        this.Last.setNext(newNode);
    }

    @Override
    public void insertDataAtEnd(T newData) {
        Node<T> newNode = new Node<>(newData);
        if (this.insertInitialElement(newNode)){
            return;
        }
        Node<T> tempLast = this.Last;
        tempLast.setNext(newNode);
        this.Last = newNode;
        newNode.setNext(this.First);
    }

    /**
     * This method will insert the data in ascending order.
     * @param newData Node<T>
     */
    @Override
    public void insertDataSorted(T newData) {
        /**
         * 1-No data at all:
         *      this.Last = null
         *      this.First = null
         *      tempCurrentNode = null
         *      tempPreviousNode = null
         * 2-Only one node:
         *   F/L
         *   TC/TP
         *   1 -> null
         * 2.1 Data at the head:
         *      this.Last = node1
         *      this.First = node1
         *      tempCurrentNode = node1
         *      tempPreviousNode = node1
         * 2.2 Data at the tail:
         *      this.Last = node1
         *      this.First = node1
         *      tempCurrentNode = null
         *      tempPreviousNode = node1
         * 3-Two or more nodes:
         *    F     L
         *   TC    TP
         * -> 1 -> 3 ->
         *    NN
         * -> 0 -> 1 -> 3 ->
         * 3.1 Data at the head:
         *      this.Last = node2
         *      this.First = node1
         *      tempCurrentNode = null
         *      tempPreviousNode = null
         *    TP  TC
         * -> 1 -> 3 -> 5 ->
         * 3.2 Data at the tail:
         *      this.Last = node2
         *      this.First = node1
         *      tempCurrentNode = node1
         *      tempPreviousNode = node2
         *    TP   NN   TC
         * -> 1 -> 2 -> 3 ->
         * 3.3 Data at the middle:
         *      this.Last = node2
         *      this.First = node1
         *      tempCurrentNode = null
         *      tempPreviousNode = null
         */
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

        // Let's do the connection amongh the nodes
        Node<T> newNode = new Node<>(newData);
        newNode.setNext(tempCurrentNode);
        tempPreviousNode.setNext(newNode);
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
            this.First = this.First.getNext();
            this.Last.setNext(this.First);
            this.Size--;
            return;
        }
        // When two or more nodes!!
        boolean found = false;
        Node<T> tempCurrentNode = this.First.getNext();
        Node<T> tempPreviousNode = this.First;
        while(tempCurrentNode!=this.First){
            if(tempCurrentNode.getData().equals(dataToBeDeleted)){
                found = true;
                break;
            }
            tempPreviousNode = tempCurrentNode;
            tempCurrentNode = tempCurrentNode.getNext();
        }
        //We have three cases
        if (!found){
            //No nodes to be deleted found!!
            return;
        } else if(tempCurrentNode == this.Last){
            // Node to be deleted is the tail;
            tempPreviousNode.setNext(this.First);
            this.Last = tempPreviousNode;
        } else{
            // Node to be deleted is in between;
            tempPreviousNode.setNext(tempCurrentNode.getNext());
        }
        this.Size--;
    }
}
