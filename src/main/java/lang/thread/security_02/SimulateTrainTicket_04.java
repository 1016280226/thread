package lang.thread.security_02;

/**
 * Created by Calvin on 2019/4/13
 * 标题: 多线程死锁
 * 模拟抢火车票
 */
public class SimulateTrainTicket_04 implements Runnable{

    /** 火车票有100 张 (共享资源)**/
    private int trainTicketCount = 100;
    private static boolean flag = true;
    private Object o = new Object();

    @Override
    public void run() {
        if(flag){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o){
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
        }else{
            while (trainTicketCount > 0){
                saleTicket();
            }
        }

    }

    /** 同步synchroized中嵌套synchroized代码块 **/
    public synchronized void saleTicket(){
        synchronized (o){ //这个对象可以为任意对象
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(trainTicketCount > 0){
                System.out.println(Thread.currentThread().getName() + ", 出售第: " + (100 - trainTicketCount + 1) + " 票");
                trainTicketCount --;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimulateTrainTicket_04 simulateTrainTicket_01 = new SimulateTrainTicket_04();
        Thread t1 = new Thread(simulateTrainTicket_01,"窗口①号");
        Thread t2 = new Thread(simulateTrainTicket_01, "窗口②号");
        t1.start();
        Thread.sleep(40);
        SimulateTrainTicket_04.flag = false;
        t2.start();
    }
}
