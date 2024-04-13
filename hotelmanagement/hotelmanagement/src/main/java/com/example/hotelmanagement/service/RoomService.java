package com.example.hotelmanagement.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.hotelmanagement.model.Room;
import com.example.hotelmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Create a new room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

     // Method to get paginated and sorted rooms
    public Page<Room> getPaginatedAndSortedRooms(int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return roomRepository.findAll(pageable);
    }

    // Get room by ID
    public Room getRoomById(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.orElse(null);
    }

    // Update room
    public Room updateRoom(Long id, Room newRoom) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room existingRoom = optionalRoom.get();
            existingRoom.setRoomNumber(newRoom.getRoomNumber());
            existingRoom.setRoomType(newRoom.getRoomType());
            existingRoom.setPrice(newRoom.getPrice());
            return roomRepository.save(existingRoom);
        } else {
            return null;
        }
    }

    // Delete room
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
