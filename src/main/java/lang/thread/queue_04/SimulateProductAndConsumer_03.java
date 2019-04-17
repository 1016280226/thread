package lang.thread.queue_04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Calvin on 2019/4/15
 * 标题：模拟生成者 -> 队列 <- 消费者
 */
public class SimulateProductAndConsumer_03{

    public static void main(String[] args) {
        // 共享同一个队列
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        // 生产者生产数据入列
        Product product = new Product(blockingQueue);
        // 消费者获取队列信息
        Consumer consumer = new Consumer(blockingQueue);
        Thread p1 = new Thread(product);
        Thread c1 = new Thread(consumer);
        p1.start();
        c1.start();
        try{
            // 等待 10 秒时间 ,所有线程休眠10 秒
            Thread.sleep(1000 * 10);
            // 将生成者生产停止。
            product.stop();
        }catch (Exception e){

        }
    }
}

/**
 * 标题：生成者
 * 功能：入列
 */
class Product implements Runnable {

    public BlockingQueue<String> blockingQueue;
    /** Volatile 线程可见性，可以禁止重排序 */
    public volatile Boolean FLAG = Boolean.TRUE;
    /** 计数器 */
    AtomicInteger atomicInteger = new AtomicInteger();

    public Product(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println("生产者线程已经启动");
        try {
            // 一直生产
            while (FLAG) {
                // 计数器自动增长
                String data = atomicInteger.incrementAndGet() + "";
               // 入列
                boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
                if (!offer) {
                    // 如果超过队列满了, 超过2秒后，入列失败。
                    System.out.println("生产者传入队列失败! data:" + data);  // 有可能存满了
                } else {
                    // 否则，入列成功
                    System.out.println("生成者传入队列成功！ data:" + data);
                }
                // 入列后，线程休眠 1 秒
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 最后，生成结束
            System.out.println("生产者已经结束 ...");
        }

    }

    public void stop() {
        this.FLAG = Boolean.FALSE;
    }
}

/**
 * 标题：消费者
 * 功能：出列
 */
class Consumer implements  Runnable{
    public BlockingQueue<String> blockingQueue;
    public volatile Boolean FLAG = Boolean.TRUE;

    Consumer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println("消费者线程已经启动....");
        try{
            // 表示消费者线程一直在读取队列消息。
            while(FLAG){
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if(data == null){
                    System.out.println("超过2秒时间，没有获取到队列信息");
                    FLAG = false;
                    return;
                }
                System.out.println("消费者获取到data：" + data);
            }
        }catch (Exception e){

        }finally{
            System.out.println("消费线程已经结束...");
        }
    }
}

