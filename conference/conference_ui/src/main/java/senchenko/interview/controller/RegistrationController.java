package senchenko.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import senchenko.interview.entities.Role;
import senchenko.interview.entities.User;
import senchenko.interview.services.RoleServiceImpl;
import senchenko.interview.services.UserService;
import senchenko.interview.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserServiceImpl userService, RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerNewListener(Model model) {
        model.addAttribute("user", new User());
        return "check-in";
    }

    @PostMapping
    public String registerNewListener(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "check-in";
        }
        Collection<Role> roles = user.getRoles();
        roles.add(roleService.findRoleById(roleService.getListenerId()).get());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        userService.authenticateUser(user);
        return "redirect:/";
    }
}
