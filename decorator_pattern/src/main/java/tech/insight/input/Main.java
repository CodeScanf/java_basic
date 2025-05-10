package tech.insight.input;

import java.io.*;
import java.time.Instant;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/10 22:06:02
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("decorator_pattern/book.pdf");
        long l = Instant.now().toEpochMilli();
        long times = 0L;
        try(InputStream inputStream = new BufferedFileInputStream(new FileInputStream(file))) {
            while (true){
                int read = inputStream.read();
                times++;
                if (read == -1) {
                    break;
                }
            }
            System.out.println("用时" + (Instant.now().toEpochMilli() - l) + "毫秒");
            System.out.println("调用：" + times + "次");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
