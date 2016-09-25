package com.yakovchuk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoListController {

    private static final String TODO_LIST_IN_SESSION = "todoList";

    @RequestMapping("/list")
    public String listTasks(HttpSession session, Model model) {
        model.addAttribute("todoList", getTodoList(session));
        return "listOfTasks";
    }

    @RequestMapping("/addTaskForm")
    public String addTaskForm() {
        return "addNewTask";
    }

    @RequestMapping("/addTask")
    public String addTask(HttpSession session, Model model,
                          final String taskText) {
        List<String> list =
                getTodoList(session);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(taskText);
        session.setAttribute(TODO_LIST_IN_SESSION, list);
        return "redirect:list";
    }

    private List<String> getTodoList(HttpSession session) {
        List<String> list = (List<String>) session.getAttribute(TODO_LIST_IN_SESSION);
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @RequestMapping("/deleteTask")
    public String deleteTask(HttpSession session,
                             @RequestParam("id") int id) {
        List<String> todoList = getTodoList(session);
        todoList.remove(id);
        return "redirect:list";
    }

    @RequestMapping("/editTaskForm")
    public String editTaskForm(HttpSession session,
                           @RequestParam("id") int id, Model model) {
        List<String> todoList = getTodoList(session);
        model.addAttribute("taskText", todoList.get(id));
        return "editTask";
    }

    @RequestMapping("/editTask")
    public String editTask(HttpSession session,
                           @RequestParam("id") int id,
                           @RequestParam("taskText") String taskText) {
        List<String> todoList = getTodoList(session);
        todoList.set(id,taskText);
        return "redirect:list";
    }
}