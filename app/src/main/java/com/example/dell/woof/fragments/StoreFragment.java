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
import com.example.dell.woof.adapters.MyStoreListAdapter;
import com.example.dell.woof.model.MyStore;
import com.example.dell.woof.ui.BaseRequestClass;
import com.example.dell.woof.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 17-09-2016.
 */
public class StoreFragment extends Fragment {

    private List<MyStore> storeList = new ArrayList<>();
    private MyStoreListAdapter adapter;
    private MyStoreListAdapter.OnStoreClickListener clickListener;
    private BottomSheetBehavior mBottomSheetBehavior;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, null);
        listView = (ListView) view.findViewById(R.id.listview);
        final View bottomSheet = view.findViewById(R.id.bottom_sheet);
        getMyStore();
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        adapter = new MyStoreListAdapter(getActivity(), storeList);
        clickListener = new MyStoreListAdapter.OnStoreClickListener() {
            @Override
            public void onStoreClick(View view, int position) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                ImageView imageView = (ImageView) bottomSheet.findViewById(R.id.storeImage);
                ((TextView) bottomSheet.findViewById(R.id.storeName)).setText(storeList.get(position).getStore().getName());
                ((TextView) bottomSheet.findViewById(R.id.storeAbout)).setText(storeList.get(position).getStore().getAbout());
                Picasso.with(getActivity()).load(storeList.get(position).getStore().getImage()).into(imageView);
            }
        };
        listView.setAdapter(adapter);
        return view;
    }

    private void getMyStore(){
        Util.showProgressDialog("Loading your store...", getActivity());
        HashMap<String, String> params = new HashMap<>();
        params.put("user", WoofApplication.getWoofApplication().getCurrentUser().getUserID());
        params.put("type","store");
        final com.android.volley.Response.Listener<ArrayList<MyStore>> listener = new Response.Listener<ArrayList<MyStore>>() {
            @Override
            public void onResponse(ArrayList<MyStore> response) {
                Util.hideProgressDialog();
                storeList = response;
                adapter = new MyStoreListAdapter(getActivity(), storeList);
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

        BaseRequestClass.fetchMyStore(getActivity(), params, listener, errorListener);
    }
}
