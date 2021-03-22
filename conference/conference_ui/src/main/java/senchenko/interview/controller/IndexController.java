package senchenko.interview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import senchenko.interview.services.RoomService;
import senchenko.interview.services.ScheduleService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final ScheduleService scheduleService;
    private final RoomService roomService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAdminStartPage(Model model) {
        model.addAttribute("schedules", scheduleService.findAllSchedules());
        model.addAttribute("rooms", roomService.findAllRooms());
        return "index";
    }

    @GetMapping("inside_conference")
    public String showConferencePlug() {
        return "inside_conference";
    }

}
