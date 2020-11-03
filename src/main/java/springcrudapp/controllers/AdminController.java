package springcrudapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springcrudapp.model.User;
import springcrudapp.service.UserDetailsServiceImpl;

@Controller
@RequestMapping("/")
public class AdminController {
    private UserDetailsServiceImpl userService;

    @Autowired
    public AdminController(UserDetailsServiceImpl userService) {this.userService = userService;}

    @GetMapping("/admin")
    public String allUsers(Model model) {
        model.addAttribute("pageOwner", (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return "admin";
    }

    @GetMapping("")
    public String startPage() {

        return "redirect:/login";
    }
}
