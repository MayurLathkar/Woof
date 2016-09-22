package com.example.dell.woof.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.model.MyPet;
import com.example.dell.woof.ui.DogProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 18-09-2016.
 */
public class MyBuddiesListAdapter extends BaseAdapter {
    Context context;
    private List<MyPet> myPetList = new ArrayList<>();
    private OnBuddiesClickListener listener;

    public void setOnBuddiesClickListener(OnBuddiesClickListener listener){
        this.listener = listener;
    }


    public MyBuddiesListAdapter(Context context, List<MyPet> list){
        this.context = context;
        this.myPetList = list;
        myPetList.add(new MyPet());
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
        return myPetList.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (i == (myPetList.size() - 1)){
            view = View.inflate(context, R.layout.add_new_puppy, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DogProfileActivity.class);
                    context.startActivity(intent);
                }
            });
        } else {
            view = View.inflate(context, R.layout.mybuddy_single_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            ((TextView) view.findViewById(R.id.dogName)).setText(myPetList.get(i).getName());
            ((TextView) view.findViewById(R.id.dogAge)).setText("Age: "+myPetList.get(i).getAge()+" yr");
            ((TextView) view.findViewById(R.id.dogCity)).setText(myPetList.get(i).getDescription());
            if (i < 3)
                Picasso.with(context).load(myPetList.get(i).getPetImages()[i]).placeholder(R.drawable.loading).into(imageView);
            else
                Picasso.with(context).load(myPetList.get(i).getPetImages()[0]).placeholder(R.drawable.loading).into(imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onBuddyClick(view, i);
                }
            });
        }
        return view;
    }

    public interface OnBuddiesClickListener{
        void onBuddyClick(View view, int position);
    }
}
