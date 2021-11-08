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
        int i = Integer.parseInt(Thread.currentThread().getName().substring(7));
        if(choice[0].equals(0)) {
            choice[0] = proposed[i];
            System.out.println("--------------------------------------------------");
            System.out.println("Choices: R" + proposed[0] + " and R" + proposed[1]);
            System.out.println("Decided: R" + choice[0]);
            proposed[0] = choice[0]; proposed[1] = choice[0];
            System.out.println("Updated choice: R" + proposed[0] + " and R" + proposed[1]);
            System.out.println("--------------------------------------------------");
        }
    }
}
