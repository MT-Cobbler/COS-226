public class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;

    public ThreadDemo(String s) {
        threadName = s;
        System.out.println("Creating thread: " + this.threadName );        
    }
    public void run(){
        System.out.println("Running: " + this.threadName);
        try {
            for(int i = 4 ; i > 0; i--){
                System.out.println("Thread: " + this.threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            //TODO: handle exception
            System.out.println("Thread: " + this.threadName + ", iterrupted!");
        }
    }
    public void start(){
        System.out.println("Starting " + this.threadName);
        if(t == null){
            t = new Thread(this, this.threadName);
            t.start();
        }
    }
}
