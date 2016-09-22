package com.example.dell.woof.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dell on 18-09-2016.
 */
public class MyPet implements Serializable {

    @SerializedName("user")
    private String user;

    @SerializedName("vaccination")
    private String[] vaccinations;

    @SerializedName("name")
    private String name;

    @SerializedName("gender")
    private String gender;

    @SerializedName("age")
    private String age;

    @SerializedName("description")
    private String description;

    @SerializedName("breed")
    private String breed;

    @SerializedName("img_url")
    private String imgUrl;

    @SerializedName("pet_img")
    private String[] petImages;

    @SerializedName("location")
    private double[] location;

    @SerializedName("id")
    private String id;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String[] getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(String[] vaccinations) {
        this.vaccinations = vaccinations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String[] getPetImages() {
        return petImages;
    }

    public void setPetImages(String[] petImages) {
        this.petImages = petImages;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
