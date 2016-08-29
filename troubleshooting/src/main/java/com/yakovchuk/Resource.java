package com.yakovchuk;

public class Resource {

    private final String name;

    public Resource(String i) {
        this.name = i;
    }

    public synchronized String doStuff() {
        return "Do something important";
    }

    public String getName() {
        return name;
    }
}
