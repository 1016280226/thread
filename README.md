#Thread
* [线程和进程的区别](#线程)
    * [线程](#线程)
    * [进程](#进程)
* [多线程的两种实现方式和区别](#实现方式和区别)
    * [extends Thread](#继承线程)
    * [implements Runnable](#实现Runnable)
* [线程的状态](#状态变化)
* [多线程的主要操作方法](#操作方法)
* [多线程的同步和死锁](#同步、死锁)
* [线程的生命周期](#生命周期)

##1.线程和进程的区别
1. 进程：是程序一次动态执行的过程，它需要经历从代码的记载、代码执行、执行完毕的一个完整过程。
2. 线程：线程和进程一样。
####区别：
        a. 线程比进程更小的执行单位。
        b. 一个进程里包含许多的线程。（多个线程同时出现，被称为多线程）
        
##2.多线程的两种实现方式和区别
####2.1 extends Thread

````java
package lang.thread.ways;
/**
 * Created by Calvin on 2018/4/7
 * 
 * 线程的实现方式：通过继承线程，来实现线程。
 *  a.extends Thread
 *  b.覆盖Thread 类中的 run()方法.
 */
public class ThreadByExtendsThread extends Thread {
    
    /**
     * 记录线程运行次数
     */
    private static int RUNNING_COUNT = 0;
    
    /**
     * 线程名称
     */
    private String threadName;
    
    ThreadByExtendsThread(String threadName){
        this.threadName = threadName;
    }
    /**
     * 此方法是线程中的主体
     */
    @Override
    public void run(){
        for(int i = 0 ; i<10; i++){
            // 记录运行次数
            ++RUNNING_COUNT;
            // 通过打印，查看线程运行状态
            System.out.println(threadName + "运行，i=" + i + " 运行次数"+ RUNNING_COUNT );
        }
    }
}
````
线程测试：
````java
package lang.thread.ways;

import org.junit.Test;

/**
 * Created by Calvin on 2018/4/7
 */
public class ThreadByExtendsThreadTest {

    /**
     * 线程是否是有序的 ？
     *     无序
     */
    @Test
    public void isOrder(){
        ThreadByExtendsThread t1 = new ThreadByExtendsThread("线程 A");
        ThreadByExtendsThread t2 = new ThreadByExtendsThread("线程 B");
        t1.start();
        t2.start();
    }

    /**
     * 同一个线程是否可以调用多次？
     *   不可以，报错 java.lang.IllegalThreadStatExcpetion
     */
    @Test
    public void isCallTimes(){
        ThreadByExtendsThread t3 = new ThreadByExtendsThread("线程 C");
        t3.start();
        t3.start();
    }

}

````
运行isOrder结果1：
![通过继承线程的运行结果图](file:///G:/localspace/Thread/src/main/resources/img/ThreadByExtendsThread_Result1.png)

运行isCallTimes结果2：
![通过继承线程的运行结果图](file:///G:/localspace/Thread/src/main/resources/img/ThreadByExtendsThread_Result2.png)

总结：
>如果一个类只能继承Thread类才能实现多线程，会受到单继承的局限的影响（不能多继承、耦合性增加）

####2.2 implements Runnable
````java
package lang.thread.ways;

/**
 * Created by Calvin on 2018/4/7
 */

/**
 * 线程的实现方式：通过实现 Runnable，来实现线程。
 *  a.implements Runnable
 *  b.实现Runnable 中的抽象方法
 */
public class ThreadByImplRunnable implements Runnable {

    /**
     * 记录运行的次数
     */
    private static int RUNING_COUNT = 0;

    /**
     * 线程名称
     */
    private String threadName;

    public ThreadByImplRunnable(String threadName){
        this.threadName = threadName;
    }

    /**
     * 实现Runnable 中的抽象方法
     */
    public void run() {
        for(int i=0; i<10; i++){
            ++ RUNING_COUNT;
            System.out.println(threadName+" 运行，i="+i+" 运行的次数："+ RUNING_COUNT);
        }
    }
}

````
线程测试：
````java
package lang.thread.ways;

import org.junit.Test;

/**
 * Created by Calvin on 2018/4/7
 * 通过实现Runnable来实现线程-测试类
 */
public class ThreadByImplRunnableTest {

    /**
     * 线程启动
     */
    @Test
   public void threadStart(){
        ThreadByImplRunnable tD = new ThreadByImplRunnable("线程 D");
        ThreadByImplRunnable tE = new ThreadByImplRunnable("线程 E");
        // 通过Thread 带参构造方法，启动线程
        Thread t4  = new Thread(tD);
        Thread t5  = new Thread(tE);
        t4.start();
        t5.start();
    }

    /**
     * 同一个线程是否可以调用多次？
     *   可以
     */
    @Test
    public void isCallTimes(){
        ThreadByImplRunnable tF = new ThreadByImplRunnable("线程 F");
        // 与继承线程不同的是，这里的多次调用是：通过创建2个新的线程来进行多次调用同一个线程F
        Thread t6  = new Thread(tF);
        Thread t7  = new Thread(tF);
        t6.start();
        t7.start();
    }
}
````
运行threaStart结果1：
![通过继承线程的运行结果图](file:///G:/localspace/Thread/src/main/resources/img/ThreadByImplRunnable_Result1.png)

运行isCallTimes结果2：
![通过继承线程的运行结果图](file:///G:/localspace/Thread/src/main/resources/img/ThreadByImplRunnable_Result2.png)

总结：
>1. 通过实现Runnable接口来实现多线程，启动时必须依靠Thread中构造方法
>2. 与继承线程不同的是，这里的多次调用是：通过创建2个新的线程来进行多次调用同一个线程F

####2.3 区别：
        a. 继承 threed 类, 是不可以共享资源的。
        b. 实现 Runable 接口， 是可以共享资源的。
        
##3.线程的状态
####3.1 线程中的5中状态：
* 1.创建
>Thread t = new Thread();
* 2.准备
>t.start()
* 3.运行
>t.run();
* 4.阻塞
>t.sleep() / t.suspend() / wait();
* 5.终止
>t.stop() / run()方法后结束

####3.2 线程中的主要方法