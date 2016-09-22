package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.model.MyKennel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 18-09-2016.
 */
public class MyKennelListAdapter extends BaseAdapter {
    Context context;
    List<MyKennel> kennelList = new ArrayList<>();
    OnKennelClickListener listener;

    public void setListener(OnKennelClickListener listener) {
        this.listener = listener;
    }

    public MyKennelListAdapter(Context context, List<MyKennel> list){
        this.context = context;
        this.kennelList = list;
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
        return kennelList.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.kennel_single_item, null);
        ((TextView) view.findViewById(R.id.kennelName)).setText(kennelList.get(i).getKennel().getName());
        ((TextView) view.findViewById(R.id.kennelNumber)).setText(kennelList.get(i).getKennel().getNumber());
        ((TextView) view.findViewById(R.id.kennelEmail)).setText(kennelList.get(i).getKennel().getEmail());
        ((TextView) view.findViewById(R.id.kennelAddress)).setText(kennelList.get(i).getKennel().getAddress());
        ((TextView) view.findViewById(R.id.kennelCost)).setText("Time: "+kennelList.get(i).getKennel().getCost());
        CircleImageView imageView = (CircleImageView) view.findViewById(R.id.kennelImage);
        Picasso.with(context).load(kennelList.get(i).getKennel().getImage()).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSpaClick(view, i);
            }
        });
        return view;
    }

    public interface OnKennelClickListener{
        void onSpaClick(View view, int position);
    }
}
