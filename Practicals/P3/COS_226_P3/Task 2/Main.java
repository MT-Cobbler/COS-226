// Matthew Schoeman u17029377
public class Main {
    public static void main(String[] args) {
        ConsensusProtocol<Integer> a;
        ConsensusThread obj;
        Thread t1;
        Thread t2;
        Thread t3;
        Thread t4;
        Thread t5;
        for (int i = 0; i < 5; i++) {
            a = new CASConsensus<>(5);
            obj = new ConsensusThread(a);
            t1 = new Thread(obj, "Thread-0");
            t2= new Thread(obj, "Thread-1");
            t3= new Thread(obj, "Thread-2");
            t4= new Thread(obj, "Thread-3");
            t5= new Thread(obj, "Thread-4");
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            try{
                t1.join();
                t2.join();
                t3.join();
                t4.join();
                t5.join();
            }catch (Exception e){

            }
        }
    }
}
