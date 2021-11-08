// Matthew Schoeman u17029377
public interface Consensus<T>
{
	void decide();
	void propose(T value);
}
