package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.woof.R;
import com.example.dell.woof.adapters.MyDoctorsListAdapter;
import com.example.dell.woof.model.MyDoctors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 09-09-2016.
 */
public class DoctorsFragment extends Fragment {

    private List<MyDoctors> myDoctorsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctors, null);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        myDoctorsList.add(new MyDoctors("Berry Allen", "berry@gmail.com", "9833223322", "Sony center, Kalyan Nagar, Bangalore", "Contacted at 13 Sep, 2016"));
        myDoctorsList.add(new MyDoctors("Iris Allen", "berry@gmail.com", "9833223322", "Sony center, Kalyan Nagar, Bangalore", "Contacted at 13 Sep, 2016"));
        myDoctorsList.add(new MyDoctors("Harrison Wells", "berry@gmail.com", "9833223322", "Sony center, Kalyan Nagar, Bangalore", "Contacted at 13 Sep, 2016"));
        myDoctorsList.add(new MyDoctors("Patty Joe", "berry@gmail.com", "9833223322", "Sony center, Kalyan Nagar, Bangalore", "Contacted at 13 Sep, 2016"));
        myDoctorsList.add(new MyDoctors("Wolly Hells", "berry@gmail.com", "9833223322", "Sony center, Kalyan Nagar, Bangalore", "Contacted at 13 Sep, 2016"));
        myDoctorsList.add(new MyDoctors("Ketty Perry", "berry@gmail.com", "9833223322", "Sony center, Kalyan Nagar, Bangalore", "Contacted at 13 Sep, 2016"));
        myDoctorsList.add(new MyDoctors("Kaitlin Seis", "berry@gmail.com", "9833223322", "Sony center, Kalyan Nagar, Bangalore", "Contacted at 13 Sep, 2016"));
        listView.setAdapter(new MyDoctorsListAdapter(getActivity(), myDoctorsList));
        return view;
    }
}
