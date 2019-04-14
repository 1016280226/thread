package lang.thread.security_02;

/**
 * Created by Calvin on 2019/4/13
 * 标题: 解决线程安全的问题 - 使用内置锁Synchroized
 * 模拟抢火车票
 */
public class SimulateTrainTicket_03 implements Runnable{

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

    /** 同步synchroized代码块 **/
    public void saleTicket(){
        synchronized (this){ //这个对象可以为任意对象
            if(trainTicketCount > 0){
                System.out.println(Thread.currentThread().getName() + ", 出售第: " + (100 - trainTicketCount + 1) + " 票");
                trainTicketCount --;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimulateTrainTicket_03 simulateTrainTicket_01 = new SimulateTrainTicket_03();
        Thread t1 = new Thread(simulateTrainTicket_01,"窗口①号");
        Thread t2 = new Thread(simulateTrainTicket_01, "窗口②号");
        t1.start();
        Thread.sleep(40);
        t2.start();
    }
}
