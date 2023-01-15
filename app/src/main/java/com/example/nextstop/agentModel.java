package com.example.nextstop;

public class agentModel {
    String Fullname,Email,Phone;

    public agentModel(String fullname, String email, String phone) {
        Fullname = fullname;
        Email = email;
        Phone = phone;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
