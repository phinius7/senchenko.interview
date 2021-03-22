package senchenko.interview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import senchenko.interview.entities.Presentation;
import senchenko.interview.entities.Schedule;
import senchenko.interview.entities.User;
import senchenko.interview.services.PresentationServiceImpl;
import senchenko.interview.services.RoomServiceImpl;
import senchenko.interview.services.ScheduleServiceImpl;
import senchenko.interview.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationServiceImpl presentationService;
    private final ScheduleServiceImpl scheduleService;
    private final UserServiceImpl userService;
    private final RoomServiceImpl roomService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllPresentation(Model model) {
        List<Presentation> presentationList;
        presentationList = presentationService.findAllPresentations();
        model.addAttribute("presentation", presentationList);
        return "lectures";
    }

    @Secured({"ROLE_ADMIN", "ROLE_PRESENTER"})
    @GetMapping("/add")
    public String showAddPresentationForm(Model model) {
        model.addAttribute("presentation", new Presentation());
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("rooms", roomService.findAllRooms());
        return "add_lecture";
    }

    @Secured({"ROLE_ADMIN", "ROLE_PRESENTER"})
    @PostMapping("/add")
    public String showPresentationAddForm(@Valid @ModelAttribute Presentation presentation,
                                          @Valid @ModelAttribute Schedule schedule,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_lecture";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User author = userService.getByUsername(username);
        List<User> authorList = new ArrayList<>();
        authorList.add(author);
        presentation.setAuthors(authorList);
        presentationService.savePresentation(presentation);
        schedule.setPresentation(presentation);
        scheduleService.saveSchedule(schedule);
        return "redirect:/lectures";
    }

    @Secured({"ROLE_ADMIN", "ROLE_PRESENTER"})
    @GetMapping("/edit/{id}")
    public String showEditPresentationForm(@PathVariable Long id, Model model) {
        scheduleService.deleteScheduleByPresentation(presentationService.findPresentationById(id).get());
        Presentation presentation = presentationService.findPresentationById(id).get();
        model.addAttribute("presentation", presentation);
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("rooms", roomService.findAllRooms());
        return "edit_lecture";
    }

    @Secured({"ROLE_ADMIN", "ROLE_PRESENTER"})
    @PostMapping("/edit")
    public String showEditPresentationForm(@Valid @ModelAttribute Presentation presentation,
                                           @Valid @ModelAttribute Schedule schedule,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_lecture";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User author = userService.getByUsername(username);
        List<User> authorList = presentation.getAuthors();
        if (!authorList.contains(author)) {
            authorList.add(author);
        }
        presentation.setAuthors(authorList);
        presentationService.savePresentation(presentation);
        schedule.setPresentation(presentation);
        scheduleService.saveSchedule(schedule);
        return "redirect:/lectures";
    }

    @Secured({"ROLE_ADMIN", "ROLE_PRESENTER"})
    @GetMapping("/delete/{id}")
    public String deletePresentationById(@PathVariable Long id) {
        scheduleService.deleteScheduleByPresentation(presentationService.findPresentationById(id).get());
        presentationService.deletePresentationById(id);
        return "redirect:/lectures";
    }
}
