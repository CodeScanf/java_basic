package tech.insight.spring;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * @author lijiaobin
 * @version 1.0
 * @project Default (Template) Project
 * @description ${description}
 * @date 2025/5/26 21:48:02
 */
public class ApplicationContext {

    public ApplicationContext(String packageName) throws Exception {
        initContext(packageName);
    }

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String, Object> ioc = new HashMap<>();

    public void initContext(String packageName) throws Exception {
        scanpackage(packageName).stream().filter(this::scanCreate).map(this::wrapper).forEach(this::createBean);
    }

    protected boolean scanCreate(Class<?> type) {
        return type.isAnnotationPresent(Component.class);
    }

    protected void createBean(BeanDefinition beanDefinition) {
        String name = beanDefinition.getName();
        if (ioc.containsKey(name)) {
            return;
        }
        doCreateBean(beanDefinition);
    }

    private void doCreateBean(BeanDefinition beanDefinition) {
        Constructor<?> constructor = beanDefinition.getConstructor();
        Object bean = null;
        try {
            bean = constructor.newInstance();
            Method postConstructMethod = beanDefinition.getPostConstructMethod();
            if (postConstructMethod != null) {
                postConstructMethod.invoke(bean);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ioc.put(beanDefinition.getName(), bean);
    }

    protected BeanDefinition wrapper(Class<?> type) {
        BeanDefinition beanDefinition = new BeanDefinition(type);
        if (beanDefinitionMap.containsKey(beanDefinition.getName())){
            throw new RuntimeException("bean name is duplicate");
        }
        beanDefinitionMap.put(beanDefinition.getName(), beanDefinition);
        return beanDefinition;
    }

    private List<Class<?>> scanpackage(String packageName) throws Exception {
        List<Class<?>> classList = new ArrayList<>();
        // a.b.c
        URL resource = this.getClass().getClassLoader().getResource(packageName.replace(".", File.separator));
        Path path = Path.of(resource.getFile());
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path absolutePath = file.toAbsolutePath();
                if (absolutePath.toString().endsWith(".class")) {
                    String replaceStr = absolutePath.toString().replace(File.separator, ".");
                    int packageIndex = replaceStr.indexOf(packageName);
                    String className = replaceStr.substring(packageIndex, replaceStr.length() - ".class".length());
                    try {
                        classList.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return classList;
    }

    public Object getBean(String name) {
        return this.ioc.get(name);
    }

    public <T> T getBean(Class<T> beanType) {
        return this.ioc.values()
                .stream()
                .filter(bean -> beanType.isAssignableFrom(bean.getClass()))
                .map(bean -> (T) bean)
                .findAny()
                .orElse(null);
    }

    public <T> List<T> getBeans(Class<T> beanType) {
        return this.ioc.values()
                .stream()
                .filter(bean -> beanType.isAssignableFrom(bean.getClass()))
                .map(bean -> (T) bean)
                .toList();
    }
}