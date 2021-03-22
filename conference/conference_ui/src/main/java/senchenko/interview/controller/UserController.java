package senchenko.interview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import senchenko.interview.entities.User;
import senchenko.interview.services.RoleService;
import senchenko.interview.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAllRoles());
        return "add_user";
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/add")
    public String showAddUserForm(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_user";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id).get());
        model.addAttribute("roles", roleService.findAllRoles());
        return "edit_user";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/edit")
    public String showEditUserForm(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
