package com.example.hp.friendsbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hp on 25/3/17.
 */
public class ViewActivity extends Activity {


    private static final String TAG = "GroupActivity";
    private final static String tag = "SEARCHED TAG";
    public String tagstring = "";
    public String phoneno;
    public TextView stringTextView, searchPhoneNo;
    public Spinner stringTag;
    FriendsManager manager;
    Button friendbutton;
    ArrayList<Friend> friends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_textview);
        manager = FriendsManager.getInstance();
        friends = manager.getFriends();

        shownfriends();

    }

    private void shownfriends() {


        stringTag = (Spinner) findViewById(R.id.nameoftag);
        tagstring = stringTag.getTag().toString();

        // stringTextView = (TextView) findViewById(R.id.textView1);
//        tagstring = stringTextView.getTag().toString();
        // Log.d(TAG, "onClick:hdbhcdjc ");

        //stringTextView.setText(stringTextView.getText());

        try {
            // Log.d(TAG, "onClick:hdbhcdjc ");

            Log.d(TAG, "onClick:hdbhcdjc " + friends.size());

            for (int j = 0; j < friends.size(); j++) {

                if (tagstring.equals(friends.get(j).getTag())) {

                    Log.d(TAG, "onClick:hdbhcdjc fro" + friends.size() + "\ntag==" + tagstring);
                    Log.d(TAG, "showfriends: " + friends.get(j).getName());
                    phoneno = friends.get(j).getMobileNumber();
                    friends.get(j).getName();

                    Toast.makeText(this, "Tag Found" + friends.get(j).getName() + " " + tagstring, Toast.LENGTH_SHORT).show();
                    Log.d(tag, "Found j =" + friends.get(j).getName() + friends.get(j).getMobileNumber());
                    Log.i(tag, "Name: " + friends.get(j).getName());

                    //stringTextView.isShown();
                    stringTextView.setText(stringTextView.getText() + friends.get(j).getName() + " \t\t " + friends.get(j).getMobileNumber() + " \n\n ");


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
