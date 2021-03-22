package senchenko.interview.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senchenko.interview.entities.Room;
import senchenko.interview.entities.Schedule;
import senchenko.interview.entities.views.ScheduleView;
import senchenko.interview.services.RoomService;
import senchenko.interview.services.ScheduleServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule/api/v1")
public class ScheduleRestController {

    ScheduleServiceImpl scheduleService;
    RoomService roomService;

    @Autowired
    public ScheduleRestController(ScheduleServiceImpl scheduleService, RoomService roomService) {
        this.scheduleService = scheduleService;
        this.roomService = roomService;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @JsonView(ScheduleView.ScheduleInfo.class)
    public Map<Room, List<Schedule>> scheduleInRoomToXml() {
        return getMap();
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(ScheduleView.ScheduleInfo.class)
    public Map<Room, List<Schedule>> scheduleInRoomToJson() {
        return getMap();
    }

    private Map<Room, List<Schedule>> getMap() {
        Map<Room, List<Schedule>> roomsSchedulesMap = new HashMap<>();
        List<Room> rooms = roomService.findAllRooms();
        for (Room r : rooms) {
            assert false;
            roomsSchedulesMap.put(r, scheduleService.getSchedulesByRoom(r));
        }
        return roomsSchedulesMap;
    }

}
