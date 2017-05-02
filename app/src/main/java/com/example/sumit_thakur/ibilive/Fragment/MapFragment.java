package com.example.sumit_thakur.ibilive.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sumit_thakur.ibilive.Constants.Constants;
import com.example.sumit_thakur.ibilive.R;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;

/**
 * Created by SUMIT_THAKUR on 02/05/17.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, Constants {
    private GoogleMap mMap;
    private double mLatitude, mLongitude;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private String lat, lon;
    private String mPermission = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private String add = "";
    private SupportMapFragment mapFrag;
    private SupportMapFragment supportMapFragment;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map, container, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        //checkLocationPermission();
        return view;
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Error", "Sucess 8");
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Success Stuff here
                Log.e("Error", "Permission Granted");
                //showGPSDisabledAlertToUser();
                buildGoogleApiClient();
            } else {
                // Failure Stuff
                showGPSDisabledAlertToUser();
                Log.e("Error", "Error1");
            }
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (googleMap != null) {
            googleMap.clear();
            Log.e("Error", "Sucess 6");
            getAddress(mLatitude, mLongitude);
            mMap = googleMap;
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            LatLng india = new LatLng(mLatitude, mLongitude);
            mMap.addMarker(new MarkerOptions()
                    .position(india)
                    .title(String.valueOf(add))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(india));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(ZOOM_MAP));
            Log.e("Error", "Sucess 7");
        } else {
            Toast.makeText(getContext(), "googleMap is Empty", Toast.LENGTH_SHORT).show();
        }

        /*
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        */
    }

    /**
     * @param latt lat
     * @param lonn lan
     */
    private void init(final String latt, final String lonn) {
        mLatitude = Double.parseDouble(latt);
        mLongitude = Double.parseDouble(lonn);
        Log.e("Error", "changed");
    }

    @Override
    public void onConnected(final Bundle bundle) {
        Log.e("Error", "Sucess 3");
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(TH_M);
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        mLastLocation = FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            lat = String.valueOf(mLastLocation.getLatitude());
            lon = String.valueOf(mLastLocation.getLongitude());
            init(lat, lon);
            Log.e("Error", "Sucess 4");
            //mLatitude = Double.parseDouble(lat);
            //mLongitude = Double.parseDouble(lon);
        } else {
            showGPSDisabledAlertToUser();
        }
        updateLocation();
    }

    @Override
    public void onConnectionSuspended(final int i) {

    }

    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        buildGoogleApiClient();

    }

    @Override
    public void onLocationChanged(final Location location) {
        Log.e("Error", "Sucess 5");
        lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());
        init(lat, lon);
        Log.e("Error", "lat");
        Log.e("Error", lat);
        Log.e("Error", "lon");
        Log.e("Error", lon);

        mLatitude = Double.parseDouble(lat);
        mLongitude = Double.parseDouble(lon);
        //getAddress(mLatitude, mLongitude);
        updateLocation();
    }

    /**
     * check location permission
     */
    private void checkLocationPermission() {

        try {
            if (ActivityCompat.checkSelfPermission(getContext(), mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions((Activity) getContext(),
                        new String[]{mPermission}, REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will execute every time, else your else part will work
            } else {
                Log.e("Error", "Sucess 1");
                buildGoogleApiClient();
            }
        } catch (Exception e) {
            Log.e("Error", "Permission issue");
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Error", "Sucess 9");
        checkLocationPermission();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Error", "Sucess 11");
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Error", "Sucess 10");
        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    /**
     * build google Api
     */
    private synchronized void buildGoogleApiClient() {
        Log.e("Error", "Sucess 2");
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    /**
     * update the location
     */
    private void updateLocation() {
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void extra() {

    }

    /**
     * @param lati latitude
     * @param lng  longitude
     */
    public void getAddress(final double lati, final double lng) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lati, lng, 1);
            if (addresses != null && addresses.size() > 0) {
                Address obj = addresses.get(0);
                add = obj.getAddressLine(0);
                add = add + "\n" + obj.getCountryName();
                add = add + "\n" + obj.getCountryCode();
                add = add + "\n" + obj.getAdminArea();
                add = add + "\n" + obj.getPostalCode();
                add = add + "\n" + obj.getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Show alert and go to Settings
     */
    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                        mapFrag.getMapAsync(MapFragment.this);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}


