package com.example.hp.friendsbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hp on 24/9/16.
 */
public class AddContactActivity extends Activity{

    private static final int RESULT_PICK_CONTACT = 85500;
    private String phoneNo = null, name = null, Tag = null;

    private TextView textViewName;
    private TextView textViewPhoneNumber;
    private Spinner tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        //  Log.d(TAG, "onCreate: ");
        textViewName = (TextView) findViewById(R.id.textView1);
        textViewPhoneNumber = (TextView) findViewById(R.id.textView2);
        tag = (Spinner) findViewById(R.id.Tag);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textViewName.getText().toString();
                String phone = textViewPhoneNumber.getText().toString();
                String selectedTag = tag.getSelectedItem().toString().trim();
                String selectTag = tag.getSelectedItem().toString().trim();

                Intent intent = new Intent(AddContactActivity.this, SaveContactActivity.class);
                intent.putExtra("person_name", name);
                intent.putExtra("phone_number", phone);
                intent.putExtra("Tag", selectTag);

                // TODO Add this contact in FrindManager class

                finish();
                saveContact(view);
                startActivity(intent);

            }

        });


    }

    public void pickContact(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }

        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null;
            String name = null;
            String Tag = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();


            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);


            textViewName.setText(name);
            textViewPhoneNumber.setText(phoneNo);
             tag.setTag(Tag);
            Toast.makeText(this, name + " " + phoneNo, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void saveContact(View view) {
        FriendsManager friendobject = FriendsManager.getInstance();
        String name = textViewName.getText().toString();
        String phone = textViewPhoneNumber.getText().toString();
        String selecttag = tag.getSelectedItem().toString();
        Friend friendsingle = new Friend(name, phone, selecttag);

        if (friendobject.addFriend(friendsingle) == true) {
            Toast.makeText(this, "\t" + friendsingle.getName() + friendsingle.getMobileNumber() + friendsingle.getTag(), Toast.LENGTH_SHORT).show();
        }
    }






}
