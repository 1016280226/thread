package lang.thread.queue_04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Calvin on 2019/4/15
 * 标题: 阻塞式队列
 *  好处：a.防止队内容器溢出。
 */
public class BlockQueue_02 {

    // 存入队列的时候，如果满了，就会等待。
    // 获取对列的时候，如果获取不到，就会等待。
    public static void main(String[] args) throws InterruptedException {
        // 最多支持3个队列总数
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        // 添加非阻塞式
        boolean q1 = arrayBlockingQueue.offer("张三");
        System.out.println("张三出入队列是否成功: " + q1);
        // 添加阻塞式
        boolean q2 = arrayBlockingQueue.offer("李四", 3, TimeUnit.SECONDS);
        System.out.println("李四出入队列是否成功: " + q2);
        // 添加非阻塞式
        boolean q3 = arrayBlockingQueue.offer("王五");
        System.out.println("王五出入队列是否成功: " + q3);
        // 添加阻塞式
        boolean q4 = arrayBlockingQueue.offer("赵六", 3, TimeUnit.SECONDS);
        System.out.println("赵六出入队列是否成功: " + q4);
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(3, TimeUnit.SECONDS));
    }

}
