package lang.thread.base_01.ways_01;

/**
 * Created by Calvin on 2018/4/7
 * 标题: 线程的实现方式 -> 2.通过实现Runnable接口，实现多线程。
 */
// 1.实现Runnable 接口
public class ThreadByImplRunnable_02 implements Runnable {

    // 2. 重写run() 方法
    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println("运行，i="+i);
        }
    }

    // 测试
    public static void main(String[] args) {
        System.out.println("-----多线程创建开始-----");
        ThreadByImplRunnable_02 implRunnable_02 = new ThreadByImplRunnable_02();
        System.out.println("-----多线程创建启动-----");
        Thread thread = new Thread(implRunnable_02);
        thread.start();
        System.out.println("-----多线程创建结束-----");
    }
}
