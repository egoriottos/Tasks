import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private int numberOfTasks;
    private CyclicBarrier cyclicBarrier;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.cyclicBarrier = new CyclicBarrier(numberOfTasks, this::results);
    }

    public void executeTasks(int numberOfTask) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTask);
        for(int i = 0; i < numberOfTask;i++){
            ComplexTask task = new ComplexTask(i);
            executorService.submit(()->{
                try{
                    task.execute();
                    cyclicBarrier.await();
                }
                catch (BrokenBarrierException | InterruptedException e){
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            });
        }
    }

    public void results() {
        System.out.println("tasks completed " + Thread.currentThread().getName());
    }

}
