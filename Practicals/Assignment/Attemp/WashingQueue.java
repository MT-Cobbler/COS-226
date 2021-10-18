import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class WashingQueue extends Thread{

    Queue<Car> washQueue;
    Queue<Car> dryQueue;
    Bakery l;
    Thread t;
    WashingQueue(Queue<Car> wash, Queue<Car> dry, Bakery l){
        washQueue = wash;
        dryQueue = dry;
        this.l = l;
        t = new Thread(this, String.valueOf(CarWash.threadNumber));
    }


    public void addEmployee(){
//        Thread t = new Thread(this, String.valueOf(CarWash.threadNumber));

//        t = new Thread(this, String.valueOf(CarWash.threadNumber));
        CarWash.threadNumber += 1;
        l.addE();
//        t.start();
        System.out.println(t.getName() + " ready to wash a car.");
    }

    @Override
    public void run() {
//        t.start();
        while(true){
            if(!washQueue.isEmpty()) {
                // dequeue car
                l.lock();
                Car car = washQueue.peek();
                long timeRemaining = car.washTime;

                try{
                    while(timeRemaining> 0) {
//                        l.lock();
                        long washerActive = (long)(Math.random() * (500 - 100)) + 100;
                        long washFor = Math.min(washerActive, timeRemaining);
                        Thread.sleep(washFor);
                        timeRemaining -= washFor;
                        //Thread-2 washed Defender for 167 ms. Time remaining: 1081
                        if (timeRemaining > 0) {
                            System.out.println(Thread.currentThread().getName() + " washed " + car.name + " for " + washFor + " ms. Time remaining: " + timeRemaining + " ms.");
                            System.out.println(Thread.currentThread().getName() + " is taking a break");
                            car.washTime = timeRemaining;
//                            l.unlock();
                            try {
                                Thread.sleep((long) (Math.random() * (100 - 50) + 50));
//                            System.out.println(Thread.currentThread().getName() + " is taking a break");
//                          l.unlock();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            try {
                                Thread.sleep((long) (Math.random() * (100 - 50) + 50));
                                System.out.println(Thread.currentThread().getName() + " is taking a break");
//                              l.unlock();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + " finisihed wasing " + car.name);
                            Car temp = washQueue.remove();
                            dryQueue.add(car);
                            // add car to dry queue here
                        }
                        l.unlock();
                        // Assume employee takes a break after washing a car
                    }
                } catch (Exception e) {
                    //
                }finally{
                    // Thread-2 washed Defender for 167 ms. Time remaining: 1081

                    // sleep here when taking a break then release the lock.

                }


            }
        }

    }
}
