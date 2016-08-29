package com.yakovchuk;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Worker implements Runnable {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh:MM:ss.SSS");
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
            System.out.println(getTime() + " Worker {" + name + "} waits for resource {" + resource1.getName() + "}");
            synchronized (resource1) {
                System.out.println(getTime() + " Worker {" + name + "} captured resource {" + resource1.getName() + "}");
                resource1.doStuff();
                System.out.println(getTime() + " Worker {" + name + "} waits for resource {" + resource2.getName() + "}");
                synchronized (resource2) {
                    System.out.println(getTime() + " Worker {" + name + "} captured resource {" + resource2.getName() + "}");
                    resource2.doStuff();
                }
            }
            System.out.println(getTime() +
                    " Worker {" + name + "} released resources {" +
                    resource1.getName() + "} and {" + resource2.getName() + "}");
        }

    }

    private String getTime() {
        return DATE_FORMAT.format(new Date());
    }
}
