package com.example.nextstop.models;

public class userHelper {
    String app,Mobile,Address,Room,Cost,Description,Img_link;





    public userHelper(String app, String mobile, String address, String room, String cost, String description, String img_link) {
        this.app = app;
        Mobile = mobile;
        Address = address;
        Room = room;
        Cost = cost;
        Description = description;
        Img_link = img_link;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImg_link() {
        return Img_link;
    }

    public void setImg_link(String img_link) {
        Img_link = img_link;
    }
}