package com.example.hp.friendsbook;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void startactivityaddContact(View view) {
        //TODO start add contact screen
        Intent addcontact=new Intent(getApplicationContext(),AddContactActivity.class);
        startActivity(addcontact);

    }

    public void startactivityfindFriend(View view) {
        //TODO start find f
        Intent tagintent = new Intent(this,SearchFriendActivity.class);
        startActivity(tagintent);


    }

    public void startactivityviewgroup(View view) {
        //TODO start find f
        Intent groupintent = new Intent(getApplicationContext(),GroupActivity.class);

            startActivity(groupintent);


    }
    public  void addEvent()
    {
        //TODO
    }
}