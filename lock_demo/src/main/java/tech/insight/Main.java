package tech.insight;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijiaobin
 * @version 1.0
 * @project Default (Template) Project
 * @description ${description}
 * @date 2025/5/20 16:34:42
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] count = new int[]{1000};
        ArrayList<Thread> threads = new ArrayList<>();
        MyLock lock = new MyLock() ;
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                lock.lock();
                for (int j = 0; j < 10; j++) {
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    count[0]--;
                }
                lock.unlock();
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(count[0]);
    }
}