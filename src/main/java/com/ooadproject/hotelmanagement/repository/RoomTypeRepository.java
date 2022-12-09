package com.ooadproject.hotelmanagement.repository;

import com.ooadproject.hotelmanagement.model.RoomType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoomTypeRepository extends MongoRepository<RoomType, String> {

    List<RoomType> findByBedroom(int bedroom);

    @Query("{'maxGuest' : { $lte : ?0 }}")
    List<RoomType> getAllRoomTypeNotOverMaxGuest(int maxGuest);

    List<RoomType> findByBedAmount(int bedAmount);
}
