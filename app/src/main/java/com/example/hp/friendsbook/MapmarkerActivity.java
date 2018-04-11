package com.example.hp.friendsbook;

/**
 * Created by hp on 27/3/17.
 */

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.friendsbook.DbHelperFrndList;
import com.example.hp.friendsbook.Friend;
import com.example.hp.friendsbook.FriendsManager;
import com.example.hp.friendsbook.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapmarkerActivity extends FragmentActivity implements LocationListener {

    public static final int MY_LOCATION_REQUEST_CODE = 200;
    public static long MIN_TIME_BETWN_UPDATES = 1; // 1 minute
    public static float MIN_DIST_CHNG = 1.0f; // 10 mitures
    DbHelperFrndList dbHelperFrndList;
    SharedPreferences prefs;
    //my location, trying to get from location service
    Location location = null;
    private static String TAG="MapmarkerActivity";

    //my latitude and longitude
    //LatLng latitude = null;
    //LatLng longitude = null;


    GoogleMap googleMap;
    FriendsManager manager;
    ArrayList<Friend> friends;
    String phone;
    String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = FriendsManager.getInstance();
        friends = manager.getFriends();

        //show error dialog if GooglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        setContentView(R.layout.activity_my);
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        googleMap = supportMapFragment.getMap();
        googleMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Log.d(TAG, "onCreate: lm"+locationManager);
        Criteria criteria = new Criteria();
        Log.d(TAG, "onCreate:criteria "+criteria);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Log.d(TAG, "onCreate: bp--" +bestProvider);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        Log.d(TAG, "onCreate: loc---"+location);
        Log.d(TAG, "onCreate: ");
      //  onLocationChanged(location);

        if (location == null) {

            onLocationChanged(location);
            Log.d(TAG, "location if: ");

        }
        else
        {

            Log.d(TAG, "location else:   ");
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        /*
        phoneno = friends.get(j).getMobileNumber();
        friends.get(j).getName();
        */

        TextView locationTv = (TextView) findViewById(R.id.latlongLocation);

       double latitude = location.getLatitude();
       double longitude = location.getLongitude();
       // double latitude=2.88;
       // double longitude=3.88;
        Log.d(TAG, "onLocationChanged: latitude--"+latitude+"longitude--"+longitude);
        LatLng latLng = new LatLng(latitude, longitude);
        for (int j = 0; j < friends.size(); j++) {
            phone = friends.get(j).getMobileNumber();
            name = friends.get(j).getName();
            googleMap.addMarker(new MarkerOptions().position(latLng).title("" + name + "\t" + phone));
            Log.d(TAG, "onLocationChanged: "+name+"----------"+phone);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory     .zoomTo(15));
            googleMap.isMyLocationEnabled();

            locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);

        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub4

    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }



}