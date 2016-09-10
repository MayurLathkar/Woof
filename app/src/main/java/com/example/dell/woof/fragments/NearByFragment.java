package com.example.dell.woof.fragments;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.woof.Manifest;
import com.example.dell.woof.R;
import com.example.dell.woof.adapters.MyInfoWindowAdapter;
import com.example.dell.woof.model.DoctorsDetails;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Dell on 09-08-2016.
 */
public class NearByFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Marker currLocationMarker;
    LatLng latLng;
    GoogleMap mGoogleMap;
    ArrayList<DoctorsDetails> doctorsDetailses;
    String type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, null);
        type = getArguments().getString("type");
        if(type.equals("Veterinary"))
            doctorsDetailses = (ArrayList<DoctorsDetails>) getArguments().getSerializable("doctors");
        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setTrafficEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.MAPS_RECEIVE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.MAPS_RECEIVE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.setBuildingsEnabled(true);
        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    protected synchronized void buildGoogleApiClient() {
//        Toast.makeText(getContext(),"buildGoogleApiClient",Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
//        Toast.makeText(getContext(),"onConnected",Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.MAPS_RECEIVE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.MAPS_RECEIVE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(6000000); //60 seconds
        mLocationRequest.setFastestInterval(600000); //60 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }


    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getContext(),"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getContext(),"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        if (type.equals("Veterinary")){
            if ( doctorsDetailses.size() > 0)
                setNearestDoctorsOnMap();
        }
        else if (type.equals("Spa"))
            setNearestSpaOnMap();

    }

    private void setNearestDoctorsOnMap(){
        latLng = new LatLng(doctorsDetailses.get(0).getLocation().get(0), doctorsDetailses.get(0).getLocation().get(1));
        mGoogleMap.setInfoWindowAdapter(new MyInfoWindowAdapter(getContext()));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(doctorsDetailses.get(0).getDtrName());
        markerOptions.snippet(doctorsDetailses.get(0).getDtrAvailability());
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currLocationMarker = mGoogleMap.addMarker(markerOptions);
        if (!currLocationMarker.isInfoWindowShown())
            currLocationMarker.showInfoWindow();
        //zoom to current position:
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(14).build();

        mGoogleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mGoogleApiClient.disconnect();
    }

    private void setNearestSpaOnMap(){

    }
}
