// Name: 				Matthew Schoeman
// Student Number:		u17029377
public class Queue extends Thread
{
	Store store;
	private Thread t;
	static int count = 0;
	
	public Queue(Store s){
		store = s;
		count += 1;
		t = new Thread(this, String.valueOf(count));
	}

	@Override
	public void run()
	{
		store.enterStore();
	}
}
