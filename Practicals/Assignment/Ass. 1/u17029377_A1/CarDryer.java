import java.util.Queue;

// Matthew Schoeman - u17029377

class CarDryer extends Thread {

	Queue<Car> washCars;
	Queue<Car> dryCars;
	Bakery lock;
	CarDryer(Queue<Car> washCars, Queue<Car> dryCars, Bakery lock){
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
			System.out.println(Thread.currentThread().getName() + " is ready to dry car.");
			lock.lock();
			while(dryCars.isEmpty() && !washCars.isEmpty()) {
				System.out.print("");
			}
			car = dryCars.peek();
			if(car == null && washCars.isEmpty()) {
				lock.unlock();
				break;
			}
			car = dryCars.peek();
			long dryTime = (long)(Math.random()*500 + 100);
			while(dryTime > 0) {
				if(car.dryTime > dryTime) {
					car.dryTime -= dryTime;
					System.out.println(Thread.currentThread().getName() + " dried " + car.name + " for " + dryTime + " ms. Remaining Time: " + car.dryTime);
					long breakTime = (long)(Math.random()*50 + 50);
					try {
						System.out.println(Thread.currentThread().getName() + " is taking a break");
						Thread.sleep(breakTime);
						lock.unlock();
					} catch (Exception e) {e.printStackTrace();}
					dryTime = -1;
				} else {
					dryCars.remove();
					System.out.println(Thread.currentThread().getName() + " finished drying " + car.name);
					if(car.dryTime < dryTime) {
						dryTime -= car.dryTime;
						car = washCars.peek();
						if(car == null) {
							lock.unlock();
							done = true;
							break;
						}
					} else {
						long breakTime = (long)(Math.min(Math.random()*50, 50));
						try {
							System.out.println(Thread.currentThread().getName() + " is taking a break.");
							Thread.sleep(breakTime);
							lock.unlock();
						} catch (Exception e) {e.printStackTrace();}
						dryTime = -1;						
					}
				}
			}
		}
	}
}
