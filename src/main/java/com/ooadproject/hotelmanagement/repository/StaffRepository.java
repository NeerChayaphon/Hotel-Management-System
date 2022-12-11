package com.ooadproject.hotelmanagement.repository;

import com.ooadproject.hotelmanagement.model.Customer;
import com.ooadproject.hotelmanagement.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StaffRepository extends MongoRepository<Staff,String> {
    List<Staff> findByNameStartsWith(String name);
    List<Staff> findByEmail(String email);
    List<Staff> findByPosition(String position);
}
