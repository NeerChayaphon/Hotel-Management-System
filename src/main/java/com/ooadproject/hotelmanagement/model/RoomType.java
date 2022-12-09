package com.ooadproject.hotelmanagement.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomType {
    private Integer typeId;
    private String typeName;
    private Integer bedRoom;
    private Double area;
    private Integer maxGuest;
    private Double roomPrice;

    public RoomType() {
    }

    public RoomType(Integer typeId, String typeName, Integer bedRoom, Double area, Integer maxGuest, Double roomPrice) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.bedRoom = bedRoom;
        this.area = area;
        this.maxGuest = maxGuest;
        this.roomPrice = roomPrice;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public Integer getBedRoom() {
        return bedRoom;
    }

    public Double getArea() {
        return area;
    }

    public Integer getMaxGuest() {
        return maxGuest;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setBedRoom(Integer bedRoom) {
        this.bedRoom = bedRoom;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setMaxGuest(Integer maxGuest) {
        this.maxGuest = maxGuest;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }
}
