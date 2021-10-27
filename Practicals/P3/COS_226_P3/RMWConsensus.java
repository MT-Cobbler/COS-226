// Matthew Schoeman u17029377
public class RMWConsensus<T> extends ConsensusProtocol<T>{
    volatile Object[] choice;   // stores the decided value
    public RMWConsensus(int threadCount) {
        super(threadCount);
        //TODO Auto-generated constructor stub
        choice = new Object[1];
        choice[0] = 0;
    }

    @Override
    public void decide() {
        int i = Integer.parseInt(Thread.currentThread().getName().substring(7)) - 1;
        if(choice[0].equals(0)) {
            choice[0] = proposed[i];
            System.out.println("The two choices are: " + proposed[0] + " and " + proposed[1]);
            System.out.println("The final choice is: " + choice[0]);
            proposed[0] = choice[0]; proposed[1] = choice[0];
            System.out.println("The two choices are now: " + proposed[0] + " and " + proposed[1]);
        }
    }
}
