package com.yakovchuk;

public class App {

    private static final String MESSAGE_HELP = "expected input \"-[fh] [file name] [command] [command args]\"";
    private static final String MESSAGE_COMMAND_LIST = "command list:\n  list\n  add [text]\n  remove [task number]\n  remove all";
    private static final String MESSAGE_FAILURE_INVALID_INPUT = "failure: invalid input";
    private static final String OPTION_HELP = "-h";
    private static final String OPTION_FILE = "-f";

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                invalidInput();
            } else if (OPTION_HELP.equals(args[0])) {
                outputHelp();
                System.exit(0);
            } else if (OPTION_FILE.equals(args[0])) {
                String filename = args[1];
                String commandFromUser = args[2];
                TodoListDAO dao = new FileTodoListDAO(filename);

                try {
                    for (Command command : Command.values()) {
                        if (command.getName().equals(commandFromUser)) {
                            command.perform(dao, System.out, args);
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
