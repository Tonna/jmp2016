package com.yakovchuk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoListController {

    @RequestMapping("/list")
    public String listTasks(Model model) {
        model.addAttribute("task", "Buy coffee");
        return "listOfTasks";
    }
}