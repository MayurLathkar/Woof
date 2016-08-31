package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Dell on 11-08-2016.
 */
public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public MyInfoWindowAdapter(Context context){
        this.context = context;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return getInfoContents(marker);
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = null;
        if (view == null && context != null)
            view = View.inflate(context, R.layout.custom_marker, null);
//
        ((TextView) view.findViewById(R.id.tvTitle)).setText(marker.getTitle());
        ((TextView) view.findViewById(R.id.tvTime)).setText("Time : "+marker.getSnippet());
        return view;
    }
}
