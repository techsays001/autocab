package com.msg91.sendotp.sample;


public class Cheque {

    private String image;
    private String status;
    private String user;

    private String phone;


    public Cheque(String image,String status,String user,String phone) {

        this.image = image;
        this.status = status;
        this.user=user;
        this.phone=phone;
    }




    public String getImage() {
        return image;
    }
    public String getStatus() {
        return status;
    }
    public String getUser() {
        return user;
    }
    public String getPhone() {
        return phone;
    }}
