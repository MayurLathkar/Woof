package com.example.dell.woof.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dell on 17-09-2016.
 */
public class Doctor implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("numbers")
    private String number;

    @SerializedName("email")
    private String email;

    @SerializedName("address")
    private String address;

    @SerializedName("fees")
    private String fess;

    @SerializedName("degree")
    private String degree;

    @SerializedName("experience")
    private String experience;

    private String contacted;

    @SerializedName("image_url")
    private String image;

    @SerializedName("availability")
    private String availability;

    @SerializedName("about")
    private String about;

    @SerializedName("details")
    private String details;

    @SerializedName("location")
    private double[] location;

    @SerializedName("id")
    private String doctorId;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFess() {
        return fess;
    }

    public void setFess(String fess) {
        this.fess = fess;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getContacted() {
        return contacted;
    }

    public void setContacted(String contacted) {
        this.contacted = contacted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

}
