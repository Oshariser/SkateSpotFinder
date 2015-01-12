package com.example.iem.skatespotfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;

/**
 * Created by iem on 09/01/15.
 */
public class Method {

    private void getFileBrowser(){
        Intent lIntent = new Intent();
        lIntent.setType("image/*");
        lIntent.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(Intent.createChooser(lIntent, "Select picture"), 1);
    }

    private Bitmap getPictureFromUri(Uri aUri){
        File lFile = new File(aUri.getPath());
        Bitmap lBitmap = null;
        if(lFile.exists()) {
            lBitmap = BitmapFactory.decodeFile(lFile.getAbsolutePath());
        }
        return lBitmap;
    }

    public static LatLng getCurrentLocalisation(Context aContext){
        LatLng lLatLng = null;
        LocationManager lLocationManager = null;
        LocationListener lLocationListener;
        lLocationManager = (LocationManager) aContext.getSystemService(Context.LOCATION_SERVICE);
        lLocationListener = new MyLocationListener();
        lLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lLocationListener);
        if (lLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lLatLng = new LatLng(MyLocationListener.mLatitude, MyLocationListener.mLongitude);
        }
        return lLatLng;
    }
}
