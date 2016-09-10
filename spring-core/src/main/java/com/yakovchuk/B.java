package com.yakovchuk;

public class B {

    private A friend;

    public B(A friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "B hangs out with {" +
                "friend=" + friend +
                '}';
    }
}
