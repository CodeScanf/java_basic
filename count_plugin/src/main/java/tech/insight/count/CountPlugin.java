package tech.insight.count;

import tech.insight.plugin.MyPlugin;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/18 22:50:20
 */
public class CountPlugin implements MyPlugin {

    AtomicInteger count = new AtomicInteger(0);
    @Override
    public void beforeGetTime() {
        System.out.println(count.incrementAndGet());
    }
}
