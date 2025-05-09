package tech.insight;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.LockSupport;

public class ScheduleService {
    Trigger trigger = new Trigger();
    ExecutorService executorService = Executors.newFixedThreadPool(6);
    void schedule(Runnable task, long delay) {
        Job job = new Job();
        job.setTask(task);
        job.setStartTime(System.currentTimeMillis() + delay);
        job.setDelay(delay);
        this.trigger.queue.offer(job);
        this.trigger.wakeup();
    }

    //  等待合适的时间，把对应的任务扔到线程池中
    class Trigger {
        PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

        Thread thread = new Thread(() -> {
            while (true){
                while (queue.isEmpty()){
                    LockSupport.park();
                }
                Job latalyJob = queue.peek();
                if (latalyJob.getStartTime() < System.currentTimeMillis()) {
                    latalyJob = queue.poll();
                    executorService.execute(latalyJob.getTask());
                    Job nextJob = new Job();
                    nextJob.setTask(latalyJob.getTask());
                    nextJob.setDelay(latalyJob.getDelay());
                    nextJob.setStartTime(latalyJob.getDelay() + System.currentTimeMillis());
                    queue.offer(nextJob);
                }else {
                    LockSupport.parkUntil(latalyJob.getStartTime());
                }
            }
        });

        {
            thread.start();
            System.out.printf("触发器启动了！");
        }
        void wakeup(){
            LockSupport.unpark(thread);
        }
    }
}