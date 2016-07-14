package com.yakovchuk;

import com.yakovchuk.dao.FileTodoListDAO;
import com.yakovchuk.dao.TodoListDAO;

import java.io.PrintStream;
import java.util.Arrays;

import static com.yakovchuk.UserOutputHelper.invalidFile;
import static com.yakovchuk.UserOutputHelper.invalidInput;
import static com.yakovchuk.UserOutputHelper.outputHelp;

public class TodoListApp {

    private static final String OPTION_HELP = "-h";
    private static final String OPTION_FILE = "-f";

    void run(String[] args) {
        PrintStream out = System.out;
        try {
            if (OPTION_HELP.equals(args[0])) {
                outputHelp(out);
                return;
            } else if (OPTION_FILE.equals(args[0])) {
                String filename = args[1];
                String commandFromUser = args[2];
                TodoListDAO dao = new FileTodoListDAO(filename);
                try {
                    for (Command command : Command.values()) {
                        if (command.getName().equals(commandFromUser)) {
                            command.perform(dao, out, Arrays.asList(args).subList(3, args.length));
                            return;
                        }
                    }
                } catch (RuntimeException e) {
                    invalidFile(out,filename);
                    return;
                }
            }
            invalidInput(out);
            outputHelp(out);
        } catch (IndexOutOfBoundsException e) {
            invalidInput(out);
            outputHelp(out);
        }
    }


}
