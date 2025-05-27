package tech.insight.spring;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/27 19:35:29
 */
public interface BeanPostProcessor {
    default Object beforeInitializeBean(Object bean, String beanName){
        return bean;
    }

    default Object afterInitializeBean(Object bean, String beanName){
        return bean;
    }
}
