package com.yakovchuk;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainGC {

    private static final int TEN_MINUTES = 10 * 60 * 60 * 1000;
    private static final int TEN_SECONDS = 10 * 1000;

    public static void main(String[] args) {
        final Integer load = Integer.valueOf(args[0]);
        final Integer interval = Integer.valueOf(args[1]);
        final Integer threads = Integer.valueOf(args[2]);

        final Runnable job = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < load; i++) {
                    List<String> list = new LinkedList<>();
                    for (int j = 0; j < 1000; j++) {
                        list.add(String.valueOf(System.currentTimeMillis()));
                    }
                }
            }
        };

        Runnable scheduler = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < threads; i++) {
                        new Thread(job).start();
                    }
                }

            }
        };

        Runnable stopper = new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis() + TEN_MINUTES;
                while (System.currentTimeMillis() < endTime) {
                    try {
                        sleep(TEN_SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("stopping");
                System.exit(0);
            }
        };

        new Thread(scheduler).start();
        new Thread(stopper).start();

    }

}
