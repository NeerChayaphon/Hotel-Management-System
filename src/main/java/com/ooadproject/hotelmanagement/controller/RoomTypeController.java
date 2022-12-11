package com.ooadproject.hotelmanagement.controller;

import com.ooadproject.hotelmanagement.model.Booking;
import com.ooadproject.hotelmanagement.model.RoomType;
import com.ooadproject.hotelmanagement.repository.RoomTypeRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roomTypes")
public class RoomTypeController {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomType createRoomType(@RequestBody RoomType roomType) {
        roomType.setTypeId(UUID.randomUUID().toString().split("-")[0]);
        return roomTypeRepository.save(roomType);
    }

    @GetMapping
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    @GetMapping("/{roomTypeId}")
    public RoomType getType(@PathVariable String roomTypeId) {
        return roomTypeRepository.findById(roomTypeId).get();
    }

    @PutMapping("/{roomTypeId}")
    public RoomType modifyRoomType(@RequestBody RoomType roomTypeRequest, @PathVariable String roomTypeId) {
        // get the existing document from DB
        // populate new value from request to existing object/entity/document
        RoomType existingRoomType = roomTypeRepository.findById(roomTypeId).get();
        existingRoomType.setTypeName(roomTypeRequest.getTypeName());
        existingRoomType.setRoomPrice(roomTypeRequest.getRoomPrice());
        existingRoomType.setMaxGuest(roomTypeRequest.getMaxGuest());
        existingRoomType.setBedAmount(roomTypeRequest.getBedAmount());
        existingRoomType.setBedroom(roomTypeRequest.getBedroom());
        return roomTypeRepository.save(existingRoomType);
    }

    @DeleteMapping("/{roomTypeId}")
    public String deleteRoomType(@PathVariable String roomTypeId) {
        roomTypeRepository.deleteById(roomTypeId);
        return roomTypeId + " room type deleted from system ";
    }

    @GetMapping("/bedroom")
    public List<RoomType> findByBedroom(@RequestParam("bedroom") int bedroom) {
        return roomTypeRepository.findByBedroom(bedroom);
    }

    @GetMapping("/bedAmount")
    public List<RoomType> findByBedAmount(@RequestParam("bedAmount") int bedAmount) {
        return roomTypeRepository.findByBedAmount(bedAmount);
    }

    @GetMapping("/maxGuest")
    public List<RoomType> findByMaxGuest(@RequestParam("maxGuest") int maxGuest) {
        return roomTypeRepository.findByMaxGuest(maxGuest);
    }

    @GetMapping("/roomPrice")
    public List<RoomType> findByRoomPrice(@RequestParam("roomPrice") int roomPrice) {
        return roomTypeRepository.findByRoomPrice(roomPrice);
    }



    @GetMapping("/search")
    @ApiOperation(value = "Search room type",
            notes = "Room price is less than or equal to the user input / Max guest is greater than or equal to the user input / Bed amount and Bedroom are equal to the user input")
    public List<RoomType> searchRoomType(
            @RequestParam(required = false) String maxRoomPrice,
            @RequestParam(required = false) String minGuest,
            @RequestParam(required = false) String bedAmount,
            @RequestParam(required = false) String bedroomAmount
    ){

        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();

        // Room price is less than or equal to the user input
        if (maxRoomPrice != null && !maxRoomPrice.isEmpty()) {
            criteria.add(Criteria.where("roomPrice").lte(Double.parseDouble(maxRoomPrice)));
        }

        // Max guest amount is greater than or equal to the user input
        if (minGuest != null && !minGuest.isEmpty()) {
            criteria.add(Criteria.where("maxGuest").gte(Integer.parseInt(minGuest)));
        }

        // Bed amount is equal to the user input
        if (bedAmount != null && !bedAmount.isEmpty()) {
            criteria.add(Criteria.where("bedAmount").is(Integer.parseInt(bedAmount)));
        }

        // Bedroom amount is equal to the user input
        if (bedroomAmount != null && !bedroomAmount.isEmpty()) {
            criteria.add(Criteria.where("bedroom").is(Integer.parseInt(bedroomAmount)));
        }

        if(!criteria.isEmpty()) {
            query.addCriteria(new Criteria()
                    .andOperator(criteria.toArray(new Criteria[0])));
        }

        return mongoTemplate.find(query,RoomType.class);
    }
}