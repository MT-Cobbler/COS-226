// Practical 1 - Task 3
// Matthew Schoeman 
// u17029377

public class Counter extends PetersonLock{
    // variable
    PetersonLock lock = new PetersonLock();
    private int counter;

    public Counter(){
        counter = 0;
    }
    public int incrementCounter(){
        lock.lock();
        try{
            ++counter;
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return counter;
    }
}
