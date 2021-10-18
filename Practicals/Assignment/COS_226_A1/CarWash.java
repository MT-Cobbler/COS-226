// Matthew Schoeman u17029377

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class CarWash
{
	private volatile Queue<Car> washCars = new LinkedList<>(), dryCars = new LinkedList<>();
	Car c;
	static int num = 0;
	public CarWash() {
		washCars.add(new Car('s', "Panda"));
		washCars.add(new Car('m', "Cerato"));
		washCars.add(new Car('s', "Swift"));
		washCars.add(new Car('l', "Defender"));
		washCars.add(new Car('m', "A3"));
		washCars.add(new Car('l', "Ranger"));
		washCars.add(new Car('s', "GTI"));
	}

	public Car getHead(){
		Car temp = washCars.remove();
		washCars.add(temp);
		return temp;
	}

	public void washCars(){
		Lock washLock = new Bakery(7);
		System.out.println(Thread.currentThread().getName() + " is ready to wash car.");
		washLock.lock();
		Car temp = washCars.remove();
		while(temp.washTime > 0) {
			try {
				double v = Math.random() * (500 - 100) + 100;
				long vv = (long) v;
				Thread.sleep(vv);
				temp.washTime -= vv;
				if (temp.washTime > 0) {
					System.out.println(Thread.currentThread().getName() + " washed " + temp.name + " for " + vv + " ms. Time remaining: " + temp.washTime);
				} else {
					System.out.println(Thread.currentThread().getName() + " finished washing " + temp.name);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep((long) (Math.random() * (100 - 50) + 100));
					System.out.println(Thread.currentThread().getName() + " is taking a break");
					washLock.unlock();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
