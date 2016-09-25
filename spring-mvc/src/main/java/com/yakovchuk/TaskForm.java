package com.yakovchuk;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskForm {

    @NotNull
    @Size(min = 10, max = 100)
    private String taskText;

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "taskText='" + taskText + '\'' +
                '}';
    }
}
