package com.ihover;

public class Utils {

    public static void print(final String msg) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
        }
        System.out.print(msg);
    }

    public static void println(final String msg) {
        print(msg + '\n');
    }
}
