package com.example.dell.woof.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dell.woof.R;

/**
 * Created by Dell on 24-09-2016.
 */
public class PartnerWithUsFragment extends Fragment implements View.OnClickListener{

//    private EditText name, email, mobile, fees, aboutMe, clinicAdd, clinicSite;
//    private ImageView regProof, degreeProof, phoIdProof;
//    private Spinner partner, experience;
//    private Button btnContinue;
//    private Uri mCropImageUri;
    private View layoutView;
    private WebView webView;
//    private CropImageView mCropImageView;
//    private String[] partnerTypes = {"Select Type", "Doctor", "Kennel", "Spa", "Store"};
//    private String[] totalExp = {"1 - 5","5 - 10","10 - 15", "15 - 20"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_partner, null);
        intializeAll(layoutView);
        return layoutView;
    }

    private void intializeAll(final View view){
        webView = (WebView) view.findViewById(R.id.webView);
        startWebView("http://54.169.84.107/form-boot/index.php");
//        name = (EditText) view.findViewById(R.id.etName);
//        email = (EditText) view.findViewById(R.id.etEmail);
//        mobile = (EditText) view.findViewById(R.id.etMobile);
//        fees = (EditText) view.findViewById(R.id.etFees);
//        aboutMe = (EditText) view.findViewById(R.id.etAbout);
//        clinicAdd = (EditText) view.findViewById(R.id.etClinic);
//        clinicSite = (EditText) view.findViewById(R.id.etWebsite);
//        partner = (Spinner) view.findViewById(R.id.partnerType);
//        experience = (Spinner) view.findViewById(R.id.experience);
//        btnContinue = (Button) view.findViewById(R.id.btnContinue);
//        regProof = (ImageView) view.findViewById(R.id.reg_image);
//        degreeProof = (ImageView) view.findViewById(R.id.deg_image);
//        phoIdProof = (ImageView) view.findViewById(R.id.id_image);
//        regProof.setOnClickListener(this);
//        degreeProof.setOnClickListener(this);
//        phoIdProof.setOnClickListener(this);
//        btnContinue.setOnClickListener(this);
//        final ArrayAdapter<String> partnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout
//                .simple_dropdown_item_1line, partnerTypes);
//        partnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        partner.setAdapter(partnerAdapter);
//
//        partner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View selectedView, int i, long l) {
//                if (partnerTypes[i].equals("Doctor")){
//                    view.findViewById(R.id.kennelLayout).setVisibility(View.GONE);
//                } else if (partnerTypes[i].equals("Kennel")){
//                    view.findViewById(R.id.kennelLayout).setVisibility(View.VISIBLE);
//                } else if (partnerTypes[i].equals("Spa")){
//                    view.findViewById(R.id.kennelLayout).setVisibility(View.GONE);
//                } else if (partnerTypes[i].equals("Store")){
//                    view.findViewById(R.id.kennelLayout).setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        ArrayAdapter<String> experienceAdapter = new ArrayAdapter<>(getActivity(), android.R.layout
//                .simple_dropdown_item_1line, totalExp);
//        experienceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        experience.setAdapter(experienceAdapter);

    }


    private void startWebView(final String url){
        webView.setWebViewClient(new WebViewClient(){
            ProgressDialog progressDialog;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }



    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.reg_image:
//                startActivityForResult(Util.getPickImageChooserIntent(getActivity()), 0);
//                break;
//            case R.id.deg_image:
//                startActivityForResult(Util.getPickImageChooserIntent(getActivity()), 1);
//                break;
//            case R.id.id_image:
//                startActivityForResult(Util.getPickImageChooserIntent(getActivity()), 2);
//                break;
//            case R.id.btnContinue:
//                ((LinearLayout) layoutView.findViewById(R.id.kennelLayout)).addView(View.inflate(getActivity(), R.layout.dynamic_facility_view, null));
//                saveAllDetails();
//        }
    }

    private void saveAllDetails(){

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK){
//            Uri imageUri = Util.getPickImageResultUri(data, getActivity());
//            boolean requirePermissions = false;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
//                    getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
//                    Util.isUriRequiresPermissions(imageUri, getActivity())) {
//
//                // request permissions and handle the result in onRequestPermissionsResult()
//                requirePermissions = true;
//                mCropImageUri = imageUri;
//                switch (requestCode) {
//                    case 0:
//                        getActivity().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//                        break;
//                }
//            }
//            if (!requirePermissions) {
//                switch (requestCode) {
//                    case 0:
//                        openCropDialog(imageUri, 0);
//                        break;
//                    case 1:
//                        openCropDialog(imageUri, 1);
//                        break;
//                    case 2:
//                        openCropDialog(imageUri, 2);
//                        break;
//                }
//            }
//        }
//    }

//    void openCropDialog(Uri imageUri, final int position) {
//        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//        View profilePicChangeView = layoutInflater.inflate(R.layout.crop_image, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(profilePicChangeView);
//        builder.setCancelable(false);
//        final AlertDialog dialog = builder.create();
//        Button cropDone = (Button) profilePicChangeView.findViewById(R.id.crop_done);
//        mCropImageView = (CropImageView) profilePicChangeView.findViewById(R.id.CropImageView);
//        mCropImageView.setImageUriAsync(imageUri);
//        mCropImageView.setFixedAspectRatio(true);
//        mCropImageView.setAspectRatio(700, 600);
//        cropDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                onCropImageClick(null, position);
//            }
//        });
//        dialog.show();
//    }

//    public void onCropImageClick(View view, int position) {
//        Bitmap cropped = mCropImageView.getCroppedImage(500, 800);
//
//        if (cropped != null){
//
//            if (cropped != null) {
//                switch (position) {
//                    case 0:
//                        regProof.setImageBitmap(cropped);
//                        break;
//                    case 1:
//                        degreeProof.setImageBitmap(cropped);
//                        break;
//                    case 2:
//                        phoIdProof.setImageBitmap(cropped);
//                        break;
//                }
//            }
//        }
//    }
}
