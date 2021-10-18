import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name:				Matthew Schoeman
// Student Number:		u17029377

public class Bakery implements Lock
{

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

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		System.out.println("Wassup");
		
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		System.out.println("Wassup");
	}

}
