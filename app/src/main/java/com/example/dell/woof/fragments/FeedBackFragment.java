package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.ui.BaseRequestClass;
import com.example.dell.woof.util.Util;
import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * Created by Dell on 18-09-2016.
 */
public class FeedBackFragment extends Fragment {

    private String[] feedbacks = {"Good", "Average","Excellent","Worst"};
    private Button send;
    private Spinner spinner;
    private EditText name, email, comment;
    private HashMap<String, String> params = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, null);
        initialize(view);

        ArrayAdapter<String> feedBackAdapter = new ArrayAdapter<>(getActivity(), android.R.layout
                .simple_dropdown_item_1line, feedbacks);
        feedBackAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(feedBackAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                params.put("feedback", feedbacks[i]);
                Toast.makeText(getActivity(), "Selected: "+feedbacks[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFeedback();
            }
        });
        return view;
    }

    private void initialize(View view){
        send = (Button) view.findViewById(R.id.btnSend);
        spinner = (Spinner) view.findViewById(R.id.feedback);
        name = (EditText) view.findViewById(R.id.etName);
        email = (EditText) view.findViewById(R.id.etEmail);
        comment = (EditText) view.findViewById(R.id.etComment);
    }


    private void sendFeedback(){
        if (name.getEditableText().toString().isEmpty() || email.getEditableText().toString().isEmpty()
                || comment.getEditableText().toString().isEmpty())
            Toast.makeText(getActivity(), "Fill all the details please", Toast.LENGTH_SHORT).show();
        else {
            Util.showProgressDialog("Sending", getActivity());
            params.put("userid", WoofApplication.getWoofApplication().getCurrentUser().getUserID());
            params.put("name", name.getEditableText().toString());
            params.put("email", email.getEditableText().toString());
            params.put("comment", comment.getEditableText().toString());

            com.android.volley.Response.Listener<JsonObject> listener = new Response.Listener<JsonObject>() {
                @Override
                public void onResponse(JsonObject response) {
                    Util.hideProgressDialog();
                }
            };

            com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Util.hideProgressDialog();
                }
            };

            BaseRequestClass.sendUserFeedBack(getActivity(), params, listener, errorListener);
        }
    }
}
