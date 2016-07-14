package com.yakovchuk;

import com.yakovchuk.command.*;

import java.util.ArrayList;

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
        java.util.List<Command> commands = new ArrayList<Command>();
        commands.add(new List());
        commands.add(new Add());
        commands.add(new Remove());
        commands.add(new RemoveAll());
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
                    for (Command command1 : commands) {
                        if (command1.getName().equals(command)) {
                            command1.perform(dao, System.out, args);
                            return;
                        }
                    }
                    invalidInput();
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

}
