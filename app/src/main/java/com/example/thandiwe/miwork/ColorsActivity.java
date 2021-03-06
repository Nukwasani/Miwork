package com.example.thandiwe.miwork;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {



    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //creating an array of words
       final ArrayList<Word> words = new ArrayList<Word>();
        //Assigning  values in the arrayList
        words.add(new Word("red","wetetti",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("dusty yellow","topiise",R.drawable.color_dusty_yellow,R.raw.color_mustard_yellow));
        words.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_dusty_yellow));
        words.add(new Word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));



        WordAdapter adapter = new WordAdapter(this, words,R.color.category_colors);

        ListView listView =(ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = new MediaPlayer().create(ColorsActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });



    }



    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    //clean up the media player by releasing its resource

    public void releaseMediaPlayer()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
