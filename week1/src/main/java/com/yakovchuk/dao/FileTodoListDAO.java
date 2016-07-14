package com.yakovchuk.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class FileTodoListDAO implements TodoListDAO {
    private final Path path;

    public FileTodoListDAO(String filename) {
        path = Paths.get(filename);
    }

    @Override
    public List<String> list() {
        try {
            return Files.readAllLines(path, UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String task) {
        try {
            Files.write(path, Collections.singletonList(task), UTF_8, APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int taskNum) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path, UTF_8);
            if ((taskNum < 0) || (taskNum > (lines.size() - 1))) {
                return;
            }
            lines.remove(taskNum);
            Files.write(path, lines, UTF_8, TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeAll() {
        try {
            Files.write(path, Collections.<CharSequence>emptyList(), UTF_8, TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
