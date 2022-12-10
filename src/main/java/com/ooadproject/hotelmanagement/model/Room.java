package com.ooadproject.hotelmanagement.model;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Document(collection = "room")
@Data
public class Room {

    @Id
    @ApiModelProperty(readOnly = true)
    private String roomId;
    private String roomTypeId;
    private int roomFloor;
    private String roomBuilding;

    public Room() {
    }

    public Room(String roomId, String roomTypeId, int roomFloor, String roomBuilding) {
        this.roomId = roomId;
        this.roomTypeId = roomTypeId;
        this.roomFloor = roomFloor;
        this.roomBuilding = roomBuilding;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getRoomBuilding() {
        return roomBuilding;
    }

    public void setRoomBuilding(String roomBuilding) {
        this.roomBuilding = roomBuilding;
    }
}
