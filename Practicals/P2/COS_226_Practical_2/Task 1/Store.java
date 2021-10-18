import java.util.concurrent.locks.Lock;

// Name:				Matthew Schoeman
// Student Number:		u17029377

public class Store
{	
	private int num = 0;
	public int sum()  
	{  
		num += 1;
		return num; 
	} 
	Lock l = new Filter(20);
	public void enterStore(){
		// l = new Filter(20);
		num += 1;
		// Thread.currentThread().setPriority(sum());
		System.out.println(Thread.currentThread().getName() + " Person " + (Integer.parseInt(Thread.currentThread().getName().substring(7)) + 1) + " is trying to get inside." );
		l.lock();
		try{
			System.out.println(Thread.currentThread().getName() + " Person "  +  (Integer.parseInt(Thread.currentThread().getName().substring(7)) + 1) + " has entered the store.");
			Thread.sleep( (int)((Math.random() * (1000 - 200)) + 200) );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(Thread.currentThread().getName() + " Person " +  (Integer.parseInt(Thread.currentThread().getName().substring(7)) + 1) + " has left the store.");
			l.unlock();
		}
	}
}
