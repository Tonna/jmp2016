package com.yakovchuk;

import java.util.Arrays;
import java.util.List;

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
                        List<String> lines = dao.list();
                        for (int i = 0; i < lines.size(); i++) {
                            System.out.println(i + 1 + " " + lines.get(i));
                        }
                    } else if (COMMAND_ADD.equals(command)) {
                        String newLine = Util.join(Arrays.asList(args).subList(3, args.length), " ");
                        dao.add(newLine);
                    } else if (COMMAND_REMOVE.equals(command)) {
                        Integer taskNum = null;
                        try {
                            taskNum = Integer.decode(args[3]) - 1;
                        } catch (NumberFormatException e) {
                            outputError("failure: input \"" + args[3] + "\" is not a number");
                            System.exit(0);
                        }
                        dao.remove(taskNum);
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

}
