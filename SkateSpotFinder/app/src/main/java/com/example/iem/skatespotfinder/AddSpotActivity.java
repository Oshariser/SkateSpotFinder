package com.example.iem.skatespotfinder;

import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;


public class AddSpotActivity extends Activity {
    private LatLng mLatLng;

    private Button mButtonGetLocalisation;
    private TextView mTextViewLatitude;
    private TextView mTextViewLongitude;
    private ImageView mImageViewSpot;
    private RatingBar mRatingBarSpot;
    private EditText mEditTextDescription;
    private Button mButtonAddSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spot);

        mButtonGetLocalisation = (Button) findViewById(R.id.buttonGetLocalisation);
        mButtonGetLocalisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager mLocationManager=null;
                LocationListener mLocationListener;
                mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                mLocationListener = new MyLocationListener();
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);

                if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    //if(MyLocationListener.mLatitude>0)
                    //{
                        mTextViewLatitude.setText("Latitude:- " + MyLocationListener.mLatitude);
                        mTextViewLongitude.setText("Longitude:- " + MyLocationListener.mLongitude);
                    /*}
                    else
                    {
                        Toast.makeText(AddSpotActivity.this, "GPS in progress, please wait.", Toast.LENGTH_LONG).show();
                    }*/
                } else {
                    Toast.makeText(AddSpotActivity.this, "GPS is not turned on...", Toast.LENGTH_LONG).show();
                }
            }
        });
        mTextViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        mTextViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
        mImageViewSpot = (ImageView)findViewById(R.id.imageViewSpot);
        mRatingBarSpot = (RatingBar)findViewById(R.id.ratingBarSpot);
        mEditTextDescription = (EditText)findViewById(R.id.editTextDescription);
        mButtonAddSpot = (Button)findViewById(R.id.buttonAddSpot);
        mButtonAddSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Spot lSpot = new Spot();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_spot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addSpot(Spot aSpot){
        ParseObject lParseObject = new ParseObject("Spot");
        lParseObject.put("localisation", new ParseGeoPoint(aSpot.getLatitude(), aSpot.getLongitude()));
        lParseObject.put("image", aSpot.getImage());
        lParseObject.put("rating", aSpot.getRating());
        lParseObject.put("description", aSpot.getDescription());
        lParseObject.saveInBackground();
    }
}
