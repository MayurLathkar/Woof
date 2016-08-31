package com.example.dell.woof;

import android.app.Application;
import android.util.Log;

import com.example.dell.woof.constants.AppSpecificConstants;
import com.example.dell.woof.model.UserDetails;
import com.example.dell.woof.services.DeviceResourceHandler;
import com.example.dell.woof.services.MyVolley;
import com.example.dell.woof.services.Serializer;

/**
 * Created by Dell on 15-08-2016.
 */
public class WoofApplication extends Application {
    private static WoofApplication woofApplication = null;
    private UserDetails currentUser;
    private DeviceResourceHandler deviceResourceHandler = null;

    public static WoofApplication getWoofApplication() {
        return woofApplication;
    }

    public static void setIotApplication(WoofApplication context) {
        WoofApplication.woofApplication = context;
    }

    public void initializeUser(UserDetails currentUser){
        this.initializeUser(currentUser, true);
    }

    public void initializeUser(UserDetails currentUser, boolean forceWrite) {
        if (this.getDeviceResourceHandler().getDataFromSharedPref("current_user") == null || forceWrite) {
            this.getDeviceResourceHandler().clearSharedPref("current_user");
            this.getDeviceResourceHandler().addToSharedPref("current_user", Serializer.serialize(currentUser));
        }

        this.currentUser = currentUser;
    }

    public DeviceResourceHandler getDeviceResourceHandler() {
        if (deviceResourceHandler == null)
            deviceResourceHandler = new DeviceResourceHandler(this);
        return deviceResourceHandler;
    }

    public UserDetails getCurrentUser(){
        try {
            String e = null;
            if (this.currentUser == null && (e = this.getDeviceResourceHandler().getDataFromSharedPref("current_user")) != null) {
                this.currentUser = (UserDetails) Serializer.deserialize(e);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return this.currentUser;
    }

    public void logout(){
        WoofApplication.getWoofApplication().initializeUser(null, true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("IotApp==>","hit");
        setIotApplication(this);
        AppSpecificConstants.setConfigs(this);
        MyVolley.getInstance(this);
    }
}
