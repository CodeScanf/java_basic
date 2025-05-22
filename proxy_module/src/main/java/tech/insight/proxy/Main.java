package tech.insight.proxy;

import java.lang.reflect.Field;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/22 15:55:14
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MyInterface proxtObject = MyInterfaceFactory.createProxtObject(new PrintFunctionName());
        proxtObject.func1();
        proxtObject.func2();
        proxtObject.func3();
        System.out.println("----------------");
        proxtObject = MyInterfaceFactory.createProxtObject(new PrintFunctionName1());
        proxtObject.func1();
        proxtObject.func2();
        proxtObject.func3();
        System.out.println("----------------");
        proxtObject = MyInterfaceFactory.createProxtObject(new LogHandler(proxtObject));
        proxtObject.func1();
        proxtObject.func2();
        proxtObject.func3();
    }

    static class PrintFunctionName implements MyHandler {

        @Override
        public String functionBody(String methodName) {
            return " System.out.println(\"" + methodName + "\");";
        }
    }

    static class PrintFunctionName1 implements MyHandler {

        @Override
        public String functionBody(String methodName) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" System.out.println(1);")
                    .append(" System.out.println(\"" + methodName + "\");");
            return stringBuilder.toString();
        }
    }

    static class LogHandler implements MyHandler {
        MyInterface myInterface;

        public LogHandler(MyInterface myInterface) {
            this.myInterface = myInterface;
        }
        @Override
        public void setProxy(MyInterface proxy) {
            Class<? extends MyInterface> aClass = proxy.getClass();
            Field field = null;
            try {
                field = aClass.getDeclaredField("myInterface");
                field.setAccessible(true);
                field.set(proxy,myInterface);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        @Override
        public String functionBody(String methodName) {
            String context = " System.out.println(\"before\");\n" +
                    "       myInterface." + methodName + "();\n" +
                    "       System.out.println(\"after\");";
            return context;
        }
    }
}
