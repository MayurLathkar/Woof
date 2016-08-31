package com.example.dell.woof.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.woof.R;

/**
 * Created by Dell on 17-08-2016.
 */
public class DoctorsInfoAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    String[] stringsArray;

    public DoctorsInfoAdapter(Context context, String[] array){
        this.mContext = context;
        this.stringsArray = array;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = null;
        if (itemView == null) {
            itemView = mLayoutInflater.inflate(R.layout.view_doctor_info, container, false);
        }
        TextView textView = (TextView) itemView.findViewById(R.id.textView);
        textView.setText(stringsArray[position]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
