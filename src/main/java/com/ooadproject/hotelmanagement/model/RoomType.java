package com.ooadproject.hotelmanagement.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roomTypes")
@Data
public class RoomType {
    private String typeId;
    private String typeName;
    private int bedroom;
    private int bedAmount;
    private int maxGuest;
    private double roomPrice;


    public RoomType() {
    }

    public RoomType(String id, String typeName, int bedroom, int bedAmount, int maxGuest, double roomPrice) {
        this.typeId = id;
        this.typeName = typeName;
        this.bedroom = bedroom;
        this.bedAmount = bedAmount;
        this.maxGuest = maxGuest;
        this.roomPrice = roomPrice;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBedAmount() {
        return bedAmount;
    }

    public void setBedAmount(int bedAmount) {
        this.bedAmount = bedAmount;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }
}
