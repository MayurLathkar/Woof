package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dell.woof.R;

/**
 * Created by Dell on 09-09-2016.
 */
public class MyDoctorsListAdapter extends BaseAdapter {
    Context context;

    public MyDoctorsListAdapter(Context context){
        this.context = context;
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
        return 8;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.spa_single_item, null);
        return view;
    }
}
