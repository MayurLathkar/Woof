package com.example.dell.woof.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.adapters.ExploreViewAdapter;
import com.example.dell.woof.model.DoctorsDetails;

import java.util.ArrayList;

/**
 * Created by Dell on 09-08-2016.
 */
public class ExploreFragment extends android.support.v4.app.Fragment{

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        if (getArguments().getString("type").equals("Veterinary")){
            ArrayList<DoctorsDetails> doctorsDetailses = (ArrayList<DoctorsDetails>) getArguments().getSerializable("doctors");
            if (doctorsDetailses.size() > 0){
                ExploreViewAdapter adapter = new ExploreViewAdapter(getContext(), doctorsDetailses);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }else{
                view.findViewById(R.id.tvMessage).setVisibility(View.VISIBLE);
                ((TextView) view.findViewById(R.id.tvMessage)).setText("No doctors found near by you...!");
            }
        }
        if (getArguments().getString("type").equals("Spa")){
            view.findViewById(R.id.tvMessage).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.tvMessage)).setText("No Spa found near by you...!");
        }
        return view;
    }
}
