package tech.insight;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/10 13:37:52
 */
public class DiscardRejectHandle implements RejectHandle{
    @Override
    public void reject(Runnable rejectCommand, MyThreadPool threadPool) {
        threadPool.blockingQueue.poll();
        threadPool.execute(rejectCommand);
    }
}
