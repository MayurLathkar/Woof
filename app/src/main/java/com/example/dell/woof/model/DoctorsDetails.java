package com.example.dell.woof.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 19-08-2016.
 */
public class DoctorsDetails implements Serializable {

    @SerializedName("name")
    private String dtrName;

    @SerializedName("numbers")
    private String dtrNumber;

    @SerializedName("address")
    private String dtrAddress;

    @SerializedName("email")
    private String dtrEmail;

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    @SerializedName("location")
    private List<Double> location;

    public String getDtrFess() {
        return dtrFess;
    }

    public void setDtrFess(String dtrFess) {
        this.dtrFess = dtrFess;
    }

    public String getDtrName() {
        return dtrName;
    }

    public void setDtrName(String dtrName) {
        this.dtrName = dtrName;
    }

    public String getDtrNumber() {
        return dtrNumber;
    }

    public void setDtrNumber(String dtrNumber) {
        this.dtrNumber = dtrNumber;
    }

    public String getDtrAddress() {
        return dtrAddress;
    }

    public void setDtrAddress(String dtrAddress) {
        this.dtrAddress = dtrAddress;
    }

    public String getDtrEmail() {
        return dtrEmail;
    }

    public void setDtrEmail(String dtrEmail) {
        this.dtrEmail = dtrEmail;
    }

    public String getDtrDetails() {
        return dtrDetails;
    }

    public void setDtrDetails(String dtrDetails) {
        this.dtrDetails = dtrDetails;
    }

    public String getDtrAvailability() {
        return dtrAvailability;
    }

    public void setDtrAvailability(String dtrAvailability) {
        this.dtrAvailability = dtrAvailability;
    }

    public String getDtrExp() {
        return dtrExp;
    }

    public void setDtrExp(String dtrExp) {
        this.dtrExp = dtrExp;
    }

    public String getDtrDegree() {
        return dtrDegree;
    }

    public void setDtrDegree(String dtrDegree) {
        this.dtrDegree = dtrDegree;
    }

    public String getDtrImage() {
        return dtrImage;
    }

    public void setDtrImage(String dtrImage) {
        this.dtrImage = dtrImage;
    }

    public String getDtrID() {
        return dtrID;
    }

    public void setDtrID(String dtrID) {
        this.dtrID = dtrID;
    }

    @SerializedName("fees")
    private String dtrFess;

    @SerializedName("details")
    private String dtrDetails;

    @SerializedName("availability")
    private String dtrAvailability;

    @SerializedName("experience")
    private String dtrExp;

    @SerializedName("degree")
    private String dtrDegree;

    @SerializedName("image_url")
    private String dtrImage;

    @SerializedName("id")
    private String dtrID;
}
