package com.example.hp.friendsbook;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;



/**
 * Created by hp on 24/9/16.
 */
public class Friend {
    private String name;
    private String mobileNumber;
    private String tag;
//    private com.google.android.gms.maps.model.LatLng LatLng;
//    boolean isOnline;


    public Friend(String name, String mobileNumber, String tag) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.tag = tag;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

/*    public com.google.android.gms.maps.model.LatLng getLatLng() {
        return LatLng;
    }

    public void  setLatLng() {
        this.LatLng =  LatLng;
    }

    public void setLatLng(double lat, double lng) {
        LatLng = new LatLng(lat, lng);

    }
*/


}
