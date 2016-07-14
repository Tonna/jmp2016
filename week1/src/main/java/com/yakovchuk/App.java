package com.yakovchuk;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App {

    public static final String MESSAGE_HELP = "expected input \"-[fh] [file name] [command] [command args]\"\n";
    public static final String MESSAGE_COMMAND_LIST = "command list:\n  list\n  add [text]\n  remove [task number]\n  remove all\n";
    public static final String MESSAGE_FAILURE_INVALID_INPUT = "failure: invalid input\n";
    public static final String OPTION_HELP = "-h";
    public static final String OPTION_FILE = "-f";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_ADD = "add";
    public static final String COMMAD_REMOVE = "remove";
    public static final String COMMAND_REMOVE_ALL = "remove-all";

    public static void main(String[] args) {
        List<String> programArguments = Arrays.asList(args);
        try {
            if (programArguments.size() == 0) {
                invalidInput();
            } else if (OPTION_HELP.equals(programArguments.get(0))) {
                outputHelp();
                System.exit(0);
            } else if (OPTION_FILE.equals(programArguments.get(0))) {
                String filename = programArguments.get(1);
                TodoListDAO dao = new FileTodoListDAO(filename);
                String command = programArguments.get(2);
                try {
                    if (COMMAND_LIST.equals(command)) {
                        List<String> lines = dao.list();
                        for (int i = 0; i < lines.size(); i++) {
                            System.out.print(i + 1 + " " + lines.get(i) + "\n");
                        }
                    } else if (COMMAND_ADD.equals(command)) {
                        String newLine = join(Arrays.asList(args).subList(3, args.length), " ");
                        dao.add(newLine);
                    } else if (COMMAD_REMOVE.equals(command)) {
                        Integer taskNum = null;
                        try {
                            taskNum = Integer.decode(args[3]) - 1;
                        } catch (NumberFormatException e) {
                            outputError("failure: input \"" + args[3] + "\" is not a number\n");
                            System.exit(0);
                        }
                        dao.remove(taskNum);
                    } else if (COMMAND_REMOVE_ALL.equals(command)) {
                        dao.removeAll();
                    } else {
                        invalidInput();
                    }
                } catch (Exception e) {
                    outputError("failure: invalid file \"" + filename + "\"\n");
                }
            } else {
                invalidInput();
            }
        } catch (IndexOutOfBoundsException e) {
            invalidInput();
        }
    }

    private static String join(List<String> strings, String separator) {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            if (iterator.hasNext()) {
                buffer.append(separator);
            }
        }
        return buffer.toString();
    }

    private static void invalidInput() {
        outputError(MESSAGE_FAILURE_INVALID_INPUT);
        outputHelp();
        System.exit(0);
    }

    private static void outputError(String message) {
        System.out.print(message);
    }

    private static void outputHelp() {
        System.out.print(MESSAGE_HELP);
        System.out.print(MESSAGE_COMMAND_LIST);
    }

}
