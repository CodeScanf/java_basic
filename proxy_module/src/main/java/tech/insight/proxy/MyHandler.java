package tech.insight.proxy;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/22 15:57:22
 */
public interface MyHandler {
    String functionBody(String methodName);

    default void setProxy(MyInterface proxy){

    }
}
