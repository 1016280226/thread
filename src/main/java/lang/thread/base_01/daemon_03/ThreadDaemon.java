package lang.thread.base_01.daemon_03;

/**
 * Created by Calvin on 2019/4/13
 * 标题：守护线程
 */
public class ThreadDaemon {

    public static void main(String[] args) {
        Thread childThread = childThread();
        // 子线程设置线程为守护线程
        childThread.setDaemon(true);
        // 子线程启动线程
        childThread.start();
        // 主线程
        mainThread();
    }

    public static Thread childThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("我是子线程...");
                }
            }
        });
        return thread;
    }

    public static void mainThread(){
        for (int i = 0; i <10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是主线程");
        }
        System.out.println("主线程执行完毕！");
    }
}
