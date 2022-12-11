package com.ooadproject.hotelmanagement.repository;

import com.ooadproject.hotelmanagement.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    List<Customer> findByNameStartsWith(String name);
    List<Customer> findByEmail(String email);
}
