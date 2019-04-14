package lang.thread.base_01.ways_01;

/**
 * Created by Calvin on 2018/4/7
 * 标题: 线程的实现方式 -> 1.通过继承Thread，实现多线程。
 */
// 1. 继承Thread
public class ThreadByExtendsThread_01 extends Thread {

    // 2. 重写run() 方法
    @Override
    public void run(){
        for(int i = 0 ; i<10; i++){
            System.out.println("运行，i=" + i );
        }
    }

    // 测试
    public static void main(String[] args) {
        System.out.println("-----多线程创建开始-----");
        // 1.创建一个线程
        ThreadByExtendsThread_01 createThread = new ThreadByExtendsThread_01();
        // 2.开始执行线程 注意 开启线程不是调用run方法，而是start方法
        System.out.println("-----多线程创建启动-----");
        createThread.start();
        System.out.println("-----多线程创建结束-----");
    }
}
