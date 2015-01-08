package com.example.iem.skatespotfinder;

/**
 * Created by root on 1/8/15.
 */
public class Spot {
    private double mLatitude;
    private double mLongitude;
    private byte[] mImage;
    private float mRating;
    private String mDescription;

    public Spot(double aLatitude, double aLongitude, byte[] aImage, float aRating, String aDescription) {
        this.setLatitude(aLatitude);
        this.setLongitude(aLongitude);
        this.setImage(aImage);
        this.setRating(aRating);
        this.setDescription(aDescription);
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public byte[] getImage() {
        return mImage;
    }

    public void setImage(byte[] mImage) {
        this.mImage = mImage;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float mRating) {
        this.mRating = mRating;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
