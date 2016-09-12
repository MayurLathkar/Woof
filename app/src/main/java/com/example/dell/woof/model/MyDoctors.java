package com.example.dell.woof.model;

/**
 * Created by Dell on 12-09-2016.
 */
public class MyDoctors {

    private String name;
    private String email;
    private String number;
    private String address;
    private String contacted;

    public MyDoctors(String name, String email, String number, String address, String contacted){
        this.name = name;
        this.email = email;
        this.number = number;
        this.address = address;
        this.contacted = contacted;
    }

    public String getContacted() {
        return contacted;
    }

    public void setContacted(String contacted) {
        this.contacted = contacted;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
