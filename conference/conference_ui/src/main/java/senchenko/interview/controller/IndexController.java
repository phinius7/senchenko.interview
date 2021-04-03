package senchenko.interview.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import senchenko.interview.entities.Room;
import senchenko.interview.entities.Schedule;
import senchenko.interview.entities.views.ScheduleView;
import senchenko.interview.services.RoomService;
import senchenko.interview.services.ScheduleService;

import java.util.List;

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

    @GetMapping(value = "/rooms-rest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Room> sendRoomsRestForm() {
        return roomService.findAllRooms();
    }

    @GetMapping(value = "/schedules-rest", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({ScheduleView.ScheduleInfo.class})
    @ResponseBody
    public List<Schedule> sendScheduleRestForm() {
        return scheduleService.findAllSchedules();
    }
}
