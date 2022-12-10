package com.ooadproject.hotelmanagement.model;

public class Room {
    private String roomId;
    private RoomType roomType;
    private int roomFloor;
    private String roomBuilding;

    public Room() {
    }

    public Room(String roomId, RoomType roomType, int roomFloor, String roomBuilding) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomFloor = roomFloor;
        this.roomBuilding = roomBuilding;
    }

    public String getRoomId() {
        return roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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
