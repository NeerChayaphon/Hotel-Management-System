package com.ooadproject.hotelmanagement.controller;
import com.ooadproject.hotelmanagement.model.Booking;
import com.ooadproject.hotelmanagement.model.Customer;
import com.ooadproject.hotelmanagement.repository.BookingRepository;
import com.ooadproject.hotelmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private Optional<Customer> customer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody Booking booking){
        booking.setBookingId(UUID.randomUUID().toString().split("-")[0]);

        customer = customerRepository.findById(booking.getCustomerId());
        if (customer != null && customer.isPresent()) {
            return bookingRepository.save(booking);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

     @GetMapping
    public List<Booking> getAllRoomTypes() {
        return bookingRepository.findAll();
    }
    
    @GetMapping("/{bookingId}")
    public Booking getTask(@PathVariable String bookingId){
        return bookingRepository.findById(bookingId).get();
    }
    
    @PutMapping("/{bookingId}")
    public Booking modifyBooking(@RequestBody Booking bookingRequest, @PathVariable String bookingId){
        //get the existing document from DB
        // populate new value from request to existing object/entity/document
        Booking existingBooking = bookingRepository.findById(bookingId).get();
        existingBooking.setCustomerId(bookingRequest.getCustomerId());
        existingBooking.setGuestAmount(bookingRequest.getGuestAmount());
        existingBooking.setRoomId(bookingRequest.getRoomId());
        existingBooking.setPaymentInfo(bookingRequest.getPaymentInfo());
        return bookingRepository.save(existingBooking);
    }

    @DeleteMapping("/{bookingId}")
    public String deleteBooking(@PathVariable String bookingId){
        bookingRepository.deleteById(bookingId);
        return bookingId +" booking deleted from system ";
    }

}
