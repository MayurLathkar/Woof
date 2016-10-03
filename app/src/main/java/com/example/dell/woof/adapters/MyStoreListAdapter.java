package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.dell.woof.R;
import com.example.dell.woof.model.MyStore;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 18-09-2016.
 */
public class MyStoreListAdapter extends BaseAdapter {
    Context context;
    List<MyStore> storeList = new ArrayList<>();
    OnStoreClickListener listener;

    public void setListener(OnStoreClickListener listener) {
        this.listener = listener;
    }

    public MyStoreListAdapter(Context context, List<MyStore> list){
        this.context = context;
        this.storeList = list;
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
        return storeList.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.store_single_item, null);
        ((TextView) view.findViewById(R.id.storeName)).setText(storeList.get(i).getStore().getName());
        ((TextView) view.findViewById(R.id.storeNumber)).setText(storeList.get(i).getStore().getNumber());
        ((TextView) view.findViewById(R.id.storeAdd)).setText(storeList.get(i).getStore().getAddress());
        ((TextView) view.findViewById(R.id.storeTime)).setText("Time: "+storeList.get(i).getStore().getTiming());
        CircleImageView imageView = (CircleImageView) view.findViewById(R.id.storeImage);
        Picasso.with(context).load(storeList.get(i).getStore().getImage()).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onStoreClick(view, i);
            }
        });
        return view;
    }

    public interface OnStoreClickListener{
        void onStoreClick(View view, int position);
    }
}
