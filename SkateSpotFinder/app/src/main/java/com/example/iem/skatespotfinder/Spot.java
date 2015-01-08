package com.example.iem.skatespotfinder;

import android.media.Image;

/**
 * Created by root on 1/8/15.
 */
public class Spot {
    private byte[] mImage;
    private int mRating;
    private String mDescription;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int mRating) {
        this.mRating = mRating;
    }

    public byte[] getImage() {
        return mImage;
    }

    public void setImage(byte[] mImage) {
        this.mImage = mImage;
    }

    public Spot(byte[] aImage, int aRating, String aDescription) {
        this.mImage = aImage;
        this.mRating = aRating;
        this.mDescription = aDescription;

    }
}
