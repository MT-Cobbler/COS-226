// Matthew Schoeman u17029377
public class ConsensusThread extends Thread
{
	private Consensus<Integer> consensus;
	final int min = 100;
	final int max = 200;
	int amount;
	int breakTime;
	static int count = 0;
	Consensus<Integer> temp;
	ConsensusThread(Consensus<Integer> consensusObject)
	{
		consensus = consensusObject;
		temp = consensusObject;
	}

	public void run()
	{
		amount = (int) ((Math.random() * (200 - 100)) + 100);
		breakTime = (int) ((Math.random() * (100 - 50)) + 50);
		consensus.propose(amount);
		try {
			Thread.sleep(breakTime);
			consensus.decide();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
