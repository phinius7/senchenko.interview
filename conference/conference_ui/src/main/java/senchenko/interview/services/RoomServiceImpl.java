package senchenko.interview.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senchenko.interview.entities.Room;
import senchenko.interview.repositories.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findRoomById(Long id) {
        return roomRepository.findById(id);
    }
}
