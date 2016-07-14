package com.yakovchuk;

import java.io.PrintStream;

public class UserOutputHelper {

    private static final String MESSAGE_HELP = "expected input \"-[fh] [file name] [command] [command args]\"";
    private static final String MESSAGE_COMMAND_LIST = "command list:\n  list\n  add [text]\n  remove [task number]\n  remove all";
    private static final String MESSAGE_FAILURE_INVALID_INPUT = "failure: invalid input";
    private static final String MESSAGE_FAILURE_INVALID_FILE = "failure: invalid file \"%s\"";

    static void invalidFile(PrintStream out, String filename) {
        out.printf(MESSAGE_FAILURE_INVALID_FILE, filename);
    }

    static void invalidInput(PrintStream out) {
        out.println(MESSAGE_FAILURE_INVALID_INPUT);
    }

    static void outputHelp(PrintStream out) {
        out.println(MESSAGE_HELP);
        out.println(MESSAGE_COMMAND_LIST);
    }
}
