package com.ooadproject.hotelmanagement.repository;

import com.ooadproject.hotelmanagement.model.RoomType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoomTypeRepository extends MongoRepository<RoomType, String> {
    List<RoomType> findByBedroom(int bedroom);
    List<RoomType> findByBedAmount(int bedAmount);
    List<RoomType> findByMaxGuest(int maxGuest);
    List<RoomType> findByRoomPrice(int roomPrice);
}
