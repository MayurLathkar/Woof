package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.model.UserDetails;

/**
 * Created by Dell on 12-09-2016.
 */
public class MyProfileFragment extends Fragment implements View.OnClickListener{

    private EditText name, email, number, address;
    private ImageView ivEdit;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        name = (EditText) view.findViewById(R.id.etName);
        email = (EditText) view.findViewById(R.id.etEmail);
        number = (EditText) view.findViewById(R.id.etNumber);
        address = (EditText) view.findViewById(R.id.etAddress);
        ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        ivEdit.setOnClickListener(this);
        setUserInfo();
        return view;
    }

    private void setUserInfo(){
        name.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserName());
        email.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserEmail());
        number.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserNumber());
        address.setText(WoofApplication.getWoofApplication().getCurrentUser().getUserAddress());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivEdit){
            btnSave.setAlpha(1f);
            btnSave.setEnabled(true);
            name.setEnabled(true);
            number.setEnabled(true);
            email.setEnabled(true);
            address.setEnabled(true);
            name.requestFocus();
        } else if (view.getId() == R.id.btnSave){
            Toast.makeText(getActivity(), "Your details saved", Toast.LENGTH_SHORT).show();
            WoofApplication.getWoofApplication().initializeUser(new UserDetails(WoofApplication.getWoofApplication().getCurrentUser().getUserID(), name.getEditableText().toString(), email.getEditableText().toString(), number.getEditableText().toString(), address.getEditableText().toString()));
        }
    }
}
