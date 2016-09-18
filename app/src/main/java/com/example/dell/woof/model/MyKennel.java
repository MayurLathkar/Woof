package com.example.dell.woof.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dell on 17-09-2016.
 */
public class MyKennel implements Serializable {

    @SerializedName("user")
    private String userId;

    @SerializedName("kennel")
    private Kennel kennel;

    @SerializedName("timings")
    private String timings;

    @SerializedName("bookingtype")
    private String bookingType;

    @SerializedName("status")
    private boolean status;

    @SerializedName("id")
    private String id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Kennel getKennel() {
        return kennel;
    }

    public void setKennel(Kennel kennel) {
        this.kennel = kennel;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
