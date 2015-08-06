package com.example.chinmaykrishna.myapplication.Network;

import android.os.Handler;

import com.example.chinmaykrishna.myapplication.models.MusicApiResponse;

import javax.security.auth.callback.Callback;

import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by chinmaykrishna on 06/08/15.
 */
public class MusicApi {

    public static final String url = "https://www.kimonolabs.com/api";

    public static MusicInterface musicInterface = null;
    public static MusicInterface getApi(){
        if(musicInterface==null)
        {
            RestAdapter restAdapter=new retrofit.RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(url)
                    .build();
            musicInterface=restAdapter.create(MusicInterface.class);
        }
        return musicInterface;
    }

    public interface MusicInterface{
        @GET("/3ptzyrzc?apikey=kEFrTqhRbElSV1Id7MmdpTCMpOC0wfPs")
        MusicApiResponse getMusicList();

        @GET("/3ptzyrzc?apikey=kEFrTqhRbElSV1Id7MmdpTCMpOC0wfPs")
        void getMusicList(retrofit.Callback<MusicApiResponse> musicApiResponseCallback);

    }
}
