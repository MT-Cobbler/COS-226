import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name:				Matthew Schoeman
// Student Number:		u17029377

public class Filter implements Lock
{
	private int[] level;
	private volatile int[] victim;
	private int n;
	public Filter(int n){
		this.n = n;
		level = new int[n];
		victim = new int[n];
		for(int i = 0 ; i < n ; i++){
			level[i] = 0;
		}
	}
	@Override
	public void lock() {
		int me = Integer.parseInt(Thread.currentThread().getName().substring(7)) ;
		
		for(int i = 1 ; i < n ; i++){
			level[me] = i;
			victim[i] = me;
			for(int j = 0 ; j < n; j++){
				while((j != me) && (level[j] >= i && victim[i] == me)){}
			}
		}
	}

	@Override
	public void unlock() {
		int me = Integer.parseInt(Thread.currentThread().getName().substring(7));
		level[me] = 0;
		
	}
	public void lockInterruptibly() throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock()
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public Condition newCondition()
	{
		throw new UnsupportedOperationException();
	}


}
