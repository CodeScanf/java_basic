package tech.insight.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/10 22:17:38
 */
public class BufferedFileInputStream extends InputStream {

    private final byte[] buffer = new byte[8192];

    private int position = -1;

    private int capacity = -1;

    private final FileInputStream fileInputStream;

    public BufferedFileInputStream(FileInputStream fileInputStream){
        this.fileInputStream = fileInputStream;
    }
    @Override
    public int read() throws IOException {
        if (bufferCanRead()){
            return readFromBuffer();
        }
        refreshBuffer();
        if (!bufferCanRead()){
            return -1;
        }
        return readFromBuffer();
    }

    private int readFromBuffer() {
        return buffer[position++] & 0xff;
    }

    private void refreshBuffer() throws IOException{
        capacity = this.fileInputStream.read(buffer);
        position = 0;
    }


    private boolean bufferCanRead() {
        if (capacity == -1){
            return false;
        }
        if (capacity == position){
            return false;
        }
        return true;
    }

    @Override
    public void close() throws IOException {

    }
}
