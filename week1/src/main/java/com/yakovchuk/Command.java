package com.yakovchuk;

import com.yakovchuk.dao.TodoListDAO;

import java.io.PrintStream;
import java.util.List;

enum Command {
    LIST("list") {
        @Override
        void perform(TodoListDAO dao, PrintStream print, List<String> args) {
            java.util.List<String> list = dao.list();
            for (int i = 0; i < list.size(); i++) {
                print.println((i + 1) + " " + list.get(i));
            }
        }
    }, ADD("add") {
        @Override
        void perform(TodoListDAO dao, PrintStream print, List<String> args) {
            String newLine = Util.join(args, " ");
            dao.add(newLine);
        }
    }, REMOVE("remove") {
        @Override
        void perform(TodoListDAO dao, PrintStream print, List<String> args) {
            Integer taskNum;
            try {
                taskNum = Integer.decode(args.get(0)) - 1;
            } catch (NumberFormatException e) {
                print.println("failure: input \"" + args.get(0) + "\" is not a number");
                return;
            }
            dao.remove(taskNum);
        }
    }, REMOVE_ALL("remove-all") {
        @Override
        void perform(TodoListDAO dao, PrintStream print, List<String> args) {
            dao.removeAll();
        }
    };

    private final String name;

    Command(String name) {
        this.name = name;
    }

    abstract void perform(TodoListDAO dao, PrintStream print, List<String> args);

    String getName() {
        return name;
    }
}
