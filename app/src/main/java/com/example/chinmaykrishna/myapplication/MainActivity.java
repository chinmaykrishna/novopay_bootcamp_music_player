package com.example.chinmaykrishna.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.logging.Handler;


public class MainActivity extends ActionBarActivity {
    Button play,pause,ff,rewind;
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private ImageView songPhoto;
    MusicHandler musichandler=new MusicHandler();
    private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        play=(Button) findViewById(R.id.button_play);
        play.setVisibility(View.INVISIBLE);
        pause=(Button) findViewById(R.id.button_pause);
        pause.setVisibility(View.VISIBLE);
        ff=(Button) findViewById(R.id.forward);
        rewind=(Button) findViewById(R.id.rewind);
        mediaPlayer=MediaPlayer.create(this,R.raw.selfie);
        seekbar=(SeekBar) findViewById(R.id.seekBar2);
        songPhoto=(ImageView) findViewById(R.id.music_cover);
        Picasso.with(MainActivity.this).load("http://loremflickr.com/320/240").into(songPhoto);
        //starts playing on click
        mediaPlayer.start();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                musichandler.sendEmptyMessage(WAKE_UP_AND_SEEK);
                play.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();

                play.setVisibility(View.VISIBLE);
                pause.setVisibility(View.INVISIBLE);
            }

        });
        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
            }
        });
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "Song Ended", Toast.LENGTH_SHORT).show();
                play.setVisibility(View.VISIBLE);
                pause.setVisibility(View.INVISIBLE);
            }
        });

        seekbar.setMax(mediaPlayer.getDuration());

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(MainActivity.this,"progress is "+progress,Toast.LENGTH_SHORT).show();
                if(mediaPlayer!=null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }//mediaPlayer.seekTo(mediaPlayer.getDuration());
                if(progress==100)
                {
                    play.setVisibility(View.VISIBLE);
                    pause.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static int WAKE_UP_AND_SEEK=10;

    class MusicHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg){
            if(msg.what== WAKE_UP_AND_SEEK)
            {
                if(mediaPlayer!=null)
                {
                    if(mediaPlayer.isPlaying())
                    {
                        seekbar.setProgress(mediaPlayer.getCurrentPosition());
                        sendEmptyMessageDelayed(WAKE_UP_AND_SEEK,200);
                    }
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
}
