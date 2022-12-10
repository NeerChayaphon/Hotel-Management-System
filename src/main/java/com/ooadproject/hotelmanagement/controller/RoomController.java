package com.ooadproject.hotelmanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.hotelmanagement.model.Room;
import com.ooadproject.hotelmanagement.model.RoomType;
import com.ooadproject.hotelmanagement.repository.RoomRepository;
import com.ooadproject.hotelmanagement.repository.RoomTypeRepository;

@RestController
@RequestMapping("/room")
public class RoomController {

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private RoomTypeRepository roomTypeRepository;
  private Optional<RoomType> roomType;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Room createRoom(@RequestBody Room room) {
    room.setRoomId(UUID.randomUUID().toString().split("-")[0]);

    roomType = roomTypeRepository.findById(room.getRoomTypeId());

    if (roomType.isPresent() && roomType != null) {
      return roomRepository.save(room);
    } else {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "room type not found");
    }
  }

  @GetMapping
  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  @GetMapping("/{rooomId}")
  public Room getRoom(@PathVariable String roomId) {
    return roomRepository.findById(roomId).get();
  }

  // @PutMapping("/{roomId}")
  // public Room modifyRoom(@RequestBody Room roomRequest, @PathVariable String
  // roomId) {
  // Room existedRoom = roomRepository.findById(roomId).get();
  // existedRoom.setRoom
  // }
}
