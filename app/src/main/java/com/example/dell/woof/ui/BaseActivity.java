package com.example.dell.woof.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Mayur_Lathkar on 18-03-2016.
 */
public class BaseActivity extends AppCompatActivity {

    protected ProgressDialog mProgressDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected void onDestroy() {
        hideProgressDialog();
        super.onDestroy();
    }


    public void onErrorRes(VolleyError error) {
        String json = null;

        NetworkResponse response = error.networkResponse;
        if(response != null && response.data != null){
            switch(response.statusCode){
                case 400:
                    json = new String(response.data);
                    json = trimMessage(json, "message");
                    if(json != null) displayMessage(json);
                    break;
                case 200:
                    json = new String(response.data);
                    json = trimMessage(json, "message");
                    if(json != null) displayMessage(json);
                    break;
            }
            //Additional cases
        }
    }

    public String trimMessage(String json, String key){
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }

    //Somewhere that has access to a context
    public void displayMessage(String toastString){
        Toast.makeText(BaseActivity.this, toastString, Toast.LENGTH_LONG).show();
    }


    protected void showProgressDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(BaseActivity.this, null, msg, true, false, null);
        } else {
            mProgressDialog.setMessage(msg);
            mProgressDialog.show();
        }
    }

    protected void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

}