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
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("pageOwner", (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return "admin";
    }

    @GetMapping("admin/{id}")
    public User userById(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);

        return user;
    }

    @GetMapping("admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageOwner", (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/edit")
    public void editPage(@PathVariable("id") long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
    }

    @PatchMapping("admin/{id}")
    public String edit(@ModelAttribute("user") User user) {
        userService.editUser(user);

        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/delete")
    public String delete(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        userService.deleteUser(id);

        return "redirect:/admin";
    }

    @GetMapping("/")
    public String startPage() {

        return "redirect:/login";
    }
}
