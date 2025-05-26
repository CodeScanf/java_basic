package tech.insight.spring;

import tech.insight.spring.sub.PostConstruct;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/26 22:00:00
 */
public class BeanDefinition {
    private String name;
    private Constructor<?> constructor;

    private Method postConstructMethod;

    public BeanDefinition(Class<?> type) {
        Component component = type.getDeclaredAnnotation(Component.class);
        this.name = component.name().isEmpty() ? type.getSimpleName() : component.name();
        try {
            this.constructor = type.getConstructor();
            this.postConstructMethod = Arrays.stream(type.getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(PostConstruct.class))
                    .findFirst().orElse(null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return this.name;
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }

    public Method getPostConstructMethod() {
        return postConstructMethod;
    }
}
