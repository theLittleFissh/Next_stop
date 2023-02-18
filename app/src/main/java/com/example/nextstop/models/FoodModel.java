package com.example.nextstop.models;

public class FoodModel {

    String food_name, food_provider_name, food_price,food_img_link;

    FoodModel()
    {

    }

    public FoodModel(String food_name, String food_provider_name, String food_price, String food_img_link) {
        this.food_name = food_name;
        this.food_provider_name = food_provider_name;
        this.food_price = food_price;
        this.food_img_link = food_img_link;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_provider_name() {
        return food_provider_name;
    }

    public void setFood_provider_name(String food_provider_name) {
        this.food_provider_name = food_provider_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getFood_img_link() {
        return food_img_link;
    }

    public void setFood_img_link(String food_img_link) {
        this.food_img_link = food_img_link;
    }
}
