package tech.insight;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/10 11:48:39
 */
public class Main {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(2,4,1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new DiscardRejectHandle());
        for (int i = 0; i < 8; i++) {
            final int fi = i;
            myThreadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "\t" + fi);
            });
        }

        System.out.println("主线程没有被阻塞");
    }
}
