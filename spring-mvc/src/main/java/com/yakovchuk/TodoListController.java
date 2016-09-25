package com.yakovchuk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoListController {

    private static final String TODO_LIST_IN_SESSION = "todoList";

    @RequestMapping("/list")
    public String listTasks(HttpSession session, Model model) {
        model.addAttribute("todoList", session.getAttribute (TODO_LIST_IN_SESSION));
        return "listOfTasks";
    }

    @RequestMapping("/addTaskForm")
    public String addTaskForm() {
        return "addNewTask";
    }

    @RequestMapping("/addTask")
    public String addTask(HttpSession session, Model model, final String
            taskText) {
        List<String> list = (List<String>) session.getAttribute(TODO_LIST_IN_SESSION);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(taskText);
        session.setAttribute(TODO_LIST_IN_SESSION, list);
        return "redirect:list";
    }
}