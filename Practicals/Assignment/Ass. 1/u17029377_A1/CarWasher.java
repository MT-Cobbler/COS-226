import java.util.Queue;

// Matthew Schoeman - u17029377

class CarWasher extends Thread {

	Queue<Car> washCars;
	Queue<Car> dryCars;
	Bakery lock;
	CarWasher(Queue<Car> washCars, Queue<Car> dryCars, Bakery lock){
		this.washCars = washCars;	// assigns the washQueue from CarWash
		this.dryCars = dryCars;		// assigns the dryQueue from Car Wash
		this.lock = lock;			// assigns the carWasher lock
		this.lock.addEmployee();	// adds an employee/thread to the bakery lock
	}

	@Override
	public void run() {
		Car car;				// for a car object 
		boolean done = false; 	// if a car is done being washed
		while(!done) {			// while isn't finished being washed
			System.out.println(Thread.currentThread().getName() + " is ready to wash a car.");
			lock.lock();		// lock - for synchronization 
			car = washCars.peek();	// hold the first car in the queue
			if(car == null) {		// no more cars
				lock.unlock();
				break;
			}
			long washTime = (long)(Math.random()* ((500 - 100)+ 100)); 	// create a random washing time for the car
			while(washTime > 0) {										// while the washTime for the car is greater than 0
				if(car.washTime > washTime) {							// if the cars total wash time is longer than the washtime for the thread, meaning the thread won't finish washing the car
					car.washTime -= washTime;							// reduce the cars washtime
					System.out.println(Thread.currentThread().getName() + " washed " + car.name + " for " + washTime + " ms. Remaining Time: " + car.washTime);
					long breakTime = (long)(Math.random()* ((100 - 50) + 50));	// determine the threads breaktime
					try {
						System.out.println(Thread.currentThread().getName() + " is taking a break.");
						Thread.sleep(breakTime);						// thread is taking break
						lock.unlock();									// release the lock for another washer/thread
					} catch (Exception e) {}
					washTime = -1;
				} else {												// thread will finish washing the car with time remaining on their shift
					dryCars.add(washCars.remove());						// remove this car and add it to the dryQueue
					System.out.println(Thread.currentThread().getName() + " finished washing " + car.name);
					if(car.washTime < washTime) {						// if the thread has time remaining when finishing the car
						washTime -= car.washTime;						// reduce the threads washing time
						car = washCars.peek();							// look at the next car in wash queue
						if(car == null) {								// no more cars left, release the lock and exit loop
							lock.unlock();
							done = true;
							break;
						}
					} else {											// thread will take their break now
						long breakTime = (long)(Math.random()* ((100 - 50) + 50));
						try {
							Thread.sleep(breakTime);
							System.out.println(Thread.currentThread().getName() + " is taking a break.");
							lock.unlock();								// release the lock
						} catch (Exception e) {}
						washTime = -1;
					}
				}
			}
		}
	}
}