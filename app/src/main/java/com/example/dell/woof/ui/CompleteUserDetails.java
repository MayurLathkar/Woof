package com.example.dell.woof.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.util.Util;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteUserDetails extends AppCompatActivity implements View.OnClickListener{

    private Uri mCropImageUri;
    private CropImageView mCropImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_user_details);
        setUserInfo();
    }

    private void setUserInfo() {
        if (WoofApplication.getWoofApplication().getCurrentUser() != null){
            ((EditText) findViewById(R.id.etName)).setText(WoofApplication.getWoofApplication().getCurrentUser().getUserName());
            ((EditText) findViewById(R.id.etEmail)).setText(WoofApplication.getWoofApplication().getCurrentUser().getUserEmail());
            ((EditText) findViewById(R.id.etPhone)).setText(WoofApplication.getWoofApplication().getCurrentUser().getUserNumber());
        }
        findViewById(R.id.ivProfile).setOnClickListener(this);
        findViewById(R.id.btnContinue).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnContinue :
                if (checkFieldsForEmpty()){
                  //  updateUserProfileOnServer();
                    WoofApplication.getWoofApplication().getCurrentUser().setUserName(((EditText) findViewById(R.id.etName)).getEditableText().toString());
                    WoofApplication.getWoofApplication().getCurrentUser().setUserEmail(((EditText) findViewById(R.id.etEmail)).getEditableText().toString());
                    WoofApplication.getWoofApplication().getCurrentUser().setUserNumber(((EditText) findViewById(R.id.etPhone)).getEditableText().toString());
                    Intent intent = new Intent(CompleteUserDetails.this, OtpValidationActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CompleteUserDetails.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ivProfile :
                startActivityForResult(Util.getPickImageChooserIntent(this), 1);

        }
    }

    private boolean checkFieldsForEmpty(){
        if (((EditText) findViewById(R.id.etName)).getEditableText().toString().equals(""))
            return false;
        else if (((EditText) findViewById(R.id.etEmail)).getEditableText().toString().equals(""))
            return false;
        else if (((EditText) findViewById(R.id.etPhone)).getEditableText().toString().equals(""))
            return false;
        return true;
    }

    private void updateUserProfileOnServer() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri = Util.getPickImageResultUri(data, this);

            boolean requirePermissions = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    Util.isUriRequiresPermissions(imageUri, this)) {

                // request permissions and handle the result in onRequestPermissionsResult()
                requirePermissions = true;
                mCropImageUri = imageUri;
                switch (requestCode) {
                    case 1:
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                        break;
                }
            }
            if (!requirePermissions) {
                switch (requestCode) {
                    case 1:
                        openCropDialog(imageUri, 0);
                        break;
                }
            }
        }
    }

    void openCropDialog(Uri imageUri, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(CompleteUserDetails.this);
        View profilePicChangeView = layoutInflater.inflate(R.layout.crop_image, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(CompleteUserDetails.this);
        builder.setView(profilePicChangeView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        Button cropDone = (Button) profilePicChangeView.findViewById(R.id.crop_done);
        mCropImageView = (CropImageView) profilePicChangeView.findViewById(R.id.CropImageView);
        mCropImageView.setImageUriAsync(imageUri);
        mCropImageView.setFixedAspectRatio(true);
        mCropImageView.setAspectRatio(700, 400);
        cropDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onCropImageClick();
            }
        });
        dialog.show();
    }

    public void onCropImageClick() {
        Bitmap cropped = mCropImageView.getCroppedImage(600, 600);
        if (cropped != null)
            ((CircleImageView) findViewById(R.id.ivProfile)).setImageBitmap(cropped);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
