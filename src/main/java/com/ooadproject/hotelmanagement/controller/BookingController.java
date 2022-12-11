package com.ooadproject.hotelmanagement.controller;
import com.ooadproject.hotelmanagement.model.*;
import com.ooadproject.hotelmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody Booking booking){
        booking.setBookingId(UUID.randomUUID().toString().split("-")[0]);
        bookingValidation(booking.getCustomerId(),booking.getRoomId());
        booking.setPaymentInfo(generatePaymentInfo(booking));

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

        bookingValidation(bookingRequest.getCustomerId(),bookingRequest.getRoomId());

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

//    @PostMapping("/testRoomPrice")
//    public double testRoomPrice(@RequestBody Booking booking){
//        return generatePaymentInfo(booking.getCheckIn(),booking.getCheckOut(),booking.getRoomId());
//    }

    private void bookingValidation(String customerId, List<String> roomId){
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer == null || customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        for (int i = 0; i < roomId.size(); i++){
            Optional<Room> room = roomRepository.findById(roomId.get(i));
            if (room == null || room.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
            }
        }
    }

    private PaymentInfo generatePaymentInfo(Booking booking){
        PaymentInfo paymentInfo = new PaymentInfo();
        double totalRoomPrice = findRoomPrice(booking.getRoomId()) * calDayBetween(booking.getCheckOut(),booking.getCheckIn());

        paymentInfo.setPaymentInfoId(UUID.randomUUID().toString().split("-")[0]);
        paymentInfo.setPaymentComplete(false);
        paymentInfo.setAmount(totalRoomPrice);
        paymentInfo.setPaymentDate(null);

        return paymentInfo;
    }

    private double findRoomPrice(List<String> roomIds){
        double roomPrice = 0.0;
        for (int i = 0; i < roomIds.size(); i++){
            Room room = roomRepository.findById(roomIds.get(i)).get();
            RoomType roomType = roomTypeRepository.findById(room.getRoomTypeId()).get();

            roomPrice += roomType.getRoomPrice();
        }
        return roomPrice;
    }

    private long calDayBetween(Date checkIn, Date checkOut){
        long difference = (checkOut.getTime() - checkIn.getTime()) / 86400000;
        return Math.abs(difference);
    }

}
