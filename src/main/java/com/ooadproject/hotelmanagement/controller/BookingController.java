package com.ooadproject.hotelmanagement.controller;
import com.ooadproject.hotelmanagement.model.Booking;
import com.ooadproject.hotelmanagement.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody Booking booking){
        booking.setBookingId(UUID.randomUUID().toString().split("-")[0]);
        return bookingRepository.save(booking);
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
