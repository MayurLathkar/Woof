package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.model.MyLove;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 24-09-2016.
 */
public class MyLoveListAdapter extends BaseAdapter {

    private Context context;
    private List<MyLove> myLoveList = new ArrayList<>();
    private OnLoveClickListener listener;

    public void setOnLoveClickListener(OnLoveClickListener listener){
        this.listener = listener;
    }

    public MyLoveListAdapter(Context context, List<MyLove> loveList){
        this.context = context;
        this.myLoveList = loveList;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return myLoveList.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.mylove_single_item, null);
        if (myLoveList.get(i) != null){
            ImageView profile = (ImageView) view.findViewById(R.id.image);
            ((TextView) view.findViewById(R.id.dogName)).setText(myLoveList.get(i).getPet2().getName());
            Picasso.with(context).load(myLoveList.get(i).getPet2().getPet_Images()[0]).into(profile);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onLoveClick(view, i);
                }
            });
        }
        return view;
    }

    public interface OnLoveClickListener{
        void onLoveClick(View view, int position);
    }
}
