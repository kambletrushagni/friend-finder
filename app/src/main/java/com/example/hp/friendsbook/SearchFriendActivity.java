package com.example.hp.friendsbook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.friendsbook.R;
import com.example.hp.friendsbook.activity.ChatActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by hp on 18/9/16.
 */
public class SearchFriendActivity extends Activity {
    Button searchbutton, messagebutton, Callbutton, mapbutton, n;
    public String tagstring = "";
    public String phoneno;
    private final static String tag = "SEARCHED TAG";
    public TextView stringTextView, searchPhoneNo;
    public Spinner searchtag;
    FriendsManager manager;
    ArrayList<Friend> friends;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    //  Intent sendmessage;
/*
    searchbutton = (Button) findViewById(R.id.searchbutton);
    messagebutton = (Button) findViewById(R.id.smsbutton);
    Callbutton = (Button) findViewById(R.id.callbutton);
    mapbutton = (Button) findViewById(R.id.mbutton);
    stringTextView = (TextView)findViewById(R.id.textView1);
    manager = FriendsManager.getInstance();
    friends = manager.getFriends();
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_find_friend);
        searchtag = (Spinner) findViewById(R.id.nameoftag);
        tagstring = searchtag.getSelectedItem().toString();
        searchbutton = (Button) findViewById(R.id.searchbutton);
        messagebutton = (Button) findViewById(R.id.smsbutton);
        Callbutton = (Button) findViewById(R.id.callbutton);
        mapbutton = (Button) findViewById(R.id.mbutton);
        stringTextView = (TextView) findViewById(R.id.textView1);
        manager = FriendsManager.getInstance();
        friends = manager.getFriends();

        searchbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                messagebutton.setVisibility(View.VISIBLE);
                Callbutton.setVisibility(View.VISIBLE);
                mapbutton.setVisibility(View.VISIBLE);
                stringTextView.setVisibility(View.VISIBLE);
                addtagtoString(view);

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void addtagtoString(View view) {
        searchtag = (Spinner) findViewById(R.id.nameoftag);
        tagstring = searchtag.getSelectedItem().toString();

        try {

            for (int j = 0; j < friends.size(); j++) {

                if (tagstring.equals(friends.get(j).getTag())) {

                    phoneno = friends.get(j).getMobileNumber();
                    friends.get(j).getName();
                    Toast.makeText(this, "Tag Found" + friends.get(j).getName() + " " + tagstring, Toast.LENGTH_SHORT).show();
                    Log.d(tag, "Found i =" + friends.get(j).getName() + friends.get(j).getMobileNumber());
                    Log.i(tag, "Name: " + friends.get(j).getName());

                    //stringTextView.isShown();

                    stringTextView.setText(stringTextView.getText() + friends.get(j).getName() + "\n \n");

                } else {
                    Toast.makeText(this, "Tag Not Found" + friends.get(j).getName() + " " + tagstring, Toast.LENGTH_SHORT).show();
                    Log.d(tag, "No Match Found For i = " + j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // sendmessage = new Intent(SearchFriendActivity.this, SmsActivity.class);
        // startActivity(sendmessage);

    }


    public void sendSMS(View view) {


        Log.d(tag, "sendSMS: " + phoneno);
        Intent sendsmsintent = new Intent();
        sendsmsintent.setAction(Intent.ACTION_VIEW);
        sendsmsintent.setData(Uri.parse("smsto:" + phoneno));
        Log.d(tag, "sendSMS: " + sendsmsintent);

        if (sendsmsintent.equals(null)) {

            Toast.makeText(this, " Application Not Found ", Toast.LENGTH_SHORT).show();

        } else {
            startActivity(sendsmsintent);
        }
        Toast.makeText(SearchFriendActivity.this, "TODO Open SMS App", Toast.LENGTH_SHORT).show();

    }

    public void Callfriend(View view) {
        Intent sendcallintent = new Intent();
        sendcallintent.setAction(Intent.ACTION_DIAL);//Uri.parse("Call To:"  + phoneno));
        sendcallintent.setData(Uri.parse("tel:" + phoneno));
        if (sendcallintent.resolveActivity(getPackageManager()) != null) {
            Log.d(tag, "Call to: " + phoneno);
            startActivity(sendcallintent);
        } else {
            Toast.makeText(this, " Application Not Found ", Toast.LENGTH_SHORT).show();
        }
    }

    public void Mapfriend(View view)

    {
        Intent mintent = new Intent(this, mapintent.class);
        try {

        startActivity(mintent);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    }
    public void startactivitybluetooth(View view)

    {
        Intent bintent = new Intent(this, ChatActivity.class);
        try {

            startActivity(bintent);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
/*
    public void Mappfriend(View view) {
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {

// Attempt to start an activity that can handle the Intent
            startActivity(mapIntent);

        } else {
            Toast.makeText(this, " Application Not Found ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SearchFriend Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.hp.friendsbook/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SearchFriend Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.hp.friendsbook/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }*/

}
