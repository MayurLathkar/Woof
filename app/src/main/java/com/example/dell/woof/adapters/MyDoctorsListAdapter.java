package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.model.MyDoctors;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 09-09-2016.
 */
public class MyDoctorsListAdapter extends BaseAdapter {
    Context context;
    private List<MyDoctors> myDoctorsList = new ArrayList<>();
    private OnDoctorClickListener listener;

    public void setOnDoctorClickListener(OnDoctorClickListener listener){
        this.listener = listener;
    }


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
        return myDoctorsList.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.doctor_single_item, null);
        ((TextView) view.findViewById(R.id.doctorName)).setText(myDoctorsList.get(i).getDoctor().getName());
        ((TextView) view.findViewById(R.id.doctorNumber)).setText("Contact: +91"+myDoctorsList.get(i).getDoctor().getNumber());
        ((TextView) view.findViewById(R.id.doctorTime)).setText("Time: "+myDoctorsList.get(i).getDoctor().getAvailability()+" (Mon-Sat)");
        ((TextView) view.findViewById(R.id.doctorAddress)).setText(myDoctorsList.get(i).getDoctor().getAddress());
        CircleImageView imageView = (CircleImageView) view.findViewById(R.id.doctorImage);
        Picasso.with(context).load(myDoctorsList.get(i).getDoctor().getImage()).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDoctorClick(view, i);
            }
        });
        return view;
    }

    public interface OnDoctorClickListener{
        void onDoctorClick(View view, int position);
    }
}
