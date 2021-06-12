package senchenko.interview.services;

import senchenko.interview.entities.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    void saveSchedule(Schedule schedule);

    List<Schedule> findAllSchedules();

    Optional<Schedule> findScheduleById(Long id);

    void deleteScheduleById(Long id);
}
