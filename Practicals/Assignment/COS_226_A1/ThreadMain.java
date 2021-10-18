public class ThreadMain extends Thread{

    private Car cWash;
    Thread t;
    static int num = 0;
    ThreadMain(){
        cWash = null;
    }
    ThreadMain(Car c){
        cWash = c;
        t = new Thread(this);
        num += 1;
    }

    public void run(){
        cWash.wash();
    }
//    public void run(){
//        for(int i = 0 ; i < 7 ; i++)
//            cWash.washCars();
//    }
}
