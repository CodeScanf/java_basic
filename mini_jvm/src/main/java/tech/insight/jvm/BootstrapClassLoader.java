package tech.insight.jvm;

import tech.medivh.classpy.classfile.ClassFile;
import tech.medivh.classpy.classfile.ClassFileParser;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: BootStraptClassLoader
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/2 14:58
 * @Version: 1.0
 **/
public class BootstrapClassLoader {
    private final List<String> classPath;

    public BootstrapClassLoader(List<String> classPath) {
        this.classPath = classPath;
    }


    public ClassFile loadClass(String fqcn) throws ClassNotFoundException {
        return classPath.stream()
                .map(path -> tryLoad(path, fqcn))
                .filter(Objects::nonNull)
                .findAny()
                .orElseThrow(() -> new ClassNotFoundException(fqcn + "找不到"));
    }

    private ClassFile tryLoad(String path, String mainClass) {
        File classFilePath = new File(path, mainClass.replace(".", File.separator) + ".class");
        if (!classFilePath.exists()) {
            return null;
        }
        try {
            byte[] bytes = Files.readAllBytes(classFilePath.toPath());
            return new ClassFileParser().parse(bytes);
        } catch (Exception e) {
            return null;
        }
    }
}
