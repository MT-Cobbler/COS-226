import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Matthew Schoeman - u17029377

public class CarWash
{
	private volatile Queue<Car> washCars = new LinkedList<>();
	private volatile Queue<Car> dryCars = new LinkedList<>();

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
		CarWash carWash = new CarWash();				// ininitalize and run the Constructor above
		List<Thread> employees = new ArrayList<>();		// list of threads - list for dynamic purposes
		Bakery washLock = new Bakery();					// Lock for washing - synchronize the wash queue
		Bakery dryLock = new Bakery();					// Lock for drying - synchronize the dry queueu
		for (int i = 0; i < 4; i++) {
			employees.add(new CarWasher(carWash.washCars, carWash.dryCars, washLock));		// add new washing object for washing thread
		}
		for (int i = 0; i < 7; i++) {
			employees.add(new CarDryer(carWash.washCars, carWash.dryCars, dryLock));		// add new drying object for drying threads
		}
		employees.forEach(Thread::start);				// start all the threads
	}
}

