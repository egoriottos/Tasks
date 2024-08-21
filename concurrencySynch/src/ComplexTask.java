public class ComplexTask {
    private final int id;

    public ComplexTask(int id) {
        this.id = id;
    }
    public void execute(){
        System.out.println(id +"Thread get info = " + Thread.currentThread().getName());
        try{
            Thread.sleep(300);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
}
