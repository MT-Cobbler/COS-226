import java.util.concurrent.atomic.AtomicInteger;

public class CASConsensus<T> extends ConsensusProtocol<T>{

    private final int FIRST = -1;
    private AtomicInteger r = new AtomicInteger(FIRST);
    volatile Object[] choice;   // stores the decided value
    public CASConsensus(int threadCount) {
        super(threadCount);
        //TODO Auto-generated constructor stub
        choice = new Object[1];
        choice[0] = 0;
    }
    @Override
    public void decide() {
        int i = Integer.parseInt(Thread.currentThread().getName().substring(7));
        if(r.compareAndSet(FIRST, i)){
            System.out.println("-------------------------------------------");
            System.out.print("Choices: ");
            for(int j = 0; j < proposed.length; j++){
                System.out.print("R" + proposed[j] + " ");
            }
            System.out.println();
            System.out.println("Decided: R" + proposed[i]);
            for(int j = 0 ; j < proposed.length; j++){
                proposed[j] = proposed[i];
            }
            System.out.print("Updated choices: ");
            for(int j = 0; j < proposed.length; j++){
                System.out.print("R" + proposed[j] + " ");
            }
            System.out.println();
            System.out.println("-------------------------------------------");

        }
    }
}
