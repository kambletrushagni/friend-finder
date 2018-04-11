package com.example.hp.friendsbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hp.friendsbook.activity.ChatActivity;

/**
 * Created by hp on 20/4/17.
 */
public class BluetoothActivity extends Activity {

        private static final String TAG = "BluetoothMainActivity";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.actvity_find_friend);
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

        public void startactivitybluetooth(View view) {
            //TODO start find f
            Intent bluetoothintent = new Intent(getApplicationContext(), ChatActivity.class);

            startActivity(bluetoothintent);


        }
        public  void addEvent()
        {
            //TODO
        }
    }



