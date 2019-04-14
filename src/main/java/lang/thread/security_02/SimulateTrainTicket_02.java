package lang.thread.security_02;

/**
 * Created by Calvin on 2019/4/13
 * 标题: 解决线程安全的问题 - 使用内置锁Synchroized
 * 模拟抢火车票
 */
public class SimulateTrainTicket_02 implements Runnable{

    /** 火车票有100 张 (共享资源)**/
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

    /** 使用 Synchronized 在 方法上，此时充当锁的对象为调用方法的对象 **/
    public synchronized void saleTicket(){
        if(trainTicketCount > 0){
            System.out.println(Thread.currentThread().getName() + ", 出售第: " + (100 - trainTicketCount + 1) + " 票");
            trainTicketCount --;
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
