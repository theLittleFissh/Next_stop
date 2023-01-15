package com.example.nextstop;

public class userHelper {
    String app,Mobile,Address,Room,Cost;

    public userHelper(String app, String mobile, String address, String room, String cost) {
        this.app = app;
        Mobile = mobile;
        Address = address;
        Room = room;
        Cost = cost;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }
}
