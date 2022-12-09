package com.ooadproject.hotelmanagement.service;

import com.ooadproject.hotelmanagement.model.Room;
import com.ooadproject.hotelmanagement.model.RoomType;
import com.ooadproject.hotelmanagement.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomTypeService {

    private RoomTypeRepository roomTypeRepository;

    //CRUD CREATE, READ, UPDATE, DELETE

    // Create new room type
    public RoomType addRoomType(RoomType roomType){
        return roomTypeRepository.save(roomType);
    }

    // Get all room type
    public List<RoomType> findAllRoomType(){
        return roomTypeRepository.findAll();
    }

    // Get a room type by Id
    public RoomType getRoomTypeById(String typeId){
        return roomTypeRepository.findById(typeId).get();
    }

    // Get a room type by Bedroom amount
    public List<RoomType> getRoomTypeByBedroom(int bedroom){
        return roomTypeRepository.findByBedroom(bedroom);
    }

    // Get a room type by Max guest
    public List<RoomType> getAllRoomTypeNotOverMaxGuest(int maxGuest){
        return roomTypeRepository.getAllRoomTypeNotOverMaxGuest(maxGuest);
    }

    // Get a room type by Bed amount
    public List<RoomType> getRoomTypeByBed(int bedAmount){
        return roomTypeRepository.findByBedAmount(bedAmount);
    }

    public RoomType updateRoomType(RoomType roomTypeRequest){
        //get the existing document from the db
        // populate new value from request to existing documnent

        RoomType existingRoomType = roomTypeRepository.findById(roomTypeRequest.getTypeId()).get();
        existingRoomType.setTypeName(roomTypeRequest.getTypeName());
        existingRoomType.setBedroom(roomTypeRequest.getBedroom());
        existingRoomType.setBedAmount(roomTypeRequest.getBedAmount());
        existingRoomType.setMaxGuest(roomTypeRequest.getMaxGuest());
        existingRoomType.setRoomPrice(roomTypeRequest.getRoomPrice());

        return roomTypeRepository.save(existingRoomType);
    }

    public String deleteRoomType(String roomTypeId){
        roomTypeRepository.deleteById(roomTypeId);
        return roomTypeId + "Room type delete from dashboard";
    }

}
