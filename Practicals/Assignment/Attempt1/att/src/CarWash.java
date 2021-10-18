import java.util.LinkedList;
import java.util.Queue;


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
}
