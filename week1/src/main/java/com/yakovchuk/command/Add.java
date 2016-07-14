package com.yakovchuk.command;

import com.yakovchuk.TodoListDAO;
import com.yakovchuk.Util;

import java.io.PrintStream;
import java.util.Arrays;

public class Add implements Command {

    @Override
    public void perform(TodoListDAO dao, PrintStream print, String[] args) {
        String newLine = Util.join(Arrays.asList(args).subList(3, args.length), " ");
        dao.add(newLine);
    }

    @Override
    public String getName() {
        return "add";
    }
}
