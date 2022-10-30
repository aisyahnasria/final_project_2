package com.example.finalproject2;

public class User {
    public String email;
    public String password;
    public String noPhone;

    public User(String email, String password, String noPhone) {
        this.email = email;
        this.password = password;
        this.noPhone = noPhone;
    }

    public User(){

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoPhone() {
        return noPhone;
    }

    public void setNoPhone(String noPhone) {
        this.noPhone = noPhone;
    }

}
