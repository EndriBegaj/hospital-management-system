package dev.endribegaj.hospitalmanagementsystem.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "page/index";
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "index";
    }

}
