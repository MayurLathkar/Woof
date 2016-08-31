package com.example.dell.woof.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dell.woof.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by praful on 14/8/16.
 */
public class ViewPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;

    List<Bitmap> productArrayList;

    public ViewPagerAdapter(Context context, List<Bitmap> productArrayList) {
        mContext = context;
        this.productArrayList = productArrayList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.view_blue, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.small_foot);
        if (productArrayList.get(position) == icon)
            Picasso.with(mContext).load(R.drawable.small_foot).resize(itemView.getWidth(), 400).placeholder(R.drawable.small_foot).into(imageView);
        else
            imageView.setImageBitmap(productArrayList.get(position));
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
