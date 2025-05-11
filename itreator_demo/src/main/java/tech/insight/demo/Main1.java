package tech.insight.demo;

import java.io.File;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/11 22:47:32
 */
public class Main1 {
    public static void main(String[] args) {
        File file = new File("itreator_demo/demo.user");
        new UserFile(file).forEach(System.out::println);
    }
}
