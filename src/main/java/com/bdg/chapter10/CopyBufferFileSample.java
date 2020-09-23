package com.bdg.chapter10;
import java.io.*;
public class CopyBufferFileSample {
    public static void copy(File source, File destination) throws IOException {
        try (
                InputStream in = new BufferedInputStream(new FileInputStream(source));
                OutputStream out = new BufferedOutputStream(
                        new FileOutputStream(destination))) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer,0,lengthRead);
                out.flush();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        File source = new File("G:\\Java\\Zoo.txt");
        File destination = new File("G:\\Java\\ZooCopy.txt");
        copy(source,destination);
    }
}

