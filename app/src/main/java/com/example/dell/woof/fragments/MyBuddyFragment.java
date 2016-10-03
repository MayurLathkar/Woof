package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.adapters.DogProfileAdapter;
import com.example.dell.woof.adapters.MyBuddiesListAdapter;
import com.example.dell.woof.model.MyPet;
import com.example.dell.woof.ui.BaseRequestClass;
import com.example.dell.woof.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 18-09-2016.
 */
public class MyBuddyFragment extends Fragment implements View.OnClickListener{

    private List<MyPet> buddyList = new ArrayList<>();
    private MyBuddiesListAdapter adapter;
    private ListView listView;
    private View bottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;
    private MyBuddiesListAdapter.OnBuddiesClickListener onBuddiesClickListener;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mybuddies, null);
        listView = (ListView) view.findViewById(R.id.listview);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        bottomSheet = view.findViewById(R.id.bottom_sheet);
        adapter = new MyBuddiesListAdapter(getActivity(), buddyList);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        onBuddiesClickListener = new MyBuddiesListAdapter.OnBuddiesClickListener() {
            @Override
            public void onBuddyClick(View view, int position) {
                setDataToBottomShit(position);
            }
        };
        getMyBuddies();
        return view;
    }

    private void setDataToBottomShit(int position){
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        ImageView imageView = (ImageView) bottomSheet.findViewById(R.id.petImage);
        ((TextView) bottomSheet.findViewById(R.id.dogName)).setText(buddyList.get(position).getName());
        ((TextView) bottomSheet.findViewById(R.id.dogAge)).setText("Age: "+buddyList.get(position).getAge()+"yr");
        ((TextView) bottomSheet.findViewById(R.id.dogCity)).setText(buddyList.get(position).getDescription());
        ((EditText) bottomSheet.findViewById(R.id.etDogName)).setText(buddyList.get(position).getName());
        ((EditText) bottomSheet.findViewById(R.id.etAbout)).setText(buddyList.get(position).getDescription());
        ((EditText) bottomSheet.findViewById(R.id.etAge)).setText(buddyList.get(position).getAge());
        ((EditText) bottomSheet.findViewById(R.id.etGender)).setText(buddyList.get(position).getGender());
        ((EditText) bottomSheet.findViewById(R.id.etDogBreed)).setText(buddyList.get(position).getBreed());
//        if (buddyList.get(position).getVaccinations() != null)
//            ((EditText) bottomSheet.findViewById(R.id.etVac)).setText(buddyList.get(position).getVaccinations()[0]);
        bottomSheet.findViewById(R.id.btnDelete).setOnClickListener(this);
        DogProfileAdapter profileAdapter = new DogProfileAdapter(getActivity(), buddyList.get(position).getId(), buddyList.get(position).getPetImages());
        recyclerView.setAdapter(profileAdapter);
        Picasso.with(getActivity()).load(buddyList.get(position).getPetImages()[0]).into(imageView);

        ((EditText) bottomSheet.findViewById(R.id.etDogName)).addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            bottomSheet.findViewById(R.id.btnDelete).setVisibility(View.GONE);
            bottomSheet.findViewById(R.id.btnSave).setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDelete:
                deleteDog();
                break;
            case R.id.btnSave:
                updateDog();
                break;
        }
    }

    private void deleteDog(){
        Toast.makeText(getActivity(), "Delete dog", Toast.LENGTH_SHORT).show();
    }

    private void updateDog(){
        Toast.makeText(getActivity(), "Details Saved", Toast.LENGTH_SHORT).show();
    }

    private void getMyBuddies(){
        Util.showProgressDialog("Loading your buddies...", getActivity());
        com.android.volley.Response.Listener<ArrayList<MyPet>> listener = new Response.Listener<ArrayList<MyPet>>() {
            @Override
            public void onResponse(ArrayList<MyPet> response) {
                Util.hideProgressDialog();
                buddyList = response;
                adapter = new MyBuddiesListAdapter(getActivity(), buddyList);
                adapter.setOnBuddiesClickListener(onBuddiesClickListener);
                listView.setAdapter(adapter);
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.hideProgressDialog();
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        };

        BaseRequestClass.fetchMyBuddies(getActivity(), WoofApplication.getWoofApplication().getCurrentUser().getUserID(), listener, errorListener);
    }

    @Override
    public void onPause() {
        Util.hideProgressDialog();
        super.onPause();
    }
}
