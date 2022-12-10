package com.ooadproject.hotelmanagement.repository;

import com.ooadproject.hotelmanagement.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
