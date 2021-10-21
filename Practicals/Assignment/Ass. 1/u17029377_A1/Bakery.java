import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Matthew Schoeman - u17029377

class Bakery implements Lock
{
    private Map<Integer, Boolean> flags;
    private Map<Integer, Integer> labels;
    private static int threadCount = 0;
    public Bakery(){
        labels = new HashMap<>();
        flags = new HashMap<>();
    }

    public void addEmployee() {
        labels.put(threadCount, 0);
        flags.put(threadCount, false);
        threadCount += 1;
    }

    @Override
    public void lock() {
        int me = Integer.parseInt(Thread.currentThread().getName().substring(7));
        flags.put(me, true);
        labels.put(me, findMax());
        Iterator<Integer> iterator = labels.keySet().iterator();
        while(iterator.hasNext()) {
            int i = iterator.next();
            while( (i != me) && flags.get(i) && (labels.get(i) < labels.get(me)) || ((labels.get(i) == labels.get(me) &&  i < me)) ){
                System.out.print("");
            }
        }
    }

    private int findMax(){
        int largest = 0;
        Iterator<Integer> iterator = labels.keySet().iterator();
        while(iterator.hasNext()) {
            int i = iterator.next();
            if(labels.get(i) > largest){
                largest = labels.get(i);
            }
        }
        return (largest + 1);
    }

    @Override
    public void unlock() {
        int me = Integer.parseInt(Thread.currentThread().getName().substring(7));
        flags.put(me, false);
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