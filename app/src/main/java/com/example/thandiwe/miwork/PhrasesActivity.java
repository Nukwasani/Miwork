package com.example.thandiwe.miwork;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {



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
        words.add(new Word("where are you going","minto wuksus",R.raw.phrase_where_are_you_going ));
        words.add(new Word("tinna","what is your name",R.raw.phrase_what_is_your_name));
        words.add(new Word("oyaaset","my name is",R.raw.phrase_my_name_is));
        words.add(new Word("michaksas","how are you feeling",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("kuchi achit","i'm feeling good",R.raw.phrase_im_feeling_good));
        words.add(new Word("aana'aa","are you coming",R.raw.phrase_are_you_coming));
        words.add(new Word("haa'aanam","yes, i'm coming",R.raw.phrase_yes_im_coming));




        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView =(ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = new MediaPlayer().create(PhrasesActivity.this, word.getmAudioResourceId());
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
