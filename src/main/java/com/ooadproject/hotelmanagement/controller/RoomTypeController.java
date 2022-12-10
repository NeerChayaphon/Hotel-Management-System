package com.ooadproject.hotelmanagement.controller;
import com.ooadproject.hotelmanagement.model.RoomType;
import com.ooadproject.hotelmanagement.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roomTypes")
public class RoomTypeController {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomType createRoomType(@RequestBody RoomType roomType){
        roomType.setTypeId(UUID.randomUUID().toString().split("-")[0]);
        return roomTypeRepository.save(roomType);
    }

    @GetMapping
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    @GetMapping("/{roomTypeId}")
    public RoomType getTask(@PathVariable String roomTypeId){
        return roomTypeRepository.findById(roomTypeId).get();
    }

    @PutMapping("/{roomTypeId}")
    public RoomType modifyRoomType(@RequestBody RoomType roomTypeRequest, @PathVariable String roomTypeId){
        //get the existing document from DB
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
    public String deleteRoomType(@PathVariable String roomTypeId){
        roomTypeRepository.deleteById(roomTypeId);
        return roomTypeId +" room type deleted from system ";
    }
}