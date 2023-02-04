package com.example.nextstop.models;

public class best_hostels_model {

    String name;
    String price;
    String rooms;
    String type;
    String img_url;

    public best_hostels_model() {
    }

    public best_hostels_model(String name, String price, String rooms, String type, String img_url) {
        this.name = name;
        this.price = price;
        this.rooms = rooms;
        this.type = type;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
