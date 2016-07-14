package com.yakovchuk;

import java.util.List;

public interface TodoListDAO {
    List<String> list();

    void add(String task);

    void remove(int taskNum);

    void removeAll();
}