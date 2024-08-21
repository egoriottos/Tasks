import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue <T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int size;

    public BlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while(queue.size()==size){
            wait();
        }
        queue.add(item);
        notifyAll();
    }
    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()){
            wait();
        }
        T item = queue.poll();
        notifyAll();
        return item;
    }
}
