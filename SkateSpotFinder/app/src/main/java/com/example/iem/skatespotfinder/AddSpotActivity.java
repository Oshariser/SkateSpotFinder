package com.example.iem.skatespotfinder;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationListener;
import android.location.LocationManager;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.io.File;


public class AddSpotActivity extends Activity {

    private double mLatitude;
    private double mLongitude;
    private Uri mUri;
    private static final int TAKE_PICTURE = 1;
    private Button mButtonGetLocalisation;
    private TextView mTextViewLatitude;
    private TextView mTextViewLongitude;
    private ImageView mImageViewSpot;
    private TextView mTextViewUri;
    private Button mButtonBrowse;
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
                getCurrentLocalisation();
            }
        });
        mTextViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        mTextViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
        mImageViewSpot = (ImageView)findViewById(R.id.imageViewSpot);
        mTextViewUri = (TextView) findViewById(R.id.textViewUri);
        mButtonBrowse = (Button) findViewById(R.id.buttonBrowse);
        mButtonBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
        mRatingBarSpot = (RatingBar)findViewById(R.id.ratingBarSpot);
        mEditTextDescription = (EditText)findViewById(R.id.editTextDescription);
        mButtonAddSpot = (Button)findViewById(R.id.buttonAddSpot);
        mButtonAddSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spot lSpot = new Spot(mLatitude, mLongitude, null, mRatingBarSpot.getRating(), mEditTextDescription.getText().toString());
                addSpot(lSpot);
            }
        });
    }

    private void takePicture() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        mUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_spot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addSpot(Spot aSpot){
        ParseObject lParseObject = new ParseObject("Spot");
        lParseObject.put("localisation", new ParseGeoPoint(aSpot.getLatitude(), aSpot.getLongitude()));
        //lParseObject.put("image", aSpot.getImage());
        lParseObject.put("rating", aSpot.getRating());
        lParseObject.put("description", aSpot.getDescription());
        lParseObject.saveInBackground();
    }

    private void getCurrentLocalisation(){
        LocationManager lLocationManager = null;
        LocationListener lLocationListener;
        lLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lLocationListener = new MyLocationListener();
        lLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lLocationListener);
        if (lLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mLatitude = MyLocationListener.mLatitude;
            mLongitude = MyLocationListener.mLongitude;
            mTextViewLatitude.setText("Latitude: - " + mLatitude);
            mTextViewLongitude.setText("Longitude: - " + mLongitude);
        } else {
            Toast.makeText(AddSpotActivity.this, "GPS is not turned on ...", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    setImageSpot();
                }
        }

    }

    private void setImageSpot() {
        Uri lUri = mUri;
        getContentResolver().notifyChange(lUri, null);
        ContentResolver lContentResolver = getContentResolver();
        Bitmap lBitmap;
        try {
            lBitmap = MediaStore.Images.Media
                    .getBitmap(lContentResolver, lUri);

            mImageViewSpot.setImageBitmap(lBitmap);
            Toast.makeText(this, lUri.toString(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                    .show();
            Log.e("Camera", e.toString());
        }
    }

}