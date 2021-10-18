import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.List;

// Name:				Matthew Schoeman
// Student Number:		u17029377

public class Bakery implements Lock
{
    private int n;
    private List<Boolean> flags;
    private List<Integer> labels;
    private int activeDuration, idleDuration;
    private final int maxActiveDuration = 500;
    private final int minActiveDuration = 100;

    public Bakery(){
        this.n = n;
        labels = new ArrayList<>();
        flags = new ArrayList<>();
        activeDuration = 0;
        idleDuration = 0;

    }

    public void addE(){
        flags.add(false);
        labels.add(0);
    }
    void incrementActiveDuration(int duration ){
        activeDuration += duration;
    }

    @Override
    public void lock() {
//        System.out.println(Thread.currentThread().getName());
        int me = Integer.parseInt(Thread.currentThread().getName().substring(7));
        flags.set(me, true);
        labels.set(me, findMax());
        for(int i = 0 ; i < flags.size(); i++){
            // If on a break, don't acquire lock
            while((i != me) && flags.get(i) && ((labels.get(i) < labels.get(me)) || ((labels.get(i) == labels.get(me)) &&  i < me)) ){
//                System.out.print(" hello " + me);
            }
        }
    }

    private int findMax(){
        int largest = 0;
        for(int i = 0 ; i < labels.size(); i++){
            if(labels.get(i) > largest){
                largest = labels.get(i);
            }
        }
        return (largest + 1);
    }

    @Override
    public void unlock() {
        int me = Integer.parseInt(Thread.currentThread().getName().substring(7));
        flags.set(me, false);
    }
    public void lockInterruptibly() throws InterruptedException
    {
        throw new UnsupportedOperationException();
    }

    public boolean tryLock()
    {
        throw new UnsupportedOperationException();
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
    {
        throw new UnsupportedOperationException();
    }

    public Condition newCondition()
    {
        throw new UnsupportedOperationException();
    }


}
