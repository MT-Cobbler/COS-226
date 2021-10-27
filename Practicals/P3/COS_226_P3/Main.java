public class Main {
    public static void main(String[] args) {
        ConsensusProtocol<Integer> a = new RMWConsensus<>(2);
        ConsensusThread obj = new ConsensusThread(a);
        Thread t1 = new Thread(obj);
        Thread t2 = new Thread(obj);
        t1.start();
        t2.start();
    }
}
