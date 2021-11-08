// Matthew Schoeman u17029377

public abstract class ConsensusProtocol<T> implements Consensus<T>
{
	public volatile Object[] proposed;
	public ConsensusProtocol(int threadCount)	{
		proposed = new Object[threadCount];
	}

	public void propose(T value){
		int i = Integer.parseInt(Thread.currentThread().getName().substring(7));
		proposed[i] = value;
	}
	abstract public void decide();
}
