package com.example.thandiwe.miwork;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Thandiwe on 2017/07/11.
 */

public class WordAdapter extends ArrayAdapter<Word>{


    /**Resource ID for the background color for this list of words*/
    private int mColorResourceId;


    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId)
    {
       super(context,0,words);
        mColorResourceId = colorResourceId;

    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {


        //check if an existing viewis being reused, otherwise inflate the view

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        //get the word object located at this position in the list
        Word currentWord = getItem(position);

        //find the TextView in the list_item.xml layout with the id miwok_text_view
        TextView miwokTextView =(TextView)listItemView.findViewById(R.id.miwork_text_view);

        //get miwok translation from the currentWord object an set the text on the miwok Textview
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        //find the TextView in the list_item.xml layout with the id default_text_view
        TextView defaultTextView =(TextView)listItemView.findViewById(R.id.default_text_view);

        //get default translation from the currentWord object an set the text on the default Textview
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        //find the imageView in the list_item.xml layout with the ID IMAGE
        ImageView imageView =(ImageView)listItemView.findViewById(R.id.image);


        //Validating the image field.
        if(currentWord.hasImage())
        {
            //set the ImageView to the image resource specified in the current word
            imageView.setImageResource(currentWord.getmImageResourceId());


            //Make sure the image is visible
            imageView.setVisibility(View.VISIBLE);
        }

        else
        {
            //Gone set the image to hide and daz not take memory
            imageView.setVisibility(View.GONE);
        }

        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color thata the resource ID maps to
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        //Set background color of the text container View
        textContainer.setBackgroundColor(color);



        return listItemView;

    }
}
