package tech.insight;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/10 13:35:21
 */
public class ThrowRejectHandle implements RejectHandle{
    @Override
    public void reject(Runnable reject, MyThreadPool threadPool) {
        throw new RuntimeException("阻塞队列满了");
    }
}
