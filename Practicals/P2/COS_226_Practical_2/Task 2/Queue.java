// Name:				Matthew Schoeman
// Student Number:		u17029377

public class Queue extends Thread
{
	Store store;
	Thread t;
	static int num = 0;
	
	public Queue(Store s){
		store = s;
		t = new Thread(this, String.valueOf(num));
		num += 1;
	}

	@Override
	public void run()
	{	
		for(int i = 0; i < 5; i++){
			store.enterStore();
		}
	}
}
