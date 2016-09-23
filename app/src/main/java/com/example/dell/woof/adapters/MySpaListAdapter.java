package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.model.MySpas;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 09-09-2016.
 */
public class MySpaListAdapter extends BaseAdapter {

    Context context;
    List<MySpas> spasList = new ArrayList<>();
    OnSpaClickListener listener;

    public void setListener(OnSpaClickListener listener) {
        this.listener = listener;
    }

    public MySpaListAdapter(Context context, List<MySpas> list){
        this.context = context;
        this.spasList = list;
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
        return spasList.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.spa_single_item, null);
        ((TextView) view.findViewById(R.id.spaName)).setText(spasList.get(i).getSpa().getName());
        ((TextView) view.findViewById(R.id.spaNumber)).setText("Contact: +91"+spasList.get(i).getSpa().getNumber());
        ((TextView) view.findViewById(R.id.spaTime)).setText("Time: "+spasList.get(i).getSpa().getTiming()+" (Mon-Sat)");
        CircleImageView imageView = (CircleImageView) view.findViewById(R.id.spaImage);
        Picasso.with(context).load(spasList.get(i).getSpa().getImage()).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSpaClick(view, i);
            }
        });
        return view;
    }

    public interface OnSpaClickListener{
        void onSpaClick(View view, int position);
    }
}
