package com.example.thandiwe.miwork;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {




    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

     private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;


    AudioManager.OnAudioFocusChangeListener  mOnAudioFocusListener =  new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                 releaseMediaPlayer();
            }
        }
    }
   ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

      /*  //creating an array of words
        String[] words = new String[10];
        //Assigning  values in the array
        words[0]="one";
        words[1] ="two";
        words[1] ="three";
        words[1] ="four";
        words[1] ="five";
        words[1] ="six";
        words[1] ="seven";
        words[1] ="eight";
        words[1] ="nine";
        words[1] ="ten";

        Log.v("NumbersActivity", "Word at index 0: " + words[0]);
        Log.v("NumbersActivity", "Word at index 0: " + words[1]);
        Log.v("NumbersActivity", "Word at index 0: " + words[2]);*/


      //creating arrayList
     /*    ArrayList<String> words = new ArrayList<String>();

        //Assigning  values in the arrayList
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");


        //find root view of the whole layout
       // LinearLayout rootView =(LinearLayout)findViewById(R.id.rootView);

         //create an index to keep track of current index position
        int index =0;

        //creating the while loop

        while(index < words.size())
        {
            //create new TextView that displays the words
            //and add the view as a child to the root view
            TextView wordView = new TextView(this);
            wordView.setText(words.get(index));
            rootView.addView(wordView);

            //update counter variable
            index++;
        }*/


    /*  //using a for loop
        for(int index = 0; index < words.size(); index++)
        {
            //create TextView
            TextView wordView = new TextView(this);
            //set text to be word at the current index
            wordView.setText(words.get(index));
            //add this TextView as another child to the roo view of this layout
            rootView.addView(wordView);
        }

         ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this,R.layout.list_item, words);

        ListView listView =(ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);*/

    //create and setup the AudioManager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //creating an array of words
       final ArrayList<Word> words = new ArrayList<Word>();
        //Assigning  values in the arrayList
        words.add(new Word("one","lutti", R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko", R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","we'e", R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha", R.drawable.number_ten,R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, words,R.color.category_numbers);

        ListView listView =(ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = words.get(position);

                //Release the media player if it currently exists coz we about to
                //play different sound file

                releaseMediaPlayer();

              // Request audio focus for playback

                int results = mAudioManager.requestAudioFocus(mOnAudioFocusListener, AudioManager.STREAM_MUSIC,
                                                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(results == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    //start playback
                     //  mAudioManager.registerMediaButtonEventReceiver();


                    //create and setup the media player for the audio resource
                    mMediaPlayer = new MediaPlayer().create(NumbersActivity.this, word.getmAudioResourceId());

                    //Starts media player
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
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

                   mAudioManager.abandonAudioFocus(mOnAudioFocusListener);
               }
         }

}
