import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Matthew Schoeman - u17029377

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
		Bakery washLock = new Bakery();
		Bakery dryLock = new Bakery();
		for (int i = 0; i < 4; i++) {
			employees.add(new CarWasher(carWash.washCars, carWash.dryCars, washLock));
		}
		for (int i = 0; i < 7; i++) {
			employees.add(new CarDryer(carWash.washCars, carWash.dryCars, dryLock));
		}
		employees.forEach(employee -> employee.start());
	}
}

