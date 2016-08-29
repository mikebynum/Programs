package com.android;

import java.util.ArrayList;
import java.util.HashMap;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class AndroidJSONTestActivity extends ListActivity 
{
	// url to make request
	private static String url = "http://vulcan.ist.unomaha.edu/~mbynum/web_service/get_business.php";
	
	// JSON Node names
	private static final String TAG_BUSINESSES = "Businesses";
	private static final String TAG_ID = "id";
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

	
	// users JSONArray
	JSONArray businesses = null;
	
	
	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        // Hashmap for ListView
        ArrayList<HashMap<String, String>> businessList = new ArrayList<HashMap<String, String>>();
 
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url);
 
        try {
            // Getting Array of businesses
            businesses = json.getJSONArray(TAG_BUSINESSES);
 
            // looping through All businesses
            for(int i = 0; i < businesses.length(); i++){
                JSONObject c = businesses.getJSONObject(i);
 
                // Storing each json item in variable
                String id = c.getString(TAG_ID);
                String name = c.getString(TAG_NAME);
                String address = c.getString(TAG_ADDRESS);
                String address2 = c.getString(TAG_ADDRESS2);
                String city = c.getString(TAG_CITY);
                String state = c.getString(TAG_STATE);
                String zip = c.getString(TAG_ZIP);
                String phone = c.getString(TAG_PHONE);
                String latitude = c.getString(TAG_LAT);
                String longitude = c.getString(TAG_LONG);
                String photo = c.getString(TAG_PHOTO);
                String description = c.getString(TAG_DESCRIPTION);
 
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
 
                // adding each child node to HashMap key => value
                map.put(TAG_NAME, name);
                map.put(TAG_ADDRESS, address);
                map.put(TAG_CITY, city);
                map.put(TAG_STATE, state);
                map.put(TAG_ZIP, zip);
                map.put(TAG_PHONE, phone);
                map.put(TAG_LAT, latitude);
                map.put(TAG_LONG, longitude);
                map.put(TAG_PHOTO, photo);
                map.put(TAG_DESCRIPTION, description);
                map.put(TAG_ADDRESS2, address2);
 
                // adding HashList to ArrayList
                businessList.add(map);
            }
        } 
        catch (JSONException e) 
        {
            e.printStackTrace();
        }
 
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(this, businessList, R.layout.list_item,
                new String[] { TAG_NAME, TAG_ADDRESS, TAG_ADDRESS2, TAG_CITY, TAG_STATE, TAG_ZIP, TAG_PHONE, TAG_LAT, TAG_LONG, TAG_PHOTO, TAG_DESCRIPTION  }, new int[] {R.id.name, R.id.address, 
        		R.id.address2, R.id.city, R.id.state, R.id.zip, R.id.phone, R.id.latitude, R.id.longitude, R.id.photo, R.id.description });
 
        setListAdapter(adapter);
 
        // selecting single ListView item
        ListView lv = getListView();
 
        // Launching new screen on Selecting Single ListItem
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
 
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
            {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                String address = ((TextView) view.findViewById(R.id.address)).getText().toString();
                String address2 = ((TextView) view.findViewById(R.id.address2)).getText().toString();
                String city = ((TextView) view.findViewById(R.id.city)).getText().toString();
                String state = ((TextView) view.findViewById(R.id.state)).getText().toString();
                String zip = ((TextView) view.findViewById(R.id.zip)).getText().toString();
                String phone = ((TextView) view.findViewById(R.id.phone)).getText().toString();
                String latitude = ((TextView) view.findViewById(R.id.latitude)).getText().toString();
                String longitude = ((TextView) view.findViewById(R.id.longitude)).getText().toString();
                String photo = ((TextView) view.findViewById(R.id.photo)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.description)).getText().toString();
                
   

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_ADDRESS, address);
                in.putExtra(TAG_ADDRESS2, address2);
                in.putExtra(TAG_CITY, city);
                in.putExtra(TAG_STATE, state);
                in.putExtra(TAG_ZIP, zip);
                in.putExtra(TAG_PHONE, phone);
                in.putExtra(TAG_LAT, latitude);
                in.putExtra(TAG_LONG, longitude);
                in.putExtra(TAG_PHOTO, photo);
                in.putExtra(TAG_DESCRIPTION, description);
                startActivity(in);
            }
        });
    }
 
}
