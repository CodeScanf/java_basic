package tech.insight;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/10 13:28:40
 */
public interface RejectHandle {
    // 拿到失败的任务
    void reject(Runnable reject, MyThreadPool threadPool);
}
