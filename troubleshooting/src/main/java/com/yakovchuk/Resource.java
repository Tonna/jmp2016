package com.yakovchuk;

import java.util.Random;

public class Resource {

    private final String name;

    public Resource(String i) {
        this.name = i;
    }

    public synchronized String doStuff() {
        return "Hi";
    }

    public String getName() {
        return name;
    }
}
