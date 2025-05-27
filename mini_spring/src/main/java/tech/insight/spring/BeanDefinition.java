package tech.insight.spring;

import tech.insight.spring.sub.Autowired;
import tech.insight.spring.sub.PostConstruct;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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

    private final List<Field> autowiredFields;  //需要自动注入的属性

    private final Class<?> beanType;

    public BeanDefinition(Class<?> type) {
        this.beanType = type;
        Component component = type.getDeclaredAnnotation(Component.class);
        this.name = component.name().isEmpty() ? type.getSimpleName() : component.name();
        try {
            this.constructor = type.getConstructor();
            this.postConstructMethod = Arrays.stream(type.getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(PostConstruct.class))
                    .findFirst().orElse(null);
            this.autowiredFields = Arrays.stream(type.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(Autowired.class))
                     .toList();
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

    public List<Field> getAutowiredFields() {
        return autowiredFields;
    }

    public Class<?> getBeanType() {
        return beanType;
    }
}
