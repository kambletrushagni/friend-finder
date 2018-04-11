package com.example.hp.friendsbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.friendsbook.AddContactActivity;
import com.example.hp.friendsbook.R;
import com.example.hp.friendsbook.SearchFriendActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by hp on 24/3/17.
 */
public class GroupActivity extends Activity {
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
        setContentView(R.layout.activity_group);
        Log.d(TAG, "onCreate: ");
        //stringTextView = (TextView)findViewById(R.id.textView1);
       friendbutton = (Button) findViewById(R.id.viewbutton);
        stringTag = (Spinner) findViewById(R.id.nameoftag);
        manager = FriendsManager.getInstance();
        friends = manager.getFriends();


        friendbutton.setOnClickListener(new View.OnClickListener() {

            //@Override
         public void onClick(View view) {

             String selectTag = stringTag.getSelectedItem().toString();

                //setContentView(R.layout.actvity_textview);
                //stringTextView = (TextView)findViewById(R.id.textView1);
                showfriends();
                        }
        });


    }

    public void showfriends() {
        Log.d(TAG, "showfriends: ");
        stringTag = (Spinner) findViewById(R.id.nameoftag);
        Log.d(TAG, "showfriends: "+stringTag);
        tagstring = stringTag.getSelectedItem().toString();
        Log.d(TAG, "showfriends: " +tagstring);
      // stringTextView = (TextView) findViewById(R.id.textView1);
//        tagstring = stringTextView.getTag().toString();
        // Log.d(TAG, "onClick:hdbhcdjc ");

        //stringTextView.setText(stringTextView.getText());

        try {
            // Log.d(TAG, "onClick:hdbhcdjc ");

            Log.d(TAG, "onClick:hdbhcdjc " + friends.size());

            for (int j = 0; j < friends.size(); j++) {

                if (tagstring.equals(friends.get(j).getTag())) {


                    setContentView(R.layout.actvity_textview);
                    stringTextView = (TextView) findViewById(R.id.textView1);
                    Log.d(TAG, "onClick:hdbhcdjc fro" + friends.size() + "\ntag==" + tagstring);
                    Log.d(TAG, "showfriends: " + friends.get(j).getName());
                    phoneno = friends.get(j).getMobileNumber();
                    friends.get(j).getName();

                    Toast.makeText(this, "Tag Found" + friends.get(j).getName() + " " + tagstring, Toast.LENGTH_SHORT).show();
                    Log.d(tag, "Found j =" + friends.get(j).getName() + friends.get(j).getMobileNumber());
                    Log.i(tag, "Name: " + friends.get(j).getName());

                    stringTextView.setText(stringTextView.getText() + friends.get(j).getName() + " \t\t " + friends.get(j).getMobileNumber() + " \n\n ");


                    }
                else
                {
                    Toast.makeText(this,"Tag Not Found" +tagstring, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


/*
    private void showfriends(View view) {
        //TODO start add contact screen
        //Intent groupcontact=new Intent(getApplicationContext(),AddContactActivity.class);
        //startActivity(groupcontact);
        try {

            for (int j = 0; j < friends.size(); j++) {

                if (tagstring.equals(friends.get(j).getTag())) {

                    phoneno = friends.get(j).getMobileNumber();
                    friends.get(j).getName();
                    Toast.makeText(this, "Tag Found" + friends.get(j).getName() + " " + tagstring, Toast.LENGTH_SHORT).show();
                    Log.d(tag, "Found i =" + friends.get(j).getName() + friends.get(j).getMobileNumber());
                    Log.i(tag, "Name: " + friends.get(j).getName());

                    //stringTextView.isShown();
                    stringTextView.setText(stringTextView.getText() + friends.get(j).getName() + " , ");

                } else {
                    Toast.makeText(this, "Tag Not Found" + friends.get(j).getName() + " " + tagstring, Toast.LENGTH_SHORT).show();
                    Log.d(tag, "No Match Found For i = " + j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
/*
    public void startactivityfindFriend(View view) {
        //TODO start find f
        Intent tagintent = new Intent(this,SearchFriendActivity.class);
        startActivity(tagintent);


    }
    public  void addEvent()
    {
        //TODO
    }
}
    
  */
}
