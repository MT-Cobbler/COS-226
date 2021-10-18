// Name: 				Matthew Schoeman
// Student Number:		u17029377

public class Main {

    public static void main(String[] args) {
	    Queue[] queues = new Queue[4];

        Store store = new Store();
        for(int i = 0; i < 4; i++)
            queues[i] = new Queue(store);

        for(Queue queue : queues)
            queue.start();
    }
}
