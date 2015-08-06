package com.example.chinmaykrishna.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.chinmaykrishna.myapplication.Network.MusicApi;
import com.example.chinmaykrishna.myapplication.models.Collection1;
import com.example.chinmaykrishna.myapplication.models.Music;
import com.example.chinmaykrishna.myapplication.models.MusicApiResponse;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by chinmaykrishna on 04/08/15.
 */
public class Secondfragment extends android.support.v4.app.Fragment{
    GridView grid;
    private MusicAdapter musicAdapter;
    List<Collection1> musicList=new ArrayList<Collection1>();

    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_second,container,false);
        grid=(GridView) view.findViewById(R.id.gridview);
        MusicApi.getApi().getMusicList(new Callback<MusicApiResponse>() {
            @Override
            public void success(MusicApiResponse musicApiResponse, Response response) {
                musicList.addAll(musicApiResponse.getResults().getCollection1());
                musicAdapter = new MusicAdapter(getActivity(), musicList);
                grid.setAdapter(musicAdapter);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* musicList.add(new Music("Music 1", "Author 1","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 2","Author 2","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 3","Author 3","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 4","Author 4","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 5","Author 5","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 6", "Author 6","http://loremflickr.com/320/240"));*/
    }
}
