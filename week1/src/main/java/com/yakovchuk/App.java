package com.yakovchuk;

import java.io.File;

public class App {

    public static final String MESSAGE_HELP = "expected input \"-[fh] [file name] [command] [command args]\"\n";
    public static final String MESSAGE_COMMAND_LIST = "command list:\n  list\n  add [text]\n  remove [task number]\n  remove all\n";
    public static final String MESSAGE_FAILURE_INVALID_INPUT = "failure: invalid input\n";
    public static final String OPTION_HELP = "-h";
    public static final String OPTION_FILE = "-f";

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                invalidInput();
            } else if (OPTION_HELP.equals(args[0])) {
                outputHelp();
                System.exit(0);
            } else if (OPTION_FILE.equals(args[0])) {
                String filename = args[1];
                if (filename == null) {
                    invalidInput();
                } else {
                    File file = new File(filename);
                    if (file.exists()) {

                    } else {
                        outputError("failure: file \"" + filename + "\" does not exist\n");
                    }
                }
            } else {
                invalidInput();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            invalidInput();
        }

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
