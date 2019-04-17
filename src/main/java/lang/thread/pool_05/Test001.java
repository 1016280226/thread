package lang.thread.pool_05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Calvin on 2019/4/16
 */
public class Test001 {

    public static void main(String[] args) {
        /**
         *  可缓存的线程池（无限大小）
         *  功能：重复利用复用机制
         */
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i <20 ; i++) {
            final int temp = i;
            // 可执行线程execute 方法表示启动线程
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "," + temp);
                }
            });
        }


    }
}
