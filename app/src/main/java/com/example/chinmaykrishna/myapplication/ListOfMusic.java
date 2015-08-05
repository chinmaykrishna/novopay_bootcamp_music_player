package com.example.chinmaykrishna.myapplication;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chinmaykrishna.myapplication.Provider.MusicDatabase;
import com.example.chinmaykrishna.myapplication.Services.MusicService;
import com.example.chinmaykrishna.myapplication.events.MusicPlayEvent;
import com.example.chinmaykrishna.myapplication.events.UpdateMusicBar;

import butterknife.Bind;
import de.greenrobot.event.EventBus;

/**
 * Created by chinmaykrishna on 04/08/15.
 */
public class ListOfMusic extends FragmentActivity {
    private ViewPager viewPager;
    private MusicListFragmentStatePagerAdapter musicFragmentPager;
    private static  final int NUMBER_OF_FRAGMENTS=2;
    MusicDatabase musicDbHelper;
    SQLiteDatabase musicDb;

    public RelativeLayout music_bar;


    Button music_bar_play;

    Button music_bar_pause;

    TextView music_bar_song_name;

    TextView music_bar_song_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        /****Database will be created when getwritable or getreadableis called*/
        musicDbHelper=new MusicDatabase(getApplicationContext());
        musicDb=musicDbHelper.getReadableDatabase();
        /***************************************************/
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        musicFragmentPager=new MusicListFragmentStatePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(musicFragmentPager);
        music_bar=(RelativeLayout) findViewById(R.id.music_bar);
        music_bar_song_name=(TextView) findViewById(R.id.music_bar_song_name);
        music_bar_song_author=(TextView)findViewById(R.id.music_bar_song_author);
        music_bar_play=(Button) findViewById(R.id.music_bar_play);
        music_bar_pause=(Button) findViewById(R.id.music_bar_pause);
    }

    private class MusicListFragmentStatePagerAdapter extends FragmentStatePagerAdapter{

        public MusicListFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new Firstfragment();
                case 1:
                    return new Secondfragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_FRAGMENTS;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(UpdateMusicBar event)
    {
        music_bar.setVisibility(View.VISIBLE);
        music_bar_song_name.setText(event.songname);
        music_bar_song_author.setText(event.songauthor);

        Intent intent=new Intent(ListOfMusic.this, MusicService.class);
        intent.putExtra(MusicService.KEY_METHOD, MusicService.METHOD_PLAY);
        startService(intent);


    }

    public void onEvent(MusicPlayEvent event){
        music_bar_play.setVisibility(View.INVISIBLE);
        music_bar_pause.setVisibility(View.VISIBLE);
        music_bar_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_bar_play.setVisibility(View.INVISIBLE);
                music_bar_pause.setVisibility(View.VISIBLE);
                MusicService.playMusic();

            }
        });
        music_bar_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_bar_pause.setVisibility(View.INVISIBLE);
                music_bar_play.setVisibility(View.VISIBLE);
                MusicService.pauseMusic();

            }
        });
        music_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOfMusic.this,MainActivity.class));
            }
            });

    }
}
