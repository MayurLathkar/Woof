package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.util.Util;

import java.util.List;

/**
 * Created by Dell on 12-09-2016.
 */
public class MyProfileFragment extends Fragment implements View.OnClickListener{

    private EditText name, email, number, address;
    private Button btnSave;
    ImageView btnGetAdd;
    private Geocoder geocoder;
    private List<Address> addresses = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        name = (EditText) view.findViewById(R.id.etName);
        email = (EditText) view.findViewById(R.id.etEmail);
        number = (EditText) view.findViewById(R.id.etNumber);
        address = (EditText) view.findViewById(R.id.etAddress);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnGetAdd = (ImageView) view.findViewById(R.id.getAddress);
        geocoder = new Geocoder(getActivity());
        double lat = getArguments().getDouble("latitude");
        if (getArguments() != null){
            getCurrentAddress();
        }
        btnSave.setOnClickListener(this);
        btnGetAdd.setOnClickListener(this);
        setUserInfo();
        return view;
    }

    private void getCurrentAddress(){
        try {
            if (Util.checkLocationServices(getActivity())){
                addresses = geocoder.getFromLocation(getArguments().getDouble("latitude"), getArguments().getDouble("longitude"), 1);
                if (addresses.size() > 0)
                    address.setText(addresses.get(0).getSubLocality()+", "+addresses.get(0).getThoroughfare()+", "+addresses.get(0).getSubAdminArea()+", "+addresses.get(0).getAdminArea()+", "+addresses.get(0).getCountryName()+" - "+addresses.get(0).getPostalCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setUserInfo(){
        name.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserName());
        email.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserEmail());
        number.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserNumber());

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSave.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSave.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSave.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSave)
            Toast.makeText(getActivity(), "Your details saved", Toast.LENGTH_SHORT).show();
        else{
            getCurrentAddress();
            Toast.makeText(getActivity(), "Address invoked", Toast.LENGTH_SHORT).show();
        }

    }
}
