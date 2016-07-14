package com.yakovchuk.command;

import com.yakovchuk.TodoListDAO;
import com.yakovchuk.command.Command;

import java.io.PrintStream;

public class RemoveAll implements Command {
    @Override
    public void perform(TodoListDAO dao, PrintStream print, String[] args) {
        dao.removeAll();
    }

    @Override
    public String getName() {
        return "remove-all";
    }
}
