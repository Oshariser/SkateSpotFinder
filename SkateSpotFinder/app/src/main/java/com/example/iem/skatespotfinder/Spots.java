package com.example.iem.skatespotfinder;

import android.net.Uri;
import android.util.Log;


import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/9/15.
 */
public class Spots {

    private static byte[] mData;
    private static final String TAG = "Spots";
    public static List<Spot> mSpots = new ArrayList<Spot>();
/*
    public static List<Spot> getRemoteSpots() {
        ParseQuery query = new ParseQuery("Spot");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> aList, ParseException e) {
                if (e == null) {
                    try {
                        fillSpots(aList);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        return mSpots;
    }

    public static void fillSpots(List<ParseObject> aList) throws IOException, ParseException {
        for(ParseObject item : aList) {
            mSpots.add(parseObjectToSpot(item));
        }
    }

    public static Spot parseObjectToSpot(ParseObject aParseObject) throws ParseException, IOException {
        Spot lSpot = new Spot(
                aParseObject.getParseGeoPoint("localisation").getLatitude(),
                aParseObject.getParseGeoPoint("localisation").getLongitude(),
                getFileFromParseFile(aParseObject.getParseFile("photo")),
                aParseObject.getInt("rating"),
                aParseObject.getString("description")
        );
        return lSpot;
    }

    public static File getFileFromParseFile(ParseFile aParseFile) throws IOException {
        File lFile = null;
        aParseFile.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    mData = data;
                }
                else {
                    // something went wrong
                }
            }
        });
        BufferedOutputStream lBufferedOutputStream = null;
        try {
            FileOutputStream lFileOutputStream = new FileOutputStream(lFile);
            lBufferedOutputStream = new BufferedOutputStream(lFileOutputStream);
            lBufferedOutputStream.write(mData);
        }
        finally {
            if(lBufferedOutputStream != null) {
                try  {
                    lBufferedOutputStream.flush();
                    lBufferedOutputStream.close();
                }
                catch(Exception e){}
            }
        }
        return lFile;
    }
    */
}
