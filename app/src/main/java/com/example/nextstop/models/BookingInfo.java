package com.example.nextstop.models;

public class BookingInfo {
    String Email,Name,Phone;

    public BookingInfo() {
    }

    public BookingInfo(String email, String name, String phone) {
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
