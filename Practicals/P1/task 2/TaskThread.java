// Practical 1 - Task 2
// Matthew Schoeman 
// u17029377
public class TaskThread extends Thread{

    private Counter counter; // holds the counter object
    private Thread t0;       // holds the thread objedct
    // constructor overload
    public TaskThread(Counter obj){
        counter = obj;
        t0 = new Thread(this);
    }
    // run method must be overridden
    public void run(){
        for (int i = 1; i <= 7; i++) {
            System.out.println(t0.getName() + " Counter: " + counter.incrementCounter());
        }
    }
    public static void main(String[] args) {
        Counter c = new Counter();
        Thread t1 = new TaskThread(c);
        Thread t2 = new TaskThread(c);

        t1.start(); // run thread 1
    
        t2.start(); // run thread 2
        try{
            // waits for threads to finishes
            t1.join();
            t2.join();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }   
}