package com.example.chinmaykrishna.myapplication.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.chinmaykrishna.myapplication.R;
import com.example.chinmaykrishna.myapplication.events.MusicCompletedEvent;
import com.example.chinmaykrishna.myapplication.events.MusicPlayEvent;

import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;

/**
 * Created by chinmaykrishna on 05/08/15.
 */
public class MusicService extends Service {

    private static MediaPlayer mediaPlayer;

    public static int getcurrentposition(){
        if(mediaPlayer!=null)
        {
            if(mediaPlayer.isPlaying())
            {
                return mediaPlayer.getCurrentPosition();
            }
        }
        return -1;
    }

    public static final String KEY_METHOD="method";
    public static final String METHOD_PLAY="method_play";
    public static final String METHOD_PAUSE="method_pause";
    public static final String METHOD_STOP="method_stop";
    public static final String METHOD_FF="method_ff";
    public static final String METHOD_RW="method_rw";


    @DebugLog
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @DebugLog
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent==null) {
            return super.onStartCommand(intent, flags, startId);
        }
        String method=intent.getStringExtra(KEY_METHOD);


        if(method.equals(METHOD_PLAY))
        {
            mediaPlayer=MediaPlayer.create(this, R.raw.selfie);
            EventBus.getDefault().post(new MusicPlayEvent(mediaPlayer.getDuration()));
            playMusic();

        }
        if(method.equals(METHOD_PAUSE))
        {
            pauseMusic();
        }
        if(method.equals(METHOD_FF))
        {
            ffMusic();
        }
        if(method.equals(METHOD_RW))
        {
            rwMusic();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                EventBus.getDefault().post(new MusicCompletedEvent());
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    public static void playMusic()
    {
        mediaPlayer.start();
    }
    public static void pauseMusic()
    {
        mediaPlayer.pause();
    }
    public static void ffMusic()
    {
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 2000);
    }
    public static void rwMusic()
    {
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 2000);
    }

    public static int songDuration() {

            return mediaPlayer.getDuration();

    }

    public static boolean isMusicPlaying(){
        if(mediaPlayer!=null)
        {
            if(mediaPlayer.isPlaying())
            {
                return true;
            }
        }
        return false;
    }

    public static void seekMusicTo(int progress, boolean fromUser){
        if(mediaPlayer!=null && fromUser) {
            mediaPlayer.seekTo(progress);
        }
    }

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @DebugLog
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
