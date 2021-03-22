package senchenko.interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senchenko.interview.entities.Presentation;
import senchenko.interview.entities.Room;
import senchenko.interview.entities.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findScheduleByRoom(Room room);

    Schedule findOneScheduleByPresentation(Presentation presentation);

    List<Schedule> findScheduleByPresentation(Presentation presentation);
}
