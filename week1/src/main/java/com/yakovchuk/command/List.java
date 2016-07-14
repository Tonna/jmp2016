package com.yakovchuk.command;

import com.yakovchuk.TodoListDAO;
import com.yakovchuk.command.Command;

import java.io.PrintStream;

public class List implements Command {

    @Override
    public void perform(TodoListDAO dao, PrintStream print, String[] args) {
        java.util.List<String> list = dao.list();
        for (int i = 0; i < list.size(); i++) {
            print.println((i + 1) + " " + list.get(i));
        }
    }

    @Override
    public String getName() {
        return "list";
    }
}
