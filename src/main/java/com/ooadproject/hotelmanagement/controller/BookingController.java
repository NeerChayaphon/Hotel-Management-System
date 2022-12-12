package com.ooadproject.hotelmanagement.controller;


import com.ooadproject.hotelmanagement.model.*;
import com.ooadproject.hotelmanagement.repository.BookingRepository;
import com.ooadproject.hotelmanagement.repository.CustomerRepository;
import com.ooadproject.hotelmanagement.repository.RoomRepository;
import com.ooadproject.hotelmanagement.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.time.temporal.ChronoUnit;

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

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody Booking booking) {
        booking.setBookingId(UUID.randomUUID().toString().split("-")[0]);
        bookingValidation(booking.getCustomerId(),booking.getRoomId());

        Service extraService = new Service();
        if (booking.getExtraService().getExtraBed() != 0){
            extraService.setExtraBed(booking.getExtraService().getExtraBed());
        }
        if (booking.getExtraService().getBreakfast() != 0){
            extraService.setBreakfast(booking.getExtraService().getBreakfast());
        }

        double extraServicePrice = extraService.totalServicePrice();

        booking.setPaymentInfo(generatePaymentInfo(booking,extraServicePrice));

        return bookingRepository.save(booking);

    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{bookingId}")
    public Booking getBooking(@PathVariable String bookingId) {
        return bookingRepository.findById(bookingId).get();
    }

    @PutMapping("/{bookingId}")
    public Booking modifyBooking(@RequestBody Booking bookingRequest, @PathVariable String bookingId) {
        // get the existing document from DB
        // populate new value from request to existing object/entity/document
        Booking existingBooking = bookingRepository.findById(bookingId).get();

        bookingValidation(bookingRequest.getCustomerId(), bookingRequest.getRoomId());

        existingBooking.setCustomerId(bookingRequest.getCustomerId());
        existingBooking.setGuestAmount(bookingRequest.getGuestAmount());
        existingBooking.setRoomId(bookingRequest.getRoomId());

        Service extraService = new Service();
        if (bookingRequest.getExtraService().getExtraBed() != 0){
            extraService.setExtraBed(bookingRequest.getExtraService().getExtraBed());
        }
        if (bookingRequest.getExtraService().getBreakfast() != 0){
            extraService.setBreakfast(bookingRequest.getExtraService().getBreakfast());
        }

        double extraServicePrice = extraService.totalServicePrice();
        existingBooking.setPaymentInfo(generatePaymentInfo(bookingRequest,extraServicePrice));

        return bookingRepository.save(existingBooking);
    }

    @DeleteMapping("/{bookingId}")
    public String deleteBooking(@PathVariable String bookingId) {
        bookingRepository.deleteById(bookingId);
        return bookingId + " booking deleted from system ";
    }

    @GetMapping("/customerId")
    public List<Booking> findByCustomerId(@RequestParam("customerId") String customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @GetMapping("/guestAmount")
    public List<Booking> findByGuestAmount(@RequestParam("guestAmount") int guestAmount) {
        return bookingRepository.findByGuestAmount(guestAmount);
    }

    @GetMapping("/checkInDate")
    public List<Booking> findByCheckInDate(@RequestParam("checkInDate") String checkInDate) {
        return bookingRepository.findByCheckInDate(LocalDate.parse(checkInDate));
    }

    @GetMapping("/checkOutDate")
    public List<Booking> findByCheckOutDate(@RequestParam("checkOutDate") String checkOutDate) {
        return bookingRepository.findByCheckOutDate(LocalDate.parse(checkOutDate));
    }

    @GetMapping("/search")
    public List<Booking> searchBooking(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String guestAmount,
            @RequestParam(required = false) String checkInDate,
            @RequestParam(required = false) String checkOutDate
    ){

        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();

        if (customerId != null && !customerId.isEmpty()) {
            criteria.add(Criteria.where("customerId").is(customerId));
        }

        if (guestAmount != null && !guestAmount.isEmpty()) {
            criteria.add(Criteria.where("guestAmount").is(Integer.parseInt(guestAmount)));
        }

        if (checkInDate != null && !checkInDate.isEmpty()) {
            criteria.add(Criteria.where("checkInDate").is(LocalDate.parse(checkInDate)));
        }

        if (checkOutDate != null && !checkOutDate.isEmpty()) {
            criteria.add(Criteria.where("checkOutDate").is(LocalDate.parse(checkOutDate)));
        }

        if(!criteria.isEmpty()) {
            query.addCriteria(new Criteria()
                    .andOperator(criteria.toArray(new Criteria[0])));
        }

        return mongoTemplate.find(query,Booking.class);
    }



    private void bookingValidation(String customerId, List<String> roomId){

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer == null || customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        for (int i = 0; i < roomId.size(); i++) {
            Optional<Room> room = roomRepository.findById(roomId.get(i));
            if (room == null || room.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
            }
        }
    }

    private PaymentInfo generatePaymentInfo(Booking booking, double extraPrice){
        PaymentInfo paymentInfo = new PaymentInfo();
        double totalRoomPrice = findRoomPrice(booking.getRoomId()) * calDayBetween(booking.getCheckOutDate(),booking.getCheckInDate());

        paymentInfo.setPaymentInfoId(UUID.randomUUID().toString().split("-")[0]);
        paymentInfo.setPaymentComplete(false);
        paymentInfo.setAmount(totalRoomPrice + extraPrice);
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

    private long calDayBetween(LocalDate checkIn, LocalDate checkOut){
        long difference = checkOut.until(checkIn,ChronoUnit.DAYS);
        return difference;
    }

}
