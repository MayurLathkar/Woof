package com.example.dell.woof.fragments;

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
import com.example.dell.woof.adapters.MyKennelListAdapter;
import com.example.dell.woof.model.MyKennel;
import com.example.dell.woof.ui.BaseRequestClass;
import com.example.dell.woof.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 17-09-2016.
 */
public class KennelFragment extends android.app.Fragment {

    private ListView listView;
    private MyKennelListAdapter adapter;
    private List<MyKennel> kennelList = new ArrayList<>();
    private BottomSheetBehavior mBottomSheetBehavior;
    private MyKennelListAdapter.OnKennelClickListener onKennelClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kennel, null);
        listView = (ListView) view.findViewById(R.id.listview);
        final View bottomSheet = view.findViewById(R.id.bottom_sheet);
        adapter = new MyKennelListAdapter(getActivity(), kennelList);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        onKennelClickListener = new MyKennelListAdapter.OnKennelClickListener() {
            @Override
            public void onSpaClick(View view, int position) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                ImageView imageView = (ImageView) bottomSheet.findViewById(R.id.kennelImage);
                ((TextView) bottomSheet.findViewById(R.id.kennelName)).setText(kennelList.get(position).getKennel().getName());
                ((TextView) bottomSheet.findViewById(R.id.kennelAdd)).setText(kennelList.get(position).getKennel().getAddress());
                Picasso.with(getActivity()).load(kennelList.get(position).getKennel().getImage()).into(imageView);
            }
        };
        getMyKennel();
        return view;
    }

    private void getMyKennel(){
        Util.showProgressDialog("Loading your kennels...", getActivity());
        HashMap<String, String> params = new HashMap<>();
        params.put("user", WoofApplication.getWoofApplication().getCurrentUser().getUserID());
        params.put("type","kennel");
        final com.android.volley.Response.Listener<ArrayList<MyKennel>> listener = new Response.Listener<ArrayList<MyKennel>>() {
            @Override
            public void onResponse(ArrayList<MyKennel> response) {
                Util.hideProgressDialog();
                kennelList = response;
                adapter = new MyKennelListAdapter(getActivity(), kennelList);
                adapter.setListener(onKennelClickListener);
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

        BaseRequestClass.fetchMyKennel(getActivity(), params, listener, errorListener);
    }

    @Override
    public void onPause() {
        Util.hideProgressDialog();
        super.onPause();
    }
}
