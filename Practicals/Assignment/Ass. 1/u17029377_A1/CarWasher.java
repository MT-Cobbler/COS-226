import java.util.Queue;

// Matthew Schoeman - u17029377

class CarWasher extends Thread {

	Queue<Car> washCars;
	Queue<Car> dryCars;
	Bakery lock;
	CarWasher(Queue<Car> washCars, Queue<Car> dryCars, Bakery lock){
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
			System.out.println(Thread.currentThread().getName() + " is ready to wash a car.");
			lock.lock();
			car = washCars.peek();
			if(car == null) {
				lock.unlock();
				break;
			}
			long washTime = (long)(Math.random()* ((500 - 100)+ 100));
			while(washTime > 0) {
				if(car.washTime > washTime) {
					car.washTime -= washTime;
					System.out.println(Thread.currentThread().getName() + " washed " + car.name + " for " + washTime + " ms. Remaining Time: " +car.washTime);
					long breakTime = (long)(Math.random()* ((100 - 50) + 50));
					try {
						System.out.println(Thread.currentThread().getName() + " is taking a break.");
						Thread.sleep(breakTime);
						lock.unlock();
					} catch (Exception e) {}
					washTime = -1;
				} else {
					dryCars.add(washCars.remove());
					System.out.println(Thread.currentThread().getName() + " finished washing " + car.name);
					if(car.washTime < washTime) {
						washTime -= car.washTime;
						car = washCars.peek();
						if(car == null) {
							lock.unlock();
							done = true;
							break;
						}
					} else {
						long breakTime = (long)(Math.random()* ((100 - 50) + 50));
						try {
							Thread.sleep(breakTime);
							System.out.println(Thread.currentThread().getName() + " is taking a break.");
							lock.unlock();
						} catch (Exception e) {}
						washTime = -1;
					}
				}
			}
		}
	}
}