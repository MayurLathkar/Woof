package com.example.dell.woof.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.model.DoctorsDetails;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashBoardActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{

    private Bundle bundle;
    private static String TYPE = "type";
    private LocationManager mLocationManager;
    LocationRequest mLocationRequest;
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    private boolean isProviderEnabled = false, isNetworkEnabled = false, canGetLocation = false;
    private Location mLastLocation;
    private Gson gson = new Gson();
    private double latitude, longitude;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        findViewById(R.id.btnVeterinary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNearByDoctors();
            }
        });

        findViewById(R.id.btnSpa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, DoctorsActivity.class);
                bundle = new Bundle();
                bundle.putString(TYPE, "Spa");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btnKennel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, DoctorsActivity.class);
                bundle = new Bundle();
                bundle.putString(TYPE, "Kennel");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btnLovenest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, DogProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btnStore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, DoctorsActivity.class);
                bundle = new Bundle();
                bundle.putString(TYPE, "Store");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(100000); //60 seconds
        mLocationRequest.setFastestInterval(100000); //60 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter
        Toast.makeText(DashBoardActivity.this, " "+mLastLocation.getLatitude()+" "+mLastLocation.getLongitude(), Toast.LENGTH_SHORT).show();

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private void getNearByDoctors() {
        showProgressDialog("Getting doctors nearby you..");
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", "doctor");
        List<Double> list = new ArrayList<>();
        list.add(mLastLocation.getLatitude());
        list.add(mLastLocation.getLongitude());
        params.put("location", list);

        com.android.volley.Response.Listener<ArrayList<DoctorsDetails>> listener = new Response.Listener<ArrayList<DoctorsDetails>>() {
            @Override
            public void onResponse(ArrayList<DoctorsDetails> response) {
                hideProgressDialog();
                Toast.makeText(DashBoardActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DashBoardActivity.this, DoctorsActivity.class);
                bundle = new Bundle();
                bundle.putSerializable("doctors", response);
                bundle.putString(TYPE, "Veterinary");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressDialog();
                Toast.makeText(DashBoardActivity.this, "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        BaseRequestClass.fetchDoctorsDetails(DashBoardActivity.this, params, listener, errorListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
}
