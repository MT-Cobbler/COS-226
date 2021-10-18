import java.util.concurrent.locks.Lock;

// Name: 				Matthew Schoeman
// Student Number:		u17029377

public class Store
{
	Lock l;
	public void enterStore(){
		l = new Filter(4);
		l.lock();
		try{
			System.out.println(Thread.currentThread().getName() + " Person: "  + " is trying to get inside." );
			Thread.sleep( (int)((Math.random() * (1000 - 200)) + 200) );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			l.unlock();
		}
		System.out.println("Hi");
	}
}
