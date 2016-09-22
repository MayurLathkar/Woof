package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
      //  btnSave = (Button) view.findViewById(R.id.btnSave);
        geocoder = new Geocoder(getActivity());
        double lat = getArguments().getDouble("latitude");
        if (getArguments() != null){
            try {
                if (Util.checkLocationServices(getActivity())){
                    addresses = geocoder.getFromLocation(getArguments().getDouble("latitude"), getArguments().getDouble("longitude"), 1);
                    if (addresses.size() > 0)
                        address.setText(addresses.get(0).getSubAdminArea()+", "+addresses.get(0).getAdminArea()+", "+addresses.get(0).getSubLocality()+", "+addresses.get(0).getLocality()+", "+addresses.get(0).getCountryName());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
     //   btnSave.setOnClickListener(this);
        setUserInfo();
        return view;
    }

    private void setUserInfo(){
        name.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserName());
        email.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserEmail());
        number.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserNumber());
    }

    @Override
    public void onClick(View view) {
//        else if (view.getId() == R.id.btnSave){
//            Toast.makeText(getActivity(), "Your details saved", Toast.LENGTH_SHORT).show();
//            WoofApplication.getWoofApplication().initializeUser(new UserDetails(WoofApplication.getWoofApplication().getCurrentUser().getUserID(), name.getEditableText().toString(), email.getEditableText().toString(), number.getEditableText().toString(), address.getEditableText().toString()));
//        }
    }
}
