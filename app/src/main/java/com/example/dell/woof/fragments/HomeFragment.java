package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.model.DoctorsDetails;
import com.example.dell.woof.ui.BaseRequestClass;
import com.example.dell.woof.ui.DoctorsActivity;
import com.example.dell.woof.ui.DogProfileActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 09-09-2016.
 */
public class HomeFragment extends Fragment {

    private static String TYPE = "type";
    private ProgressDialog mProgressDialog = null;
    private double mLatitude, mLongitude;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        if (getArguments() != null){
            mLatitude = getArguments().getDouble("latitude");
            mLongitude = getArguments().getDouble("longitude");
        }

        view.findViewById(R.id.btnVeterinary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNearByDoctors();
            }
        });

        view.findViewById(R.id.btnSpa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DoctorsActivity.class);
                bundle = new Bundle();
                bundle.putString(TYPE, "Spa");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btnKennel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DoctorsActivity.class);
                bundle = new Bundle();
                bundle.putString(TYPE, "Kennel");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btnLovenest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DogProfileActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btnStore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DoctorsActivity.class);
                bundle = new Bundle();
                bundle.putString(TYPE, "Store");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getNearByDoctors() {
        showProgressDialog("Getting doctors nearby you..");
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "doctor");
        List<Double> list = new ArrayList<>();
        list.add(mLatitude);
        list.add(mLongitude);
        params.put("location", list);

        com.android.volley.Response.Listener<ArrayList<DoctorsDetails>> listener = new Response.Listener<ArrayList<DoctorsDetails>>() {
            @Override
            public void onResponse(ArrayList<DoctorsDetails> response) {
                hideProgressDialog();
                Toast.makeText(getActivity(), "Found some doctors near by you", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DoctorsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctors", response);
                bundle.putString(TYPE, "Veterinary");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressDialog();
                Toast.makeText(getActivity(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        BaseRequestClass.fetchDoctorsDetails(getActivity(), params, listener, errorListener);
    }

    protected void showProgressDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(getActivity(), null, msg, true, false, null);
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
