public class Main {
    public static void main(String[] args) {
        CarWash station = new CarWash();
//        station.start();
        ThreadMain[] t = new ThreadMain[7];
        for(int i = 0 ; i < 7; i++){
            t[i] = new ThreadMain(station.getHead());
        }
        for(int i = 0 ; i < 7; i++){
            t[i].start();
        }
//        for(int i = 0; i < 7; i++){
//            // get each car
//            t = new ThreadMain(station.getHead());
//        }
//        t.start();
    }
}
