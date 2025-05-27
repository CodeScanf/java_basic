package tech.insight.spring.sub;

import tech.insight.spring.BeanPostProcessor;
import tech.insight.spring.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/27 20:12:30
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object afterInitializeBean(Object bean, String beanName) {
        System.out.println("MyBeanPostProcessor afterInitializeBean " + beanName);
        return bean;
    }
}

