package senchenko.interview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import senchenko.interview.entities.User;
import senchenko.interview.services.RoleServiceImpl;
import senchenko.interview.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

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

    @GetMapping("/rest")
    @ResponseBody
    public List<User> sendUsersRestForm() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User sendOneUser(@PathVariable Long id) {
        return userService.findUserById(id).get();
    }

    @PostMapping("create-rest")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        user.setId(null);
        return userService.saveOrUpdate(user);
    }

    @PutMapping("/put-rest/{id}")
    @ResponseBody
    public User putUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("/delete-rest/{id}")
    @ResponseBody
    public void deleteRestById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
