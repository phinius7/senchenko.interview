package senchenko.interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senchenko.interview.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
