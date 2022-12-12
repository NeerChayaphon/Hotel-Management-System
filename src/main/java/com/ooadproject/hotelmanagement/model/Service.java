package com.ooadproject.hotelmanagement.model;

import lombok.Data;

@Data
public class Service implements ServiceInterface{
    private int extraBed = 0;
    private int breakfast = 0;

    public Service() {
    }

    public int getExtraBed() {
        return extraBed;
    }

    public void setExtraBed(int extraBed) {
        this.extraBed = extraBed;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public double totalServicePrice(){
        int extraBedPrice = 500;
        int breakfastPrice = 250;
        return (extraBed * extraBedPrice) + (breakfast * breakfastPrice);
    }
}
