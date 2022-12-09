package com.ooadproject.hotelmanagement.controller;

import com.ooadproject.hotelmanagement.model.RoomType;
import com.ooadproject.hotelmanagement.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomType")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    // POST - Create new Room type
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomType createRoomType(@RequestBody RoomType roomType){
        return roomTypeService.addRoomType(roomType);
    }

    @GetMapping
    public List<RoomType> getAllRoomTypes(){
        return roomTypeService.findAllRoomType();
    }

    @GetMapping("/{typeId}")
    public RoomType getRoomType(@PathVariable String typeId){
        return roomTypeService.getRoomTypeById(typeId);
    }

    @GetMapping("/bedroom/{bedroom}")
    public List<RoomType> getRoomTypeByBedroom(@PathVariable int bedroom){
        return roomTypeService.getRoomTypeByBedroom(bedroom);
    }

    @GetMapping("/maxGuest/{maxGuest}")
    public List<RoomType> getAllRoomTypeNotOverMaxGuest(@PathVariable int maxGuest){
        return roomTypeService.getAllRoomTypeNotOverMaxGuest(maxGuest);
    }

    @GetMapping("/bedAmount/{bedAmount}")
    public List<RoomType> getRoomTypeByBed(@PathVariable int bedAmount){
        return roomTypeService.getRoomTypeByBed(bedAmount);
    }

    @PutMapping
    public RoomType modifyRoomType(@RequestBody RoomType roomType){
        return roomTypeService.updateRoomType(roomType);
    }

    @DeleteMapping("/{typeId}")
    public String  deleteRoomType(@PathVariable String typeId){
        return roomTypeService.deleteRoomType(typeId);
    }

}
