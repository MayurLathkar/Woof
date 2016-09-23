package com.example.dell.woof.ui;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.dell.woof.model.DoctorsDetails;
import com.example.dell.woof.model.DogDetails;
import com.example.dell.woof.model.MyDoctors;
import com.example.dell.woof.model.MyKennel;
import com.example.dell.woof.model.MyPet;
import com.example.dell.woof.model.MySpas;
import com.example.dell.woof.model.MyStore;
import com.example.dell.woof.model.UserDetails;
import com.example.dell.woof.requests.FetchDoctorsDetails;
import com.example.dell.woof.requests.FetchMyBuddies;
import com.example.dell.woof.requests.FetchMyDoctors;
import com.example.dell.woof.requests.FetchMyKennel;
import com.example.dell.woof.requests.FetchMySpas;
import com.example.dell.woof.requests.FetchMyStore;
import com.example.dell.woof.requests.SaveDogDetailsRequest;
import com.example.dell.woof.requests.SendFeedbackRequest;
import com.example.dell.woof.requests.UserLoginRequest;
import com.example.dell.woof.requests.UserSignUpRequest;
import com.example.dell.woof.services.MyVolley;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dell on 15-08-2016.
 */
public class BaseRequestClass {
    public static UserLoginRequest<UserDetails> userLogin(Context con,
                                                          HashMap<String, String> params, Response
                                                                    .Listener<UserDetails> listener, Response.ErrorListener errorListener) {
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<UserDetails>() {
        }.getType();
        final UserLoginRequest<UserDetails> lRequest = new UserLoginRequest<>(con, params, type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static UserSignUpRequest<UserDetails> userSignUp(Context con,
                                                            HashMap<String, String> params, Response
                                                                    .Listener<UserDetails> listener, Response.ErrorListener errorListener) {
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<UserDetails>() {
        }.getType();
        final UserSignUpRequest<UserDetails> lRequest = new UserSignUpRequest<>(con, params, type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static SaveDogDetailsRequest<DogDetails> saveDogDetailsRequest(Context con,
                                                                           HashMap<String, Object> params, Response
                                                                                   .Listener<DogDetails> listener, Response.ErrorListener errorListener) {
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<DogDetails>() {
        }.getType();
        final SaveDogDetailsRequest<DogDetails> lRequest = new SaveDogDetailsRequest<>(con, params, type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static FetchDoctorsDetails<ArrayList<DoctorsDetails>> fetchDoctorsDetails(Context con, HashMap<String, Object> params,
                                                                                     Response
                                                                                  .Listener<ArrayList<DoctorsDetails>> listener, Response.ErrorListener errorListener) {
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<ArrayList<DoctorsDetails>>() {
        }.getType();
        final FetchDoctorsDetails<ArrayList<DoctorsDetails>> lRequest = new FetchDoctorsDetails<>(con, params,type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static FetchMySpas<ArrayList<MySpas>> fetchMySpas(Context con, HashMap<String, String> params,
                                                                     Response
                                                                                             .Listener<ArrayList<MySpas>> listener, Response.ErrorListener errorListener) {
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<ArrayList<MySpas>>() {
        }.getType();
        final FetchMySpas<ArrayList<MySpas>> lRequest = new FetchMySpas<>(con, params,type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static FetchMyDoctors<ArrayList<MyDoctors>> fetchMyDoctors(Context con, HashMap<String, String> params, Response
            .Listener<ArrayList<MyDoctors>> listener, Response.ErrorListener errorListener){
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<ArrayList<MyDoctors>>() {
        }.getType();
        final FetchMyDoctors<ArrayList<MyDoctors>> lRequest = new FetchMyDoctors<>(con, params,type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static FetchMyKennel<ArrayList<MyKennel>> fetchMyKennel(Context con, HashMap<String, String> params, Response
            .Listener<ArrayList<MyKennel>> listener, Response.ErrorListener errorListener){
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<ArrayList<MyKennel>>() {
        }.getType();
        final FetchMyKennel<ArrayList<MyKennel>> lRequest = new FetchMyKennel<>(con, params,type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static FetchMyStore<ArrayList<MyStore>> fetchMyStore(Context con, HashMap<String, String> params, Response
            .Listener<ArrayList<MyStore>> listener, Response.ErrorListener errorListener){
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<ArrayList<MyStore>>() {
        }.getType();
        final FetchMyStore<ArrayList<MyStore>> lRequest = new FetchMyStore<>(con, params,type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static FetchMyBuddies<ArrayList<MyPet>> fetchMyBuddies(Context con, String userId, Response
            .Listener<ArrayList<MyPet>> listener, Response.ErrorListener errorListener){
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<ArrayList<MyPet>>() {
        }.getType();
        final FetchMyBuddies<ArrayList<MyPet>> lRequest = new FetchMyBuddies<>(con, userId,type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }

    public static SendFeedbackRequest<JsonObject> sendUserFeedBack(Context con,
                                                                   HashMap<String, String> params, Response
                                                                        .Listener<JsonObject> listener, Response.ErrorListener errorListener){
        RequestQueue queue = MyVolley.getInstance().getRequestQueue();
        Type type = new TypeToken<JsonObject>() {
        }.getType();
        final SendFeedbackRequest<JsonObject> lRequest = new SendFeedbackRequest<>(con, params, type, listener, errorListener);
        queue.add(lRequest);
        return lRequest;
    }
}

