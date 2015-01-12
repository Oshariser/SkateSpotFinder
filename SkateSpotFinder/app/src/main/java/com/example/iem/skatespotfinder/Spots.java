package com.example.iem.skatespotfinder;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/9/15.
 */
public class Spots {

    public static ArrayList<Spot> mSpots;

    public static List<Spot> getListSpots() {
        mSpots = new ArrayList<Spot>();
        ParseQuery lParseQuery = new ParseQuery("Spot");
        lParseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> aListParseObject, ParseException e) {
                mSpots = addSpotsToList(aListParseObject);
            }
        });
        return mSpots;
    }

    public static ArrayList<Spot> addSpotsToList(List<ParseObject> aListParseObject){
        ArrayList<Spot> lListSpots = new ArrayList<Spot>();
        for(ParseObject lParseObject : aListParseObject) {
            lListSpots.add(getSpotFromParseObject(lParseObject));
        }
        return lListSpots;
    }

    public static Spot getSpotFromParseObject(ParseObject aParseObject){
        double lLatitude = aParseObject.getParseGeoPoint("localisation").getLatitude();
        double lLongitude = aParseObject.getParseGeoPoint("localisation").getLongitude();
        byte[] lImage = new byte[0];
        try {
            lImage = aParseObject.getParseFile("photo").getData();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        float lRating = aParseObject.getInt("rating");
        String lDescription = aParseObject.getString("description");
        Spot lSpot = new Spot(lLatitude, lLongitude, lImage, lRating, lDescription);
        return lSpot;
    }
}
