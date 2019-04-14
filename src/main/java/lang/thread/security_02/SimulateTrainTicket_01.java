package lang.thread.security_02;

/**
 * Created by Calvin on 2019/4/13
 * 标题: 线程安全的问题
 * 模拟抢火车票
 */
public class SimulateTrainTicket_01 implements Runnable{

    /** 火车票有100 张 （共享资源）**/
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

    public void saleTicket(){
        if(trainTicketCount > 0){
            System.out.println(Thread.currentThread().getName() + ", 出售第: " + (100 - trainTicketCount + 1) + " 票");
            trainTicketCount --;
        }
    }

    public static void main(String[] args){
        SimulateTrainTicket_01 simulateTrainTicket_01 = new SimulateTrainTicket_01();
        Thread t1 = new Thread(simulateTrainTicket_01,"窗口①号");
        Thread t2 = new Thread(simulateTrainTicket_01, "窗口②号");
        t1.start();
        t2.start();
    }
}
