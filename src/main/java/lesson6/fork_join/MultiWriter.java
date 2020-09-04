package lesson6.fork_join;

import java.io.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultiWriter extends RecursiveAction {

    private RandomAccessFile readFile;
    private RandomAccessFile writeFile;
    private byte[] bucket;
    private final int threshold = 1000;
    private long start;
    private long end;

    public MultiWriter(RandomAccessFile readFile, RandomAccessFile writeFile, long start, long end) {
        this.readFile = readFile;
        this.writeFile = writeFile;
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        try {
            if (end - start <= threshold) {
                readFile.seek(start);
                writeFile.seek(start);
                bucket = new byte[(int) (end - start)];
                readFile.read(bucket, 0, bucket.length);
                writeFile.write(bucket);
            } else {
                long middle = start + ((end - start) / 2);
                invokeAll(new MultiWriter(readFile, writeFile, start, middle),
                        new MultiWriter(readFile, writeFile, middle, end));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("nums.txt"))) {
//            for (int i = 0; i < 5_000_000; i++) {
//                out.writeInt(i);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        try (RandomAccessFile reader = new RandomAccessFile("nums.txt", "r");
             RandomAccessFile writer = new RandomAccessFile("out.txt", "rw")) {
            System.out.println(reader.length()); //20_000_000 byte
            pool.invoke(new MultiWriter(reader, writer, 0, reader.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream in = new DataInputStream(new FileInputStream("out.txt"))) {
            for (int i = 0; i < 50_000; i++) {
                System.out.println(in.readInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
