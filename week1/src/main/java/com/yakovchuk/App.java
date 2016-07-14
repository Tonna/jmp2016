package com.yakovchuk;

import java.io.PrintStream;
import java.util.Arrays;

public class App {

    private static final String MESSAGE_HELP = "expected input \"-[fh] [file name] [command] [command args]\"";
    private static final String MESSAGE_COMMAND_LIST = "command list:\n  list\n  add [text]\n  remove [task number]\n  remove all";
    private static final String MESSAGE_FAILURE_INVALID_INPUT = "failure: invalid input";
    private static final String OPTION_HELP = "-h";
    private static final String OPTION_FILE = "-f";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_REMOVE = "remove";
    private static final String COMMAND_REMOVE_ALL = "remove-all";

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                invalidInput();
            } else if (OPTION_HELP.equals(args[0])) {
                outputHelp();
                System.exit(0);
            } else if (OPTION_FILE.equals(args[0])) {
                String filename = args[1];
                TodoListDAO dao = new FileTodoListDAO(filename);
                String command = args[2];
                try {
                    if (COMMAND_LIST.equals(command)) {
                        new List().perform(dao, System.out, args);
                    } else if (COMMAND_ADD.equals(command)) {
                        new Add().perform(dao, System.out, args);
                    } else if (COMMAND_REMOVE.equals(command)) {
                        new Remove().perform(dao, System.out,args);
                    } else if (COMMAND_REMOVE_ALL.equals(command)) {
                        dao.removeAll();
                    } else {
                        invalidInput();
                    }
                } catch (Exception e) {
                    outputError("failure: invalid file \"" + filename + "\"");
                }
            } else {
                invalidInput();
            }
        } catch (IndexOutOfBoundsException e) {
            invalidInput();
        }
    }

    private static void invalidInput() {
        outputError(MESSAGE_FAILURE_INVALID_INPUT);
        outputHelp();
        System.exit(0);
    }

    private static void outputError(String message) {
        System.out.println(message);
    }

    private static void outputHelp() {
        System.out.println(MESSAGE_HELP);
        System.out.println(MESSAGE_COMMAND_LIST);
    }

    interface Command {
        void perform(TodoListDAO dao, PrintStream print, String[] args);

        String getName();
    }

    static class List implements Command {

        @Override
        public void perform(TodoListDAO dao, PrintStream print, String[] args) {
            java.util.List<String> list = dao.list();
            for (int i = 0; i < list.size(); i++) {
                print.println((i + 1) + " " + list.get(i));
            }
        }

        @Override
        public String getName() {
            return null;
        }
    }

    static class Add implements Command {

        @Override
        public void perform(TodoListDAO dao, PrintStream print, String[] args) {
            String newLine = Util.join(Arrays.asList(args).subList(3, args.length), " ");
            dao.add(newLine);
        }

        @Override
        public String getName() {
            return null;
        }
    }

    static class Remove implements Command {
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
            return null;
        }
    }
}
