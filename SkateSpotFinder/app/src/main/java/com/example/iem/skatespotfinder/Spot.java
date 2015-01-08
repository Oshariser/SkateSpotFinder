package com.example.iem.skatespotfinder;

import android.media.Image;

/**
 * Created by root on 1/8/15.
 */
public class Spot {
    private byte[] mImage;
    private int mRating;
    private String mDescription;

    public Spot(byte[] aImage, int aRating, String aDescription) {
        this.mImage = aImage;
        this.mRating = aRating;
        this.mDescription = aDescription;
    }
}
