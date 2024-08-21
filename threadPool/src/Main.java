public class Main{
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);
        Thread thread1 = new Thread(new ThreadOne(blockingQueue));
        Thread thread2 = new Thread(new ThreadTwo(blockingQueue));
        thread1.start();
        thread2.start();

    }
}
class ThreadOne implements Runnable{
    private final BlockingQueue<Integer> blockingQueue;

    public ThreadOne(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i<5; i++){
                System.out.println("threadAdd " + i);
                blockingQueue.enqueue(i);
            }
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
class ThreadTwo implements Runnable{
    private final BlockingQueue<Integer> queue;

    public ThreadTwo(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            for (int i= 0; i < 5; i++){
                System.out.println("ThreadGet " + i);
                queue.dequeue();
            }
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
