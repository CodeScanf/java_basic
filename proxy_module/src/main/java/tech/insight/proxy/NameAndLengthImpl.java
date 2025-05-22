package tech.insight.proxy;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/21 21:18:07
 */
public class NameAndLengthImpl implements MyInterface {
    @Override
    public void func1() {
        String methodName = "func1";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }

    @Override
    public void func2() {
        String methodName = "func2";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }

    @Override
    public void func3() {
        String methodName = "func3";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }
}
