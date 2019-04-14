package lang.thread.base_01.ways_01;


/**
 * Created by Calvin on 2018/4/7
 * 标题: 线程的实现方式 -> 3.通过匿名内部类，实现多线程。
 */
public class ThreadByHiderInner_03 {

    public static void main(String[] args) {
        // 1.实例化线程，传入new Runnable方法作为参数
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(" 运行，i=" + i);
                }
            }
        });
        // 2.启动线程
        thread.start();
    }
}