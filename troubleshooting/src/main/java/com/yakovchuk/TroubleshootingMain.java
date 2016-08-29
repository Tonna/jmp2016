package com.yakovchuk;

import java.util.ArrayList;
import java.util.Random;

public class TroubleshootingMain {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Resource> resources = new ArrayList<>();
        ArrayList<Thread> workers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            resources.add(new Resource(String.valueOf(i + 1)));
        }
        workers.add(new Thread(new Worker("A", resources.get(0), resources.get(1))));
        workers.add(new Thread(new Worker("B", resources.get(1), resources.get(2))));
        workers.add(new Thread(new Worker("C", resources.get(2), resources.get(3))));
        workers.add(new Thread(new Worker("D", resources.get(3), resources.get(0))));

        for (Thread worker : workers) {
            new Thread(worker).start();
            Thread.sleep(RANDOM.nextInt(1000));
        }


    }
}
