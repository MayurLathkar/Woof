package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.woof.R;
import com.example.dell.woof.adapters.MySpaListAdapter;

/**
 * Created by Dell on 09-09-2016.
 */
public class SpaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spa, null);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(new MySpaListAdapter(getActivity()));
        return view;
    }
}
