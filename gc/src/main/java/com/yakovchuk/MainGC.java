package com.yakovchuk;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainGC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            Integer decoded = Integer.decode(scanner.next());
            runSttuff(decoded);
        }
    }

    private static void runSttuff(final Integer decoded) {
        System.out.println("Add stuff " + decoded);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < decoded; i++) {
                    List list = new LinkedList<Object>();
                    for (int j = 0; j < 1000; j++) {
                        list.add(new Object());
                    }
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
