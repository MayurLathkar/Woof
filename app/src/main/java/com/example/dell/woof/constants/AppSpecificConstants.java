package com.example.dell.woof.constants;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.example.dell.woof.util.Util;

/**
 * Created by Dell on 15-08-2016.
 */
public class AppSpecificConstants {
    private static String BASE_API = "http://54.200.185.20:1337/";
    public static String SERVER_CACHE_DIRECTORY = "";
    public static String APP_VERSION_CODE = "";
    public static String API_userLogin = "user/signin";
    public static String API_userSignUp = "user/signup";
    public static String API_addPet = "pet/add";
    public static String API_fetchDoctors = "user/nearby";
    public static String API_fetchMySpa = "bookings/counts";
    public static String API_fetchMyDoctors = "bookings/counts";
    public static String API_fetchMyBuddies = "mypet/";
    public static String API_feedback = "";
    public static String API_fetchMyLove = "likecheck";

    public static void setConfigs(Context context) {
        Resources resources = context.getResources();
        //PUSH_SENDER_ID = resources.getString(R.string.push_sender_id);

        SERVER_CACHE_DIRECTORY = new Util().getStorageLocation(context);
        try {
            APP_VERSION_CODE = "" + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseApi() {
        return BASE_API;
    }

    public static String getResourcePrefix() {
        return getBaseApi();
    }
}
