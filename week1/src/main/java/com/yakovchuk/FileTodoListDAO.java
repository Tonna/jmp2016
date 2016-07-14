package com.yakovchuk;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class FileTodoListDAO implements TodoListDAO {
    private final String filename;

    public FileTodoListDAO(String filename) {
        this.filename = filename;
    }

    @Override
    public List<String> list() {
        try {
            return Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String task) {
        try {
            Files.write(Paths.get(filename), Arrays.asList(task), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int taskNum) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            if ((taskNum < 0) || (taskNum > (lines.size() - 1))) {
                return;
            }
            lines.remove(taskNum);
            Files.write(Paths.get(filename), lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeAll() {
        try {
            Files.write(Paths.get(filename), Collections.<CharSequence>emptyList(), StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
