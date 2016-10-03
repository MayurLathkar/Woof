package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.adapters.MyDoctorsListAdapter;
import com.example.dell.woof.model.MyDoctors;
import com.example.dell.woof.ui.BaseRequestClass;
import com.example.dell.woof.ui.CalenderActivity;
import com.example.dell.woof.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 09-09-2016.
 */
public class DoctorsFragment extends Fragment {

    private List<MyDoctors> myDoctorsList = new ArrayList<>();
    private BottomSheetBehavior mBottomSheetBehavior;
    private MyDoctorsListAdapter adapter;
    private ListView listView;
    private MyDoctorsListAdapter.OnDoctorClickListener clickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctors, null);
        listView = (ListView) view.findViewById(R.id.listview);
        final View bottom_sheet = view.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        clickListener = new MyDoctorsListAdapter.OnDoctorClickListener() {
            @Override
            public void onDoctorClick(View view, int position) {
                setDoctorDetails(bottom_sheet, position);

            }
        };
        getMyDoctors();
        return view;
    }

    private void setDoctorDetails(View bottom_sheet, final int position){
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        ImageView imageView = (ImageView) bottom_sheet.findViewById(R.id.doctorImage);
        Picasso.with(getActivity()).load(myDoctorsList.get(position).getDoctor().getImage()).into(imageView);
        ((TextView) bottom_sheet.findViewById(R.id.doctorName)).setText(myDoctorsList.get(position).getDoctor().getName());
       // ((TextView) bottom_sheet.findViewById(R.id.doctorAbout)).setText(myDoctorsList.get(position).getDoctor().getAbout());
        bottom_sheet.findViewById(R.id.btnBookApt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                intent.putExtra("doctor", myDoctorsList.get(position).getDoctor().getName());
                getActivity().startActivity(intent);
            }
        });
    }

    private void getMyDoctors() {
        Util.showProgressDialog("Loading your personal doctors...", getActivity());
        HashMap<String, String> params = new HashMap<>();
        params.put("user", WoofApplication.getWoofApplication().getCurrentUser().getUserID());
        params.put("type", "doctor");


        com.android.volley.Response.Listener<ArrayList<MyDoctors>> listener = new Response.Listener<ArrayList<MyDoctors>>() {
            @Override
            public void onResponse(ArrayList<MyDoctors> response) {
                Util.hideProgressDialog();
                myDoctorsList = response;
                adapter = new MyDoctorsListAdapter(getActivity(), myDoctorsList);
                adapter.setOnDoctorClickListener(clickListener);
                listView.setAdapter(adapter);
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.hideProgressDialog();
                Toast.makeText(getActivity(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        BaseRequestClass.fetchMyDoctors(getActivity(), params, listener, errorListener);
    }

    @Override
    public void onPause() {
        Util.hideProgressDialog();
        super.onPause();
    }
}
