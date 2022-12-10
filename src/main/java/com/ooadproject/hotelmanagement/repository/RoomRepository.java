package com.ooadproject.hotelmanagement.repository;

import com.ooadproject.hotelmanagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {

}