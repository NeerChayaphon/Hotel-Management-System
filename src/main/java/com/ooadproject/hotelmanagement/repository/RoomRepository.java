package com.ooadproject.hotelmanagement.repository;

import com.ooadproject.hotelmanagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findByRoomTypeId(String roomTypeId);
    List<Room> findByRoomFloor(int roomFloor);
    List<Room> findByRoomBuilding(String roomBuilding);
}