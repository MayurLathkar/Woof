package com.example.dell.woof.fragments;

import android.app.Fragment;
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
import com.example.dell.woof.adapters.MyLoveListAdapter;
import com.example.dell.woof.model.MyLove;
import com.example.dell.woof.ui.BaseRequestClass;
import com.example.dell.woof.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 24-09-2016.
 */
public class MyLoveFragment extends Fragment {

    private ListView listView;
    private List<MyLove> myLoveList = new ArrayList<>();
    private BottomSheetBehavior mBottomSheetBehavior;
    private MyLoveListAdapter.OnLoveClickListener clickListener;
    private MyLoveListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mylove, null);
        listView = (ListView) view.findViewById(R.id.listview);
        final View bottom_sheet = view.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        clickListener = new MyLoveListAdapter.OnLoveClickListener() {
            @Override
            public void onLoveClick(View view, int position) {
                setLoveDetails(bottom_sheet, position);
            }
        };
        getMyLoveList();

        return view;
    }

    private void setLoveDetails(View bottom_sheet, int position){
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        ImageView imageView = (ImageView) bottom_sheet.findViewById(R.id.dogImage);
        ((TextView) bottom_sheet.findViewById(R.id.dogName)).setText(myLoveList.get(position).getPet2().getName());
        Picasso.with(getActivity()).load(myLoveList.get(position).getPet2().getPet_Images()[0]).into(imageView);
    }

    private void getMyLoveList(){
        Util.showProgressDialog("Loading your loves...", getActivity());
        HashMap<String, String> params = new HashMap<>();
        params.put("id", "57b34c2dc51b6dab0e59325b");

        com.android.volley.Response.Listener<ArrayList<MyLove>> listener = new Response.Listener<ArrayList<MyLove>>() {
            @Override
            public void onResponse(ArrayList<MyLove> response) {
                Util.hideProgressDialog();
                myLoveList = response;
                adapter = new MyLoveListAdapter(getActivity(), myLoveList);
                adapter.setOnLoveClickListener(clickListener);
                listView.setAdapter(adapter);
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        };

        BaseRequestClass.fetchMyLove(getActivity(), params, listener, errorListener);
    }
}
