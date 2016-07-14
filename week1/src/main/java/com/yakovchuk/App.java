package com.yakovchuk;

import com.yakovchuk.dao.FileTodoListDAO;
import com.yakovchuk.dao.TodoListDAO;

import java.util.Arrays;

public class App {

    private static final String MESSAGE_HELP = "expected input \"-[fh] [file name] [command] [command args]\"";
    private static final String MESSAGE_COMMAND_LIST = "command list:\n  list\n  add [text]\n  remove [task number]\n  remove all";
    private static final String MESSAGE_FAILURE_INVALID_INPUT = "failure: invalid input";
    private static final String OPTION_HELP = "-h";
    private static final String OPTION_FILE = "-f";
    public static final String MESSAGE_FAILURE_INVALID_FILE = "failure: invalid file \"%s\"";

    public static void main(String[] args) {
        try {
            if (OPTION_HELP.equals(args[0])) {
                outputHelp();
            } else if (OPTION_FILE.equals(args[0])) {
                String filename = args[1];
                String commandFromUser = args[2];
                TodoListDAO dao = new FileTodoListDAO(filename);
                try {
                    for (Command command : Command.values()) {
                        if (command.getName().equals(commandFromUser)) {
                            command.perform(dao, System.out, Arrays.copyOfRange(args, 3, args.length));
                            return;
                        }
                    }
                    invalidInput();
                    outputHelp();
                } catch (RuntimeException e) {
                    invalidFile(filename);
                }
            } else {
                invalidInput();
                outputHelp();
            }
        } catch (IndexOutOfBoundsException e) {
            invalidInput();
            outputHelp();
        }
    }

    private static void invalidFile(String filename) {
        System.out.printf(MESSAGE_FAILURE_INVALID_FILE, filename);
    }

    private static void invalidInput() {
        outputError(MESSAGE_FAILURE_INVALID_INPUT);
    }

    private static void outputError(String message) {
        System.out.println(message);
    }

    private static void outputHelp() {
        System.out.println(MESSAGE_HELP);
        System.out.println(MESSAGE_COMMAND_LIST);
    }

}
