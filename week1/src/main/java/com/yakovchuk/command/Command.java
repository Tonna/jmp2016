package com.yakovchuk.command;

import com.yakovchuk.TodoListDAO;

import java.io.PrintStream;

public interface Command {
    void perform(TodoListDAO dao, PrintStream print, String[] args);

    String getName();
}
