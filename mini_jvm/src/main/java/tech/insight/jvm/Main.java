package tech.insight.jvm;

/**
 * @ClassName: Main
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/2 13:54
 * @Version: 1.0
 **/
public class Main {
    public static void main(String[] args) throws Exception{
        Hotspot hotspot = new Hotspot("tech.insight.code.Demo", "/Users/lijiaobin/IdeaProjects/java_basic/mini_jvm/target/classes");
        hotspot.start();
    }
}
