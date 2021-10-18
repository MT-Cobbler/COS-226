// Practical 1 - Task 2
// Matthew Schoeman 
// u17029377
import java.util.concurrent.locks.ReentrantLock;
public class Counter extends ReentrantLock{
    // variable
    ReentrantLock lock = new ReentrantLock();
    private int counter;

    public Counter(){
        counter = 0;
    }
    public int incrementCounter(){
        lock.lock();
        int temp = 0;
        try{
            temp = ++counter;
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
        return temp;
    }
}


