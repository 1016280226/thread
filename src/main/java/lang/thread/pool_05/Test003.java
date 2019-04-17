package lang.thread.pool_05;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Calvin on 2019/4/16
 */
public class Test003 {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int temp = i;

            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "," + temp);
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

}
