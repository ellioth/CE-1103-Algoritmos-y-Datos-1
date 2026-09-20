package ListExamples;

public abstract class LinkedList<T extends Comparable<T>> {
    protected Node<T> First;
    protected Node<T> Last;
    protected int Size;

    public LinkedList(){
        this.First = null;
        this.Last = null;
    }

    protected boolean insertInitialElement(Node<T> newNode){
        this.Size++;
        if (this.First == null){
            this.First = newNode;
            this.Last = newNode;
            return true;
        }
        return false;
    }

    public abstract void insertDataAtBeginning(T newData);

    public abstract void insertDataAtEnd(T newData);

    /**
     * This method is meant to be used in ascending
     * configuration, but it can be override as needed.
     * @param newData
     */
    public abstract void insertDataSorted(T newData);

    public abstract void deleteData(T dataToBeDeleted);

    public boolean searchData(T dataToBeFound){
        Node<T> tempData = this.First;
        while(tempData!=null){
            if(tempData.getData().compareTo(dataToBeFound) == 0){
                return true;
            }
            tempData = tempData.getNext();
        }
        return false;
    }

    protected Node<T> searchAndGetNode(T dataToBeFound){
        Node<T> tempNode = this.First;
        while(tempNode!=null){
            if(tempNode.getData().compareTo(dataToBeFound) == 0){
                return tempNode;
            }
            tempNode = tempNode.getNext();
        }
        return null;
    }

    public int getSize() {
        return Size;
    }

    public void printList(){
        Node<T> tempNode = this.First;
        while(tempNode!=null){
            System.out.println(tempNode.getData());
            tempNode = tempNode.getNext();
        }
    }

    public Node<T> getFirst() {
        return First;
    }

    public Node<T> getLast() {
        return Last;
    }
}
