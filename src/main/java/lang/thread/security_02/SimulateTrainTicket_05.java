package lang.thread.security_02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Calvin on 2019/4/13
 * 标题: 解决线程安全的问题 - 使用LOCK 锁
 * 模拟抢火车票
 */
public class SimulateTrainTicket_05 implements Runnable {

    /**
     * 火车票有100 张 (共享资源)
     **/
    private int trainTicketCount = 100;

    @Override
    public void run() {
        // 只要火车票大于0，继续售票
        while (trainTicketCount > 0){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 售票
            saleTicket();
        }
    }


    public void saleTicket() {
        // 创建锁
        Lock lock = new ReentrantLock();
        // 上锁
        lock.lock();
        try{
            //可能会出现线程安全的操作
            if (trainTicketCount > 0) {
                System.out.println(Thread.currentThread().getName() + ", 出售第: " + (100 - trainTicketCount + 1) + " 票");
                trainTicketCount--;
            }
        }finally {
            //一定在finally中释放锁
            //也不能把获取锁在try中进行，因为有可能在获取锁的时候抛出异常
            lock.unlock();
        }
    }


    public static void main(String[] args){
        SimulateTrainTicket_02 simulateTrainTicket_01 = new SimulateTrainTicket_02();
        Thread t1 = new Thread(simulateTrainTicket_01,"窗口①号");
        Thread t2 = new Thread(simulateTrainTicket_01, "窗口②号");
        t1.start();
        t2.start();
    }



}
