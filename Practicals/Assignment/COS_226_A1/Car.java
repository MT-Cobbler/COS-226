// Matthew Schoeman u17029377

import java.util.concurrent.locks.Lock;

public class Car extends Thread
{
	public volatile long washTime, dryTime;
	public String name;
	Thread t;
	public Car(char c, String n){

		name = n;
		switch(c){
			case 's': 	washTime = 500;
						dryTime = 1000;
						break;
			case 'm':	washTime = 1000;
						dryTime = 1500;
						break;
			case 'l':	washTime = 1500;
						dryTime = 2000;
		}
	}

	public void wash() {

		Lock washLock = new Bakery(7);
		System.out.println(Thread.currentThread().getName() + " is ready to wash car.");
		washLock.lock();
		while(washTime > 0) {
			try {
				double v = Math.random() * (500 - 100) + 100;
				long vv = (long) v;
				Thread.sleep(vv);
				washTime -= vv;
				if (washTime > 0) {
					System.out.println(Thread.currentThread().getName() + " washed " + name + " for " + vv + " ms. Time remaining: " + washTime + " ms");
				} else {
					System.out.println(Thread.currentThread().getName() + " finished washing " + name);
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
