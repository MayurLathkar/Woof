package com.example.dell.woof.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dell on 24-09-2016.
 */
public class MyLove implements Serializable {

    @SerializedName("pet1")
    private Love pet1;

    @SerializedName("pet2")
    private Love pet2;

    @SerializedName("user")
    private String userId;

    @SerializedName("targetuser")
    private String targetUser;

    @SerializedName("status")
    private String status;

    @SerializedName("id")
    private String id;

   public class Love implements Serializable{

       @SerializedName("name")
       private String name;

       @SerializedName("pet_img")
       private String[] pet_Images;

       @SerializedName("id")
       private String id;

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public String[] getPet_Images() {
           return pet_Images;
       }

       public void setPet_Images(String[] pet_Images) {
           this.pet_Images = pet_Images;
       }

       public String getId() {
           return id;
       }

       public void setId(String id) {
           this.id = id;
       }
   }

    public Love getPet1() {
        return pet1;
    }

    public void setPet1(Love pet1) {
        this.pet1 = pet1;
    }

    public Love getPet2() {
        return pet2;
    }

    public void setPet2(Love pet2) {
        this.pet2 = pet2;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
