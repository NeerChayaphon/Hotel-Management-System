package com.ooadproject.hotelmanagement.model;

public class Room {
    private Integer roomId;
    private Integer roomFloor;
    private String roomBuilding;
    private String roomType; //replace String by Roomtype

    public Room() {
    }

    public Room(Integer roomId, Integer roomFloor, String roomBuilding, String roomType) {
        this.roomId = roomId;
        this.roomFloor = roomFloor;
        this.roomBuilding = roomBuilding;
        this.roomType = roomType;
    }

    public Integer getRoomFloor() {
        return roomFloor;
    }

    public String getRoomBuilding() {
        return roomBuilding;
    }

    public String getRoomType() {
        return roomType;
    }
}
