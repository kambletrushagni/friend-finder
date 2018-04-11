package com.example.hp.friendsbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by hp on 19/4/17.
 */
public class mapintent extends FragmentActivity implements OnMapReadyCallback {
        private GoogleMap mMap;
private static final int PLACE_PICKER_REQUEST = 1000;
private GoogleApiClient mClient;
private Place place;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        int PLACE_PICKER_REQUEST = 1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();


        try {
        startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
        e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
        e.printStackTrace();
        }
        mClient = new GoogleApiClient
        .Builder(this)
        .addApi(Places.GEO_DATA_API)
        .addApi(Places.PLACE_DETECTION_API)
        .build();



        }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
        if (resultCode == RESULT_OK) {
        Place place = PlacePicker.getPlace(data, this);
        String placeName = String.format("Place: %s", place.getName());
        String placeAddress =  String.format("Address: %s", place.getAddress());
        LatLng toLatLng = place.getLatLng();

        // Add Marker
        Marker marker = mMap.addMarker(new MarkerOptions().position(toLatLng)
        .title(placeName).snippet(placeAddress)
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        // Move Camera to selected place
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toLatLng,11));

        }
        }
        }

@Override
public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng pune = new LatLng(18.5334, 73.88);

        mMap.addMarker(new MarkerOptions().position(pune).title("Ashwini"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pune));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

        }
@Override
protected void onStart() {
        super.onStart();
        mClient.connect();
        }
@Override
protected void onStop() {
        mClient.disconnect();
        super.onStop();
        }

        }