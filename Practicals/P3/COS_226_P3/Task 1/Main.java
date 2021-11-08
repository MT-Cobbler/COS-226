// Matthew Schoeman u17029377
public class Main {
    public static void main(String[] args) {
        ConsensusProtocol<Integer> a;
        ConsensusThread obj;
        Thread t1;
        Thread t2;
        for (int i = 0; i < 5; i++) {
            a = new RMWConsensus<Integer>(2);
            obj = new ConsensusThread(a);
            t1 = new Thread(obj, "Thread-0");
            t2= new Thread(obj, "Thread-1");
            t1.start();
            t2.start();
            try{
                t1.join();
                t2.join();
            }catch (Exception e){

            }
        }
    }
}
