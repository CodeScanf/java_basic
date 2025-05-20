package tech.insight;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/20 16:35:02
 */
public class MyLock {
    AtomicBoolean flag = new AtomicBoolean(false);

    Thread owner = null;

    AtomicReference<Node> head = new AtomicReference<>(new Node());
    AtomicReference<Node> tail = new AtomicReference<>(head.get());

    void lock() {
//        if (flag.compareAndSet(false, true)) {
//            System.out.println(Thread.currentThread().getName() + "直接拿到锁");
//            owner = Thread.currentThread();
//            return;
//        }
        Node current = new Node();
        current.thread = Thread.currentThread();
        while (true) {
            Node currentTail = tail.get();
            if (tail.compareAndSet(currentTail, current)) {
                System.out.println(Thread.currentThread().getName() + "加入到了链表尾");
                current.pre = currentTail;
                currentTail.next = current;
                break;
            }
        }
        while (true) {
            //  condition
            if (current.pre == head.get() && flag.compareAndSet(false, true)) {
                owner = Thread.currentThread();
                head.set(current);
                current.pre.next = null;
                current.pre = null;
                System.out.println(Thread.currentThread().getName() + "被唤醒之后拿到锁");
                return;
            }
            LockSupport.park();
        }
    }

    void unlock() {
        if (Thread.currentThread() != owner) {
            throw new IllegalStateException("当前线程并没有锁，不能解锁");
        }
        Node headNode = head.get();
        Node next = headNode.next;
        flag.set(false);
        if (next != null) {
            System.out.println(Thread.currentThread().getName() + "唤醒了" + next.thread.getName());
            LockSupport.unpark(next.thread);
        }


    }

    class Node {
        Node pre;
        Node next;
        Thread thread;
    }
}
