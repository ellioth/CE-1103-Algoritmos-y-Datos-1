package ListExamples.QueueLinkedList;

public class PriorityQueueLinkedList<T extends Comparable<T>> extends QueueLinkedList<T> {

    public PriorityQueueLinkedList(int maxSize){
        this.maxSize = maxSize;
        this.currentIndex = 0;
    }

    @Override
    public void enqueue(T newData) throws Exception{
        if (this.currentIndex < this.maxSize){
            this.insertDataSorted(newData);
            this.currentIndex++;
            return;
        }
        throw new Exception("Queue is full!!");
    }
}
