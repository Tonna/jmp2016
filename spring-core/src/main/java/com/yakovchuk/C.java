package com.yakovchuk;

public abstract class C {

    private A father;
    private D son;

    public void setFather(A father) {
        this.father = father;
    }

    public void setSon(D son) {
        this.son = son;
    }

    public D getSon() {
        return createD();
    }

    public abstract D createD();

    @Override
    public String toString() {
        return "C{" +
                "father=" + father +
                ", son=" + getSon() +
                '}';
    }
}
