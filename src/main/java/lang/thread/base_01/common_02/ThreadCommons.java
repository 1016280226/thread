package lang.thread.base_01.common_02;

/**
 * Created by Calvin on 2019/4/13
 * 标题: 多线程常用的方法
 */
public class ThreadCommons  implements Runnable{

    public static void main(String[] args) throws InterruptedException {
       ThreadCommons threadCommons = new ThreadCommons();
        Thread thread =  new Thread(threadCommons);  // 自动设置线程名称
        Thread a_thread = new Thread(threadCommons, "线程A"); // 手动线程名称
        Thread b_thread = new Thread(threadCommons, "线程B"); // 手动线程名称
        // 常用方法三: 判断线程是否启动
        System.out.println("线程:"+ thread.getName() + "开始执行之前：" + thread.isAlive());
        thread.start();
        // 常用方法四: join 强制运行当前线程
        thread.join();
        System.out.println("线程:" + thread.getName() + "开始执行之后：" + thread.isAlive());
        System.out.println("线程:"+ a_thread.getName() + "开始执行之前：" + a_thread.isAlive());
        a_thread.start();
        b_thread.start();
        // 常用方法五：优先级（10，5）
        b_thread.setPriority(10);
        System.out.println("线程:" + a_thread.getName() + "开始执行之后：" + a_thread.isAlive());

    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            // 常用方法一: 获取当前线程 和 线程名称
            String threadName = Thread.currentThread().getName();
            // 常用方法二: 查看线程的优先级
            int priority = Thread.currentThread().getPriority();
            System.out.println("当前线程名称："+ threadName + " 线程的优先级:" + priority +  " 运行i: " + i);
            try {
                // 常用方法六： 线程休眠
                Thread.currentThread().sleep(5000);
                System.out.println(threadName + "线程，休眠：5 秒, 线程ID: " + Thread.currentThread().getId() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
