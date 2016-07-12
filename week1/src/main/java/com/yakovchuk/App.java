package com.yakovchuk;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

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
                        if ("list".equals(args[2])) {
                            List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
                            for (int i = 0; i < lines.size(); i++) {
                                System.out.print(i + 1 + " " + lines.get(i) + "\n");
                            }
                        } else if ("add".equals(args[2])) {
                            List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
                            String newLine = join(Arrays.asList(args).subList(3, args.length), " ");
                            lines.add(newLine);
                            try (PropertiesModifier pm = new PropertiesModifier("line.separator", "\n")) {
                                Files.write(Paths.get(filename), lines, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
                            }
                        } else if ("remove".equals(args[2])) {
                            List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
                            Integer lineNumber = null;
                            try {
                                lineNumber = Integer.decode(args[3]) - 1;
                            } catch (NumberFormatException e) {
                                outputError("Gxfhf");
                                System.exit(0);
                            }
                            if ((lineNumber < 0) || (lineNumber > (lines.size() - 1))) {
                                return;
                            }
                            List<String> newLines = new ArrayList<>(lines);
                            newLines.remove(lineNumber.intValue());

                            Files.delete(Paths.get(filename));
                            try (PropertiesModifier pm = new PropertiesModifier("line.separator", "\n")) {
                                Files.write(Paths.get(filename), newLines, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
                            }
                        } else if ("remove-all".equals(args[2])) {
                             try (PropertiesModifier pm = new PropertiesModifier("line.separator", "\n")) {
                                Files.write(Paths.get(filename),Collections.<CharSequence>emptyList(), StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
                            }
                        }
                    } else {
                        outputError("failure: file \"" + filename + "\" does not exist\n");
                    }
                }
            } else {
                invalidInput();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            invalidInput();
        } catch (IOException e) {
            outputError("something wrong happened during opening file!!!\n");
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

/**
 * Class which enables temporary modifications to the System properties,
 * via an AutoCloseable.  Wrap the behavior that needs your modification
 * in a try-with-resources block in order to have your properties
 * apply only to code within that block.  Generally, alternatives
 * such as explicitly passing in the value you need, rather than pulling
 * it from System.getProperties(), should be preferred to using this class.
 */
class PropertiesModifier implements AutoCloseable {
    private final String original;

    public PropertiesModifier(String key, String value) {
        StringWriter sw = new StringWriter();
        try {
            System.getProperties().store(sw, "");
        } catch (IOException e) {
            e.printStackTrace(); // Shouldn't be possible with StringWriter
        }
        original = sw.toString();
        System.setProperty(key, value);
    }

    @Override
    public void close() {
        Properties set = new Properties();
        try {
            set.load(new StringReader(original));
        } catch (IOException e) {
            e.printStackTrace(); // Shouldn't be possible with StringReader
        }
        System.setProperties(set);
    }
}
