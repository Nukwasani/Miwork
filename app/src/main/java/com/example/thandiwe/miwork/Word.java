package com.example.thandiwe.miwork;

/**
 * Created by Thandiwe on 2017/07/11.
 */

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;

    private int mImageResourceId =NO_IMAGE_PROVIDED;
    private  static final int NO_IMAGE_PROVIDED = -1;

    //Audio resource ID for the word
    private int mAudioResourceId;


    public Word() {
    }
    //Created a Word object constructor
    public  Word(String defaultTranslation, String miwokTranslation, int audioResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    //Created a Word object constructor to add image
    public  Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    //get the default translation of the word
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    //get the miwok translation of the word
    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    //Returns the image resource id of the word

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Return whether or not there is an image for this word
     * @return
     */
    public boolean hasImage()
    {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    //Return the audio resource ID of the word
    public int getmAudioResourceId() {
        return mAudioResourceId;
    }
}
