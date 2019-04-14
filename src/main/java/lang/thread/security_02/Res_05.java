package lang.thread.security_02;

/**
 * Created by Calvin on 2019/4/13
 * 标题: 多线程安全问题-使用Threadlocal 解决线程安全问题
 * 案例: 创建三个线程，每个线程生成自己独立序列号。
 */
public class Res_05 {

    public static Integer count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            // 设置线程局部变量的初始值
            return 0;
        }
    };

    // 获取线程对应的号码
    public Integer getNum(){
        // 当前线程所对应的线程局部变量 + 1 赋值给 count
        int count = threadLocal.get() + 1;
        // 每个线程生成自己独立序列号
        threadLocal.set(count);
        return count;
    }

}

class ThreadLocalDemon extends Thread{
    private Res_05 res_05;

    public ThreadLocalDemon(Res_05 res_05){
        this.res_05 = res_05;
    }
    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + "i---" + i + "--num:" + res_05.getNum());
        }
    }

    public static void main(String[] args) {
        Res_05 res = new Res_05();
        // 同一个共享的变量，为每个线程生成自己独立序列号
        ThreadLocalDemon threadLocaDemo1 = new ThreadLocalDemon(res);
        ThreadLocalDemon threadLocaDemo2 = new ThreadLocalDemon(res);
        ThreadLocalDemon threadLocaDemo3 = new ThreadLocalDemon(res);
        threadLocaDemo1.start();
        threadLocaDemo2.start();
        threadLocaDemo3.start();
    }

    /**
     * 运行结果：
     * Thread-0---i---0--num:1
     * Thread-2---i---0--num:1
     * Thread-1---i---0--num:1
     * Thread-2---i---1--num:2
     * Thread-0---i---1--num:2
     * Thread-2---i---2--num:3
     * Thread-1---i---1--num:2
     * Thread-1---i---2--num:3
     * Thread-0---i---2--num:3
     */
}
