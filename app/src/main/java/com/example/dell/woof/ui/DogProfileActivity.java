package com.example.dell.woof.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.adapters.DogProfileAdapter;
import com.example.dell.woof.adapters.ViewPagerAdapter;
import com.example.dell.woof.model.DogDetails;
import com.example.dell.woof.util.Util;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by praful on 14/8/16.
 */
public class DogProfileActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager viewPager;
    private ImageView uploadVaccination;
    private Button btnContinue;
    private ImageView image1, image2, image3, image4, image5;
    private List<String> vaccinationBase64 = new ArrayList<>();
    private CropImageView mCropImageView;
    private Uri mCropImageUri;
    private HashMap<String, Object> params = new HashMap<>();
    private Bitmap icon;
    boolean isAddMore = false, genderSelectedTrue = false, ageSelectedTrue = false;
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView dogsProfiles;
    private DogProfileAdapter profileAdapter;
    private Spinner gender, age;
    private List<Bitmap> dogImageList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_profile);
        setSpinnerItems();
        intializeAll();
    }

    private void setSpinnerItems(){
        gender = (Spinner) findViewById(R.id.spinner1);
        age = (Spinner) findViewById(R.id.spinner2);
        final ArrayList<String> genders = new ArrayList<>();
        final ArrayList<String> ages = new ArrayList<>(Arrays.asList("AGE","1","2","3","4","5","6","7","8","9","10","11","12"));
        genders.add("GENDER");
        genders.add("Male");
        genders.add("Female");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, genders);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, ages);
        gender.setAdapter(genderAdapter);
        age.setAdapter(ageAdapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0){
                    genderSelectedTrue = true;
                    params.put("gender", genders.get(position).toString());
                } else
                    genderSelectedTrue = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
               if (position != 0){
                   ageSelectedTrue = true;
                   params.put("age", Integer.valueOf(ages.get(position)));
               } else
                    ageSelectedTrue = false;
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
    }

    private void intializeAll(){
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        icon = BitmapFactory.decodeResource(getResources(), R.drawable.small_foot);
        viewPagerAdapter = new ViewPagerAdapter(this, dogImageList);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        viewPager = (ViewPager) findViewById(R.id.profile_view_pager);
        viewPager.setAdapter(viewPagerAdapter);
        btnContinue.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onPageChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        onPageChanged(0);
        Switch button = (Switch) findViewById(R.id.btnSwitch);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true)
                    isAddMore = true;
                else if (b == false)
                    isAddMore = false;
            }
        });
    }

    void onPageChanged(int position) {
        for (int i = 0; i < dogImageList.size(); i++) {
            if (dogImageList.get(i) != icon) {
                switch (i) {
                    case 0:
                        image1.setImageBitmap(dogImageList.get(i));
                        break;
                    case 1:
                        image2.setImageBitmap(dogImageList.get(i));
                        break;
                    case 2:
                        image3.setImageBitmap(dogImageList.get(i));
                        break;
                    case 3:
                        image4.setImageBitmap(dogImageList.get(i));
                        break;
                    case 4:
                        image5.setImageBitmap(dogImageList.get(i));
                        break;
                }

            } else {

            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image1:
                viewPager.setCurrentItem(0);
                onLoadImageClick(view, 0);
                break;
            case R.id.image2:
                viewPager.setCurrentItem(1);
                onLoadImageClick(view, 1);
                break;
            case R.id.image3:
                viewPager.setCurrentItem(2);
                onLoadImageClick(view, 2);
                break;
            case R.id.image4:
                viewPager.setCurrentItem(3);
                onLoadImageClick(view, 3);
                break;
            case R.id.image5:
                viewPager.setCurrentItem(4);
                onLoadImageClick(view, 4);
                break;
            case R.id.btnContinue:
                saveDogDetails();
                break;

        }
    }

    private void saveDogDetails(){
        if(((EditText) findViewById(R.id.etDogName)).getEditableText().toString().equals("") ||
                ((EditText) findViewById(R.id.etDogBreed)).getEditableText().toString().equals("") ||
                ((EditText) findViewById(R.id.etAbout)).getEditableText().toString().equals("") || !genderSelectedTrue || !ageSelectedTrue
                || dogImageList.size() == 0){
            Toast.makeText(DogProfileActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
        } else {
            showProgressDialog("Saving...");
            params.put("user", WoofApplication.getWoofApplication().getCurrentUser().getUserID());
            params.put("name", ((EditText) findViewById(R.id.etDogName)).getEditableText().toString());
            params.put("breed",((EditText) findViewById(R.id.etDogBreed)).getEditableText().toString());
            params.put("description", ((EditText) findViewById(R.id.etAbout)).getEditableText().toString());
            params.put("vaccination", Util.bitmapToBase64(dogImageList.get(0)));

            com.android.volley.Response.Listener<DogDetails> listener = new Response.Listener<DogDetails>() {
                @Override
                public void onResponse(DogDetails response) {
                    hideProgressDialog();
                    Log.v("Response=>", response.toString());
                    if (isAddMore){
                        Intent intent = new Intent(DogProfileActivity.this, DogProfileListActivity.class);
                        DogProfileListActivity.arrayList = dogImageList;
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(DogProfileActivity.this, "Dog added successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DogProfileActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };

            com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    hideProgressDialog();
                    Toast.makeText(DogProfileActivity.this, ""+error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            };

            BaseRequestClass.saveDogDetailsRequest(DogProfileActivity.this, params, listener, errorListener);
        }
    }
    /**
     * On load image button click, start pick image chooser activity.
     */
    public void onLoadImageClick(View view, int position) {
        startActivityForResult(Util.getPickImageChooserIntent(this), position);
    }

    /**
     * Crop the image and set it back to the cropping view.
     */
    public void onCropImageClick(View view, int position) {
        Bitmap cropped = mCropImageView.getCroppedImage(500, 800);
        dogImageList.add(position, cropped);
        if (cropped != null){

            if (cropped != null) {
                switch (position) {
                    case 0:
                        image1.setImageBitmap(cropped);
                        break;
                    case 1:
                        image2.setImageBitmap(cropped);
                        break;
                    case 2:
                        image3.setImageBitmap(cropped);
                        break;
                    case 3:
                        image4.setImageBitmap(cropped);
                        break;
                    case 4:
                        image5.setImageBitmap(cropped);
                        break;
                }
                viewPager.setAdapter(viewPagerAdapter);
                viewPagerAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri = Util.getPickImageResultUri(data, DogProfileActivity.this);

            // For API >= 23 we need to check specifically that we have permissions to read external storage,
            // but we don't know if we need to for the URI so the simplest is to try open the stream and see if we get error.
            boolean requirePermissions = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    Util.isUriRequiresPermissions(imageUri, DogProfileActivity.this)) {

                // request permissions and handle the result in onRequestPermissionsResult()
                requirePermissions = true;
                mCropImageUri = imageUri;
                switch (requestCode) {
                    case 0:
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                        break;
                    case 1:
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        break;
                    case 2:
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                        break;
                    case 3:
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                        break;
                    case 4:
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 4);
                        break;
                    case 5:
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 5);
                        break;
                }

            }
            if (!requirePermissions) {
                switch (requestCode) {
                    case 0:
                        openCropDialog(imageUri, 0);
                        break;
                    case 1:
                        openCropDialog(imageUri, 1);
                        break;
                    case 2:
                        openCropDialog(imageUri, 2);
                        break;
                    case 3:
                        openCropDialog(imageUri, 3);
                        break;
                    case 4:
                        openCropDialog(imageUri, 4);
                        break;
                    case 5:
                        openCropDialog(imageUri, 5);
                }

            }
        }
    }

    void openCropDialog(Uri imageUri, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(DogProfileActivity.this);
        View profilePicChangeView = layoutInflater.inflate(R.layout.crop_image, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(DogProfileActivity.this);
        builder.setView(profilePicChangeView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        Button cropDone = (Button) profilePicChangeView.findViewById(R.id.crop_done);

        mCropImageView = (CropImageView) profilePicChangeView.findViewById(R.id.CropImageView);
        mCropImageView.setImageUriAsync(imageUri);
        mCropImageView.setFixedAspectRatio(true);
        mCropImageView.setAspectRatio(700, 600);
        cropDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onCropImageClick(null, position);
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case 0:
                    openCropDialog(mCropImageUri, 0);
                    break;
                case 1:
                    openCropDialog(mCropImageUri, 1);
                    break;
                case 2:
                    openCropDialog(mCropImageUri, 2);
                    break;
                case 3:
                    openCropDialog(mCropImageUri, 3);
                    break;
                case 4:
                    openCropDialog(mCropImageUri, 4);
                    break;

            }

        } else {
            Toast.makeText(this, "Required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Create a chooser intent to select the source to get image from.<br/>
     * The source can be camera's (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br/>
     * All possible sources are added to the intent chooser.
     */
//    public Intent getPickImageChooserIntent() {
//
//        // Determine Uri of camera image to save.
//        Uri outputFileUri = getCaptureImageOutputUri();
//
//        List<Intent> allIntents = new ArrayList<>();
//        PackageManager packageManager = getPackageManager();
//
//        // collect all camera intents
//        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
//        for (ResolveInfo res : listCam) {
//            Intent intent = new Intent(captureIntent);
//            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//            intent.setPackage(res.activityInfo.packageName);
//            if (outputFileUri != null) {
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//            }
//            allIntents.add(intent);
//        }
//
//        // collect all gallery intents
//        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        galleryIntent.setType("image/*");
//        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
//        for (ResolveInfo res : listGallery) {
//            Intent intent = new Intent(galleryIntent);
//            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//            intent.setPackage(res.activityInfo.packageName);
//            allIntents.add(intent);
//        }
//
//        // the main intent is the last in the list (fucking android) so pickup the useless one
//        Intent mainIntent = allIntents.get(allIntents.size() - 1);
//        for (Intent intent : allIntents) {
//            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
//                mainIntent = intent;
//                break;
//            }
//        }
//        allIntents.remove(mainIntent);
//
//        // Create a chooser from the main intent
//        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
//
//        // Add all other intents
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));
//
//        return chooserIntent;
//    }
//
//    /**
//     * Get URI to image received from capture by camera.
//     */
//    private Uri getCaptureImageOutputUri() {
//        Uri outputFileUri = null;
//        File getImage = getExternalCacheDir();
//        if (getImage != null) {
//            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
//        }
//        return outputFileUri;
//    }
//
//    /**
//     * Get the URI of the selected image from {@link #getPickImageChooserIntent()}.<br/>
//     * Will return the correct URI for camera and gallery image.
//     *
//     * @param data the returned data of the activity result
//     */
//    public Uri getPickImageResultUri(Intent data) {
//        boolean isCamera = true;
//        if (data != null && data.getData() != null) {
//            String action = data.getAction();
//            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
//        }
//        return isCamera ? getCaptureImageOutputUri() : data.getData();
//    }
//
//    /**
//     * Test if we can open the given Android URI to test if permission required error is thrown.<br>
//     */
//    public boolean isUriRequiresPermissions(Uri uri) {
//        try {
//            ContentResolver resolver = getContentResolver();
//            InputStream stream = resolver.openInputStream(uri);
//            stream.close();
//            return false;
//        } catch (FileNotFoundException e) {
//            if (e.getCause() instanceof ErrnoException) {
//                return true;
//            }
//        } catch (Exception e) {
//        }
//        return false;
//    }
}
