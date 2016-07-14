package com.yakovchuk.command;

import com.yakovchuk.TodoListDAO;
import com.yakovchuk.command.Command;

import java.io.PrintStream;

public class Remove implements Command {
    @Override
    public void perform(TodoListDAO dao, PrintStream print, String[] args) {

        Integer taskNum = null;
        try {
            taskNum = Integer.decode(args[3]) - 1;
        } catch (NumberFormatException e) {
            print.println("failure: input \"" + args[3] + "\" is not a number");
            return;
        }

        dao.remove(taskNum);
    }

    @Override
    public String getName() {
        return "remove";
    }
}
