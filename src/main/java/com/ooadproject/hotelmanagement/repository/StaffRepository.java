package com.ooadproject.hotelmanagement.repository;

import com.ooadproject.hotelmanagement.model.Customer;
import com.ooadproject.hotelmanagement.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StaffRepository extends MongoRepository<Staff,String> {
}
