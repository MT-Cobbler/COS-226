public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        // Threads must be defined in Car
        // CarWash must perform the operations: washing and drying
        // issues:
        //      - Create thread for the Car object
        //      - Link run() to CarWash
        // When a car has finished then it must be added to the drying queue where another thread can start to dry it
        // run until all the cars have been dried dryQueue.size() = 0
    }
}
