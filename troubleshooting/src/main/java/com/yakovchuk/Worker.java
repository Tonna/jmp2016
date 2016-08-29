package com.yakovchuk;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Worker implements Runnable {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh:MM:ss");
    private String name;
    private List<Resource> resources;

    public Worker(String name, Resource... resources) {
        this.name = name;
        this.resources = Arrays.asList(resources);
    }

    @Override
    public void run() {
        while (true) {
            Resource resource1 = resources.get(0);
            Resource resource2 = resources.get(1);
            synchronized (resource1){
                System.out.println(getTime() + " Worker {" + name + "} captured resource {" + resource1.getName() + "}");
                resource1.doStuff();
                try {
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getTime() + " Worker {" + name + "} waits for resource {" + resource2.getName() + "}");
                synchronized (resource2) {
                    System.out.println(getTime() + " Worker {" + name + "} captured resource {" + resource2.getName() + "}");
                    resource2.doStuff();
                    System.out.println(getTime() +
                            " Worker {" + name + "} did stuff with resources {" +
                            resource1.getName() + "} and {" + resource2.getName() + "}");
                    try {
                        Thread.sleep(new Random().nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private String getTime() {
        return DATE_FORMAT.format(new Date());
    }
}
