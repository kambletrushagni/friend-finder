package com.example.hp.friendsbook;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class markerdemo extends FragmentActivity implements LocationListener {
    private GoogleMap map;
    private LocationManager locationManager;
    private String provider;
    public static String TAG="markerdemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap)).getMap();
        map.setMyLocationEnabled(true);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Log.d(TAG, "onCreate:service "+service);
        boolean enabledGPS = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d(TAG, "onCreate: GPS"+enabledGPS);
        boolean enabledWiFi = service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // Check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabledGPS) {
            Toast.makeText(this, "GPS signal not found", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
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
        Location location = locationManager.getLastKnownLocation(provider);
        Log.d(TAG, "onCreate: location"+location);

        // Initialize the location fields
        if (location != null) {
            Toast.makeText(this, "Selected Provider " + provider, Toast.LENGTH_SHORT).show();
            onLocationChanged(location);

        } else {

            Log.d(TAG, "onCreate: else");
        }

    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
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
        locationManager.requestLocationUpdates(provider, 1000, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
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
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat =  location.getLatitude();
        double lng = location.getLongitude();

        map.clear(); //Remove all marker from map before refresh it.

        LatLng latLng = new LatLng(lat, lng);

        Toast.makeText(this, "Location " + lat+","+lng,Toast.LENGTH_SHORT).show();
        LatLng coordinate = new LatLng(lat, lng);
        Toast.makeText(this, "Location " + coordinate.latitude+","+coordinate.longitude,Toast.LENGTH_SHORT).show();

         map.addMarker(new MarkerOptions().position(coordinate).title("Title").snippet("Snippet").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        // Zoom in the Google Map
        map.animateCamera(CameraUpdateFactory.zoomTo(15));


    }


    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

}