public class main {
    public static void main(String[] args) {
        String name = new String();
        name = "thread-1";
        ThreadDemo t1 = new ThreadDemo(name);
        t1.start();
    }
}
