package com.yakovchuk;

import com.yakovchuk.dao.FileTodoListDAO;
import com.yakovchuk.dao.TodoListDAO;

import java.io.PrintStream;
import java.util.List;

import static com.yakovchuk.UserOutputHelper.*;

class TodoListApp {

    private static final String OPTION_HELP = "-h";
    private static final String OPTION_FILE = "-f";

    void run(List<String> args) {
        PrintStream out = System.out;
        try {
            String option = args.get(0);
            if (OPTION_HELP.equals(option)) {
                outputHelp(out);
                return;
            } else if (OPTION_FILE.equals(option)) {
                String filename = args.get(1);
                TodoListDAO dao = new FileTodoListDAO(filename);

                String commandFromUser = args.get(2);
                try {
                    for (Command command : Command.values()) {
                        if (command.getName().equals(commandFromUser)) {
                            List<String> argumentsForCommand = args.subList(3, args.size());
                            command.perform(dao, out, argumentsForCommand);
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    invalidNumber(out, args.get(3));
                    return;
                } catch (RuntimeException e) {
                    invalidFile(out, filename);
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
