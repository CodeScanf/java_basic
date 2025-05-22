package tech.insight.proxy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/21 21:21:39
 */
public class MyInterfaceFactory {
    private static final AtomicInteger count = new AtomicInteger();

    private static File createJavaFile(String className, MyHandler handler) throws IOException {
        String func1Body = handler.functionBody("func1");
        String func2Body = handler.functionBody("func2");
        String func3Body = handler.functionBody("func3");
        String context = "package tech.insight.proxy;\n" +
                "\n" +
                "public class " + className + " implements MyInterface{\n" +
                " MyInterface myInterface;\n"+
                "    @Override\n" +
                "    public void func1() {\n" +
                "        " + func1Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func2() {\n" +
                "        " + func2Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func3() {\n" +
                "        " + func3Body + "\n" +
                "    }\n" +
                "}\n";
        File javaFile = new File(className + ".java");
        Files.writeString(javaFile.toPath(), context);
        return javaFile;
    }

    private static String getClassName() {
        return "MyInterface$proxy" + count.incrementAndGet();
    }

    public static MyInterface newInstance(String className, MyHandler handler) throws Exception {
        Class<?> aClass = MyInterfaceFactory.class.getClassLoader().loadClass(className);
        Constructor<?> constructor = aClass.getConstructor();
        MyInterface proxy = (MyInterface)constructor.newInstance();
        handler.setProxy(proxy);
        return proxy;
    }

    public static MyInterface createProxtObject(MyHandler myHandler) throws Exception {
        String className = getClassName();
        File javaFile = createJavaFile(className,myHandler);
        Compiler.compile(javaFile);
        return newInstance("tech.insight.proxy." + className, myHandler);
    }
}
