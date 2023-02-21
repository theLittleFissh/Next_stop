package com.example.nextstop.models;

public class HomeModel {
    String app, Address, Cost, Room, Mobile, img_link,Description;

    HomeModel() {


    }


    public HomeModel(String app, String address, String cost, String room, String mobile, String img_link, String description) {
        this.app = app;
        Address = address;
        Cost = cost;
        Room = room;
        Mobile = mobile;
        this.img_link = img_link;
        Description = description;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}