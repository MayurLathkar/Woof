package com.example.dell.woof.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.dell.woof.constants.AppSpecificConstants;
import com.example.dell.woof.model.MyDoctors;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 18-09-2016.
 */
public class FetchMyBuddies<T> extends Request<T>{
    private Request.Priority priority = Request.Priority.NORMAL;
    private Type type;
    private Response.Listener<T> listener;
    private final Gson mGson;
    private Context context;

    public FetchMyBuddies(Context context, String userId, Type type, Response
            .Listener<T>
            listener, Response.ErrorListener errorListener){
        super(Method.GET, AppSpecificConstants.getBaseApi() + AppSpecificConstants
                        .API_fetchMyBuddies + userId,
                errorListener);
        this.context = context;
        this.type = type;
        this.mGson = new Gson();
        this.listener = listener;
        VolleyLog.DEBUG = true;
        setRetryPolicy(new DefaultRetryPolicy(10000, 2, 1));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }


    @Override
    public Request.Priority getPriority() {
        return priority;
    }

    public void setPriority(Request.Priority priority) {
        this.priority = priority;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.v("TAG", json);
            return (Response<T>) Response.success(mGson.fromJson(json, type),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("TAG", e.getMessage());
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        ArrayList<MyDoctors> result = (ArrayList<MyDoctors>) response;
        if (listener != null)
            listener.onResponse((T) result);
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }
}
