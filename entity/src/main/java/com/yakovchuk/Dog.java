package com.yakovchuk;

public class Dog implements Animal {
    @Override
    public String play() {
        return "Dig a hole in the ground";
    }

    @Override
    public String voice() {
        return "Woof, woof!";
    }
}
