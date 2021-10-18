import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;


public class CarWash
{
	private volatile Queue<Car> washCars = new LinkedList<>(), dryCars = new LinkedList<>();

	public CarWash(){
		washCars.add(new Car('s', "Panda"));
		washCars.add(new Car('m', "Cerato"));
		washCars.add(new Car('s', "Swift"));
		washCars.add(new Car('l', "Defender"));
		washCars.add(new Car('m', "A3"));
		washCars.add(new Car('l', "Ranger"));
		washCars.add(new Car('s', "GTI"));
	}

	public static void main(String[] args) {
		CarWash carWash = new CarWash();
		List<Thread> employees = new ArrayList<>();
		EmployeeLock washLock = new EmployeeLock();
		EmployeeLock dryLock = new EmployeeLock();
		for (int i = 0; i < 4; i++) {
			employees.add(new CarWasher(carWash.washCars, carWash.dryCars, washLock));
		}
		for (int i = 0; i < 7; i++) {
			employees.add(new CarDryer(carWash.washCars, carWash.dryCars, dryLock));
		}
		employees.forEach(employee -> employee.start());
	}


}

class CarWasher extends Thread {

	Queue<Car> washCars;
	Queue<Car> dryCars;
	EmployeeLock lock;
	CarWasher(Queue<Car> washCars, Queue<Car> dryCars, EmployeeLock lock){
		this.washCars = washCars;
		this.dryCars = dryCars;
		this.lock = lock;
		this.lock.addEmployee();
	}

	@Override
	public void run() {
		Car car;
		boolean done = false;
		while(!done) {
			System.out.println(Thread.currentThread().getName() + " is ready to wash");
			lock.lock();
			// System.out.println(Thread.currentThread().getName() + " has the lock");
			car = washCars.peek();
			if(car == null) { // queue is empty
				// System.out.println(Thread.currentThread().getName() + " Done release the lock - queue is empty.");
				lock.unlock();
				break; // exit the loop
			}
			// loop here for the time the employee is active for.
			long washTime = (long)(Math.random()*500 + 100);
			while(washTime > 0) {
				if(car.washTime > washTime) {
					car.washTime -= washTime;
					System.out.println(Thread.currentThread().getName() + " is washing " + car.name
					+ " for " + washTime + ". Remaining Time: " +car.washTime + "ms.");
					long breakTime = (long)(Math.random()*50 + 50);
					try {
						lock.unlock();
						System.out.println(Thread.currentThread().getName() + " is taking a break for " + breakTime);
						Thread.sleep(breakTime);
					} catch (Exception e) {}
					washTime = -1;
				} else {
					dryCars.add(washCars.remove()); // send to drying queue.
					System.out.println(Thread.currentThread().getName() + " finished washing " + car.name);
					if(car.washTime < washTime) {
						washTime -= car.washTime;
						car = washCars.peek();
						if(car == null) { // queue is empty
							// System.out.println(Thread.currentThread().getName() + " SSSSSSSS release the lock - queue is empty.");
							lock.unlock();
							done = true;
							break; // exit the inner loop
						}
					} else {
						long breakTime = (long)(Math.min(Math.random()*50, 50));
						try {
							lock.unlock();
							System.out.println(Thread.currentThread().getName() + " is taking a break");
							Thread.sleep(breakTime);
						} catch (Exception e) {}
						washTime = -1;						
					}
				}
			}
		}
	}
}


class CarDryer extends Thread {

	Queue<Car> washCars;
	Queue<Car> dryCars;
	EmployeeLock lock;
	CarDryer(Queue<Car> washCars, Queue<Car> dryCars, EmployeeLock lock){
		this.washCars = washCars;
		this.dryCars = dryCars;
		this.lock = lock;
		this.lock.addEmployee();
	}

	@Override
	public void run() {
		Car car;
		boolean done = false;
		while(!done) {
			System.out.println(Thread.currentThread().getName() + " is ready to dry");
			lock.lock();
			// System.out.println(Thread.currentThread().getName() + " has the lock");

			// wait till an item is added to the queue
			while(dryCars.isEmpty() && !washCars.isEmpty()) {
				System.out.print("");
			}
			car = dryCars.peek();
			if(car == null && washCars.isEmpty()) { // queue is empty, check washing queue
				// System.out.println(Thread.currentThread().getName() + " done release the lock - both are queue is empty.");
				lock.unlock();
				break; // exit the loop
			}
			car = dryCars.peek();
			// loop here for the time the employee is active for.
			long dryTime = (long)(Math.random()*500 + 100);
			while(dryTime > 0) {
				if(car.dryTime > dryTime) {
					car.dryTime -= dryTime;
					System.out.println(Thread.currentThread().getName() + " is drying " + car.name
					+ " for " + dryTime + ". Remaining Time: " +car.dryTime + "ms.");
					long breakTime = (long)(Math.random()*50 + 50);
					try {
						lock.unlock();
						System.out.println(Thread.currentThread().getName() + " is taking a break for " + breakTime);
						Thread.sleep(breakTime);
					} catch (Exception e) {}
					dryTime = -1;
				} else {
					dryCars.remove(); // send to drying queue.
					System.out.println(Thread.currentThread().getName() + " finished drying " + car.name);
					if(car.dryTime < dryTime) {
						dryTime -= car.dryTime;
						car = washCars.peek();
						if(car == null) { // queue is empty
							// System.out.println(Thread.currentThread().getName() + " SSSSSSSS release the lock - queue is empty.");
							lock.unlock();
							done = true;
							break; // exit the inner loop
						}
					} else {
						long breakTime = (long)(Math.min(Math.random()*50, 50));
						try {
							lock.unlock();
							System.out.println(Thread.currentThread().getName() + " is taking a break");
							Thread.sleep(breakTime);
						} catch (Exception e) {}
						dryTime = -1;						
					}
				}
			}
		}
	}
}


// Custom lock
class EmployeeLock implements Lock
{
    private Map<Integer, Boolean> flags;
    private Map<Integer, Integer> labels;
	private static int threadCount = 0;
    public EmployeeLock(){
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
    //    System.out.println(Thread.currentThread().getName() + "try to get the lock");
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

