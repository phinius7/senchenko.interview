package senchenko.interview.services;

import senchenko.interview.entities.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<Room> findAllRooms();

    Optional<Room> findRoomById(Long id);
}
