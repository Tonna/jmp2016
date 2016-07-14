package com.yakovchuk;

import com.yakovchuk.dao.TodoListDAO;

import java.io.PrintStream;
import java.util.Arrays;

enum Command {
    LIST("list") {
        @Override
        void perform(TodoListDAO dao, PrintStream print, String[] args) {
            java.util.List<String> list = dao.list();
            for (int i = 0; i < list.size(); i++) {
                print.println((i + 1) + " " + list.get(i));
            }
        }
    }, ADD("add") {
        @Override
        void perform(TodoListDAO dao, PrintStream print, String[] args) {
            String newLine = Util.join(Arrays.asList(args), " ");
            dao.add(newLine);
        }
    }, REMOVE("remove") {
        @Override
        void perform(TodoListDAO dao, PrintStream print, String[] args) {
            Integer taskNum = null;
            try {
                taskNum = Integer.decode(args[0]) - 1;
            } catch (NumberFormatException e) {
                print.println("failure: input \"" + args[0] + "\" is not a number");
                return;
            }
            dao.remove(taskNum);
        }
    }, REMOVE_ALL("remove-all") {
        @Override
        void perform(TodoListDAO dao, PrintStream print, String[] args) {
            dao.removeAll();
        }
    };

    private String name;

    Command(String name) {
        this.name = name;
    }

    abstract void perform(TodoListDAO dao, PrintStream print, String[] args);

    String getName() {
        return name;
    }
}
