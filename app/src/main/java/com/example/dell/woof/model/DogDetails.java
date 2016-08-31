package com.example.dell.woof.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dell on 18-08-2016.
 */
public class DogDetails implements Serializable{

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getDogAge() {
        return dogAge;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
    }

    public String getDogGender() {
        return dogGender;
    }

    public void setDogGender(String dogGender) {
        this.dogGender = dogGender;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public ArrayList<String> getDogVaccinations() {
        return dogVaccinations;
    }

    public void setDogVaccinations(ArrayList<String> dogVaccinations) {
        this.dogVaccinations = dogVaccinations;
    }

    public String getDogAllergy() {
        return dogAllergy;
    }

    public void setDogAllergy(String dogAllergy) {
        this.dogAllergy = dogAllergy;
    }

    @SerializedName("name")
    private String dogName;

    @SerializedName("age")
    private int dogAge;

    @SerializedName("gender")
    private String dogGender;

    @SerializedName("breed")
    private String dogBreed;

    @SerializedName("vaccination")
    private ArrayList<String> dogVaccinations;

    private String dogAllergy;

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @SerializedName("image_url")
    private String imageUrl;
}
