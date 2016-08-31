package com.example.dell.woof.ui;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.dell.woof.model.DoctorsDetails;
import com.example.dell.woof.model.DogDetails;
import com.example.dell.woof.model.UserDetails;
import com.example.dell.woof.requests.FetchDoctorsDetails;
import com.example.dell.woof.requests.SaveDogDetailsRequest;
import com.example.dell.woof.requests.UserLoginRequest;
import com.example.dell.woof.requests.UserSignUpRequest;
import com.example.dell.woof.services.MyVolley;
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
}

