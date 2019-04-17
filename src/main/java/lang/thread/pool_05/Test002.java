package lang.thread.pool_05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Calvin on 2019/4/16
 */
public class Test002 {

    public static void main(String[] args) {
        /**
         * 可固定长度线程池
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i <10 ; i++) {
            final int temp = i;
            // 可执行线程execute 方法表示启动线程
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "," + temp);
                }
            });
        }

    }
}
