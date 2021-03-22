package senchenko.interview.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senchenko.interview.entities.Presentation;
import senchenko.interview.entities.Room;
import senchenko.interview.entities.Schedule;
import senchenko.interview.repositories.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> getSchedulesByRoom(Room room) {
        return scheduleRepository.findScheduleByRoom(room);
    }

    public Schedule getOneScheduleByPresentation(Presentation presentation) {
        return scheduleRepository.findOneScheduleByPresentation(presentation);
    }

    public void deleteScheduleByPresentation(Presentation presentation) {
        List<Schedule> schedule = scheduleRepository.findScheduleByPresentation(presentation);
        for (Schedule s : schedule) {
            scheduleRepository.delete(s);
        }
    }

}
