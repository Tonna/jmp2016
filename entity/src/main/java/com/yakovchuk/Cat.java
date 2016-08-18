package com.yakovchuk;

public class Cat implements Animal {
    @Override
    public String play() {
        return "climb a tree";
    }

    @Override
    public String voice() {
        return "Meow!";
    }
}
