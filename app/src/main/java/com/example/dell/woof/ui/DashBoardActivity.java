package com.example.dell.woof.ui;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.fragments.DoctorsFragment;
import com.example.dell.woof.fragments.FeedBackFragment;
import com.example.dell.woof.fragments.HomeFragment;
import com.example.dell.woof.fragments.KennelFragment;
import com.example.dell.woof.fragments.LegalFragment;
import com.example.dell.woof.fragments.MyBuddyFragment;
import com.example.dell.woof.fragments.MyLoveFragment;
import com.example.dell.woof.fragments.MyProfileFragment;
import com.example.dell.woof.fragments.PartnerWithUsFragment;
import com.example.dell.woof.fragments.SpaFragment;
import com.example.dell.woof.fragments.StoreFragment;
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
    android.app.Fragment fragment = null;
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
        fragmentManager = getFragmentManager();
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
        fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
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
        bundle = new Bundle();
        if (mLastLocation != null){
            bundle.putDouble("latitude", mLastLocation.getLatitude());
            bundle.putDouble("longitude", mLastLocation.getLongitude());
        }
        if (fragmentManager.findFragmentById(R.id.content_frame) instanceof HomeFragment){
            fragment = new HomeFragment();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Bundle bundle = new Bundle();
        switch (item.getItemId()){
            case R.id.home:
                toolbar.setTitle("Home");
                fragment = new HomeFragment();
                if (mLastLocation != null){
                    bundle.putDouble("latitude", mLastLocation.getLatitude());
                    bundle.putDouble("longitude", mLastLocation.getLongitude());
                }
                fragment.setArguments(bundle);
                break;
            case R.id.profile:
                toolbar.setTitle("My Profile");
                fragment = new MyProfileFragment();
                if (mLastLocation != null){
                    bundle.putDouble("latitude", mLastLocation.getLatitude());
                    bundle.putDouble("longitude", mLastLocation.getLongitude());
                }
                fragment.setArguments(bundle);
                break;
            case R.id.doctors:
                toolbar.setTitle("My Doctors");
                fragment = new DoctorsFragment();
                break;
            case R.id.kennel:
                toolbar.setTitle("My Kennel");
                fragment = new KennelFragment();
                break;
            case R.id.spa:
                toolbar.setTitle("My Spa");
                fragment = new SpaFragment();
                break;
            case R.id.store:
                toolbar.setTitle("My Store");
                fragment = new StoreFragment();
                break;
            case R.id.buddies:
                toolbar.setTitle("My Buddies");
                fragment = new MyBuddyFragment();
                break;
            case R.id.love:
                toolbar.setTitle("My Love");
                fragment = new MyLoveFragment();
                break;
            case R.id.chatHistory:
                toolbar.setTitle("My Chat");
                break;
            case R.id.chatLocation:
                break;
            case R.id.addDog:
                toolbar.setTitle("Add Dog");
                break;
            case R.id.partner:
                toolbar.setTitle("Partner With Us");
                fragment = new PartnerWithUsFragment();
                break;
            case R.id.feedback:
                toolbar.setTitle("Feedback");
                fragment = new FeedBackFragment();
                break;
            case R.id.legal:
                toolbar.setTitle("Legal & About Us");
                fragment = new LegalFragment();
                break;
            case R.id.Logout:
                WoofApplication.getWoofApplication().logout();
                Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        drawerLayout.closeDrawers();
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }
}
