package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.model.MyDoctors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 09-09-2016.
 */
public class MyDoctorsListAdapter extends BaseAdapter {
    Context context;
    private List<MyDoctors> myDoctorsList = new ArrayList<>();


    public MyDoctorsListAdapter(Context context, List<MyDoctors> list){
        this.context = context;
        this.myDoctorsList = list;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.doctor_single_item, null);
        ((TextView) view.findViewById(R.id.doctorName)).setText(myDoctorsList.get(i).getName());
        ((TextView) view.findViewById(R.id.doctorEmail)).setText(myDoctorsList.get(i).getEmail());
        ((TextView) view.findViewById(R.id.doctorNumber)).setText(myDoctorsList.get(i).getNumber());
        ((TextView) view.findViewById(R.id.doctorBook)).setText(myDoctorsList.get(i).getContacted());
        ((TextView) view.findViewById(R.id.doctorAddress)).setText(myDoctorsList.get(i).getAddress());
        return view;
    }
}
