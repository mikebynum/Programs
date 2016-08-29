package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMenuItemActivity  extends Activity {
	
	// JSON Node names
	private static final String TAG_NAME = "name";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_ADDRESS2 = "address2";
	private static final String TAG_CITY = "city";
	private static final String TAG_STATE = "state";
	private static final String TAG_ZIP = "zip";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_LAT = "lat";
	private static final String TAG_LONG = "long";
	private static final String TAG_PHOTO = "photo";
	private static final String TAG_DESCRIPTION = "description";
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
        String address = in.getStringExtra(TAG_ADDRESS);
        String address2 = in.getStringExtra(TAG_ADDRESS2);
        String city = in.getStringExtra(TAG_CITY);
        String state = in.getStringExtra(TAG_STATE);
        String zip = in.getStringExtra(TAG_ZIP);
        String phone = in.getStringExtra(TAG_PHONE);
        String latitude = in.getStringExtra(TAG_LAT);
        String longitude = in.getStringExtra(TAG_LONG);
        String photo = in.getStringExtra(TAG_PHOTO);
        String description = in.getStringExtra(TAG_DESCRIPTION);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblAddress = (TextView) findViewById(R.id.address_label);
        TextView lblCity = (TextView) findViewById(R.id.city_label);
        TextView lblState = (TextView) findViewById(R.id.state_label);
        TextView lblAddress2 = (TextView) findViewById(R.id.address2_label);
        TextView lblZip = (TextView) findViewById(R.id.zip_label);
        TextView lblPhone = (TextView) findViewById(R.id.phone_label);
        TextView lblLat = (TextView) findViewById(R.id.lat_label);
        TextView lblLong = (TextView) findViewById(R.id.long_label);
        TextView lblPhoto = (TextView) findViewById(R.id.photo_label);
        TextView lblDescr = (TextView) findViewById(R.id.description_label);
        
      
        
        lblName.setText(name);
        lblAddress.setText(address);
        lblAddress2.setText(address2);
        lblCity.setText(city);
        lblState.setText(state);
        lblZip.setText(zip);
        lblPhone.setText(phone);
        lblLat.setText(latitude);
        lblLong.setText(longitude);
        lblPhoto.setText(photo);
        lblDescr.setText(description);
        
    }
}
