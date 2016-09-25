package com.yakovchuk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoListController {

    @RequestMapping("/list")
    public String listTasks(HttpSession session, Model model) {
        model.addAttribute("todoList", session.getAttribute("todoList"));
        return "listOfTasks";
    }
}