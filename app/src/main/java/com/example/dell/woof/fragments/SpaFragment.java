package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.adapters.MySpaListAdapter;
import com.example.dell.woof.model.MySpas;
import com.example.dell.woof.ui.BaseRequestClass;
import com.example.dell.woof.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 09-09-2016.
 */
public class SpaFragment extends Fragment {

    private List<MySpas> spasList = new ArrayList<>();
    private MySpaListAdapter adapter;
    private MySpaListAdapter.OnSpaClickListener clickListener;
    private BottomSheetBehavior mBottomSheetBehavior;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spa, null);
        listView = (ListView) view.findViewById(R.id.listview);
        final View bottomSheet = view.findViewById(R.id.bottom_sheet);
        getAllSpasNearBy();
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        adapter = new MySpaListAdapter(getActivity(), spasList);
        clickListener = new MySpaListAdapter.OnSpaClickListener() {
            @Override
            public void onSpaClick(View view, int position) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                ImageView imageView = (ImageView) bottomSheet.findViewById(R.id.spaImage);
//                ((TextView) bottomSheet.findViewById(R.id.spaAbout)).setText(spasList.get(position).getSpa().getAbout());
                ((TextView) bottomSheet.findViewById(R.id.spaName)).setText(spasList.get(position).getSpa().getName());
//                ((TextView) bottomSheet.findViewById(R.id.spaAbout)).setText(spasList.get(position).getSpa().getAbout());
                Picasso.with(getActivity()).load(spasList.get(position).getSpa().getImage()).into(imageView);
            }
        };
        listView.setAdapter(adapter);
        return view;
    }

    private void getAllSpasNearBy(){
        Util.showProgressDialog("Loading your spas...", getActivity());
        HashMap<String, String> params = new HashMap<>();
        params.put("user", WoofApplication.getWoofApplication().getCurrentUser().getUserID());
        params.put("type","spa");
        com.android.volley.Response.Listener<ArrayList<MySpas>> listener = new Response.Listener<ArrayList<MySpas>>() {
            @Override
            public void onResponse(ArrayList<MySpas> response) {
                Util.hideProgressDialog();
                spasList = response;
                adapter = new MySpaListAdapter(getActivity(), spasList);
                adapter.setListener(clickListener);
                listView.setAdapter(adapter);
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.hideProgressDialog();
                Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        BaseRequestClass.fetchMySpas(getActivity(), params, listener, errorListener);
    }

    @Override
    public void onPause() {
        Util.hideProgressDialog();
        super.onPause();
    }
}
