package com.bdg.chapter7;

import com.bdg.chapter7.runnable.PrintData;
import com.bdg.chapter7.runnable.ReadInventoryThread;

/**
 * @author Tigran
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("begin");
        (new ReadInventoryThread()).start();
        (new Thread(new PrintData())).start();
        (new ReadInventoryThread()).start();
        System.out.println("end");
    }
}
