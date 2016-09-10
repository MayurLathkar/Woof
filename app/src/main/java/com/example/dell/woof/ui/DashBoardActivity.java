package com.example.dell.woof.ui;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.fragments.DoctorsFragment;
import com.example.dell.woof.fragments.HomeFragment;
import com.example.dell.woof.fragments.SpaFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class DashBoardActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, NavigationView.OnNavigationItemSelectedListener {

    private Bundle bundle;
    private LocationManager mLocationManager;
    LocationRequest mLocationRequest;
    private boolean isProviderEnabled = false;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private android.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        initializeAll();
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            isProviderEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}


        if(!isProviderEnabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(DashBoardActivity.this);
            dialog.setMessage("Network not enabled");
            dialog.setPositiveButton("Open location settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }
    }

    private void initializeAll(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.tvName)).setText(WoofApplication.getWoofApplication().getCurrentUser().getUserName());
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.tvEmail)).setText(WoofApplication.getWoofApplication().getCurrentUser().getUserEmail());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
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
        if (mLastLocation != null)
        Toast.makeText(DashBoardActivity.this, " "+mLastLocation.getLatitude()+" "+mLastLocation.getLongitude(), Toast.LENGTH_SHORT).show();
        fragmentManager = getFragmentManager();
        bundle = new Bundle();
        if (mLastLocation != null){
            bundle.putDouble("latitude", mLastLocation.getLatitude());
            bundle.putDouble("longitude", mLastLocation.getLongitude());
        }
        Fragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.app.Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (item.getItemId()){
            case R.id.home:
                fragment = new HomeFragment();
                if (mLastLocation != null){
                    bundle.putDouble("latitude", mLastLocation.getLatitude());
                    bundle.putDouble("longitude", mLastLocation.getLongitude());
                }
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buddies:
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.love:
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chatHistory:
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chatLocation:
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.meeting:
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.partner:
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.doctors:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new DoctorsFragment()).commit();
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.kennel:
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.spa:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new SpaFragment()).commit();
                Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home){
            Toast.makeText(DashBoardActivity.this, "Clicked", Toast.LENGTH_SHORT);
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
}
