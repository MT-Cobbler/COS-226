import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name: 				Matthew Schoeman
// Student Number:		u17029377

public class Filter implements Lock
{

	int[] level; 
	int[] victim; 
	private int n;
	public Filter(int n){
		this.n = n;
		level = new int[n]; victim = new int[n];
		for(int i = 0 ; i < n; i++){
			level[i] = 0;
		}
	}

	@Override
	public void lock() {
		int me = Integer.parseInt(Thread.currentThread().getName().substring(7));
		for(int i = 1 ; i < n ; i++){
			level[me] = i;
			victim[i] = me;
			for(int j = 0 ; j < n; j++){
				while((j != me) && (level[j] >= i && victim[i] == me)){
				}
			}
		}
	}

	@Override
	public void unlock() {
		int me = Integer.parseInt(Thread.currentThread().getName().substring(7));
		level[me] = 0;
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
}
