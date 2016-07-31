package com.yakovchuk;

import java.util.*;
import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.sleep;

public class MainGC2 {

    private static final int TEN_SECONDS = 10 * 1000;
    private static final Random RANDOM = new Random();
    private static final int MINUTE = 60 * 1000;

    public static void main(String[] args) {
        final Integer waiting = Integer.valueOf(args[0]);
        final Integer threads = Integer.valueOf(args[1]);
        final Integer appLifeTime = Integer.valueOf(args[2]);

        final Runnable job = new Runnable() {
            @Override
            public void run() {
                while(true) {
                    for (int i = 0; i < 1000; i++) {
                        List<Object> list = new LinkedList<>();
                        for (int j = 0; j < 1000; j++) {
                            list.add(String.valueOf(System.currentTimeMillis()));
                            list.add(System.currentTimeMillis());
                            list.add(new HashMap<>());
                            list.add(RANDOM.nextDouble());
                            list.add(RANDOM.nextGaussian());
                            list.add(list);
                        }
                    }
                }
            }
        };

        Runnable scheduler = new Runnable() {
            @Override
            public void run() {
                List<Thread> pool = new ArrayList<>();
                for (int i = 0; i < threads; i++) {
                    pool.add(new Thread(job));
                }
                for (Thread thread : pool) {
                    thread.start();
                }
                while (true) {
                    try {
                        for (Thread thread : pool) {
                            thread.sleep(RANDOM.nextInt(waiting));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable stopper = new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis() + (appLifeTime * MINUTE);
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
