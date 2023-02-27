package com.example.nextstop.models;

public class FoodOrderInfo {
    String Email,Name,Phone;

    public FoodOrderInfo() {
    }

    public FoodOrderInfo(String email, String name, String phone) {
        Email = email;
        Name = name;
        Phone = phone;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
