import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Practical 1 - Task 3
// Matthew Schoeman 
// u17029377
public class PetersonLock implements Lock{
    private String i, j; // needs to locally 
    private Boolean[] flag = new Boolean[2];
    private String v; // had to be volatile
    public PetersonLock(){}

    public void lock(){
        int x, y;

        i = Thread.currentThread().getName();
        x = Integer.parseInt(Thread.currentThread().getName().substring(7));
        j = i +"a";
        flag[0] = true; //desires lock
        flag[1] = !flag[0];
        v = i; // give j a chance for lock
        while(flag[1] && v == i){} // won't exxecute so i will acquire
    }
    public void unlock(){
        flag[0] = false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean tryLock() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Condition newCondition() {
        // TODO Auto-generated method stub
        return null;
    }
}
