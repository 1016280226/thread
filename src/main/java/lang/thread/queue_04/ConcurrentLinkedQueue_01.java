package lang.thread.queue_04;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Calvin on 2019/4/15
 * 标题:  非阻塞队列 （无界队列）
 */
public class ConcurrentLinkedQueue_01 {

    public static void main(String[] args) {
        // 非阻塞队列 （无界队列）
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue.offer("张三");
        concurrentLinkedQueue.offer("李四");
        concurrentLinkedQueue.offer("王五");
        // 获取队列(只能获取一个队列元素)
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        // 获取队列个数
        System.out.println(concurrentLinkedQueue.size());
    }
}
