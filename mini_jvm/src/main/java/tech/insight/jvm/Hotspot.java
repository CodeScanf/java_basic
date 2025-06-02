package tech.insight.jvm;

import tech.medivh.classpy.classfile.ClassFile;

import java.io.File;
import java.util.Arrays;

/**
 * @ClassName: Hotspot
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/2 13:53
 * @Version: 1.0
 **/
public class Hotspot {

    private String mainClass;

    private BootstrapClassLoader classLoader;

    public Hotspot(String mainClass, String calssPathString){
        this.mainClass = mainClass;
        this.classLoader = new BootstrapClassLoader(Arrays.asList(calssPathString.split(File.pathSeparator)));
    }
    public void start() throws Exception {
        ClassFile mainClassFile = classLoader.loadClass(mainClass);
        StackFrame mainFrame = new StackFrame(mainClassFile.getMainMethod(), mainClassFile.getConstantPool());
        Thread mainThread = new Thread("mian", mainFrame, classLoader);
        mainThread.start();
    }

}
