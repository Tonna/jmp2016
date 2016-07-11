package com.yakovchuk;

public class App {

    public static final String MESSAGE_HELP = "expected input \"-[fh] [file name] [command] [all other]\"\n";
    public static final String MESSAGE_FAILURE_INVALID_INPUT = "failure: invalid input\n";
    public static final String OPTION_HELP = "-h";

    public static void main(String [] args){
        String option = args[0];
        if ((option == null) || !(OPTION_HELP.equals(option))) {
            outputError(MESSAGE_FAILURE_INVALID_INPUT);
            outputHelp();
            System.exit(0);
        }
        outputHelp();
    }

    private static void outputError(String message) {
        System.out.print(message);
    }

    private static void outputHelp() {
        System.out.print(MESSAGE_HELP);
    }

}
