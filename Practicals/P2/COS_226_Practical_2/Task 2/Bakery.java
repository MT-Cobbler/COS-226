import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name:				Matthew Schoeman
// Student Number:		u17029377

public class Bakery implements Lock
{
	private int n;
	private Boolean[] flag;
	private int[] label;
	
	public Bakery(int n){
		this.n = n;
		label = new int[n];
		flag = new Boolean[n];
		for(int i = 0; i < n; i++){
			flag[i] = false;
			label[i] = 0;
		}
	}
	
	@Override
	public void lock() {
		int me = Integer.parseInt(Thread.currentThread().getName().substring(7)) ;
		flag[me] = true;
		label[me] = findMax();
		for(int i = 0 ; i < n; i++){
			while( (i != me) && flag[i] && ((label[i] < label[me]) || ((label[i] == label[me]) &&  i < me)) ){
				System.out.print("");
			}
		}
	}

	private int findMax(){
		int largest = 0;
		for(int i = 0 ; i < label.length; i++){
			if(label[i] > largest){
				largest = label[i];
			}
		}
		return (largest + 1);
	}

	@Override
	public void unlock() {
		int me = Integer.parseInt(Thread.currentThread().getName().substring(7)) ;
		flag[me] = false;
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
