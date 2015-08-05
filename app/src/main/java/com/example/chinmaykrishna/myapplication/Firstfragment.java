package com.example.chinmaykrishna.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chinmaykrishna.myapplication.Services.MusicService;
import com.example.chinmaykrishna.myapplication.models.Music;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;

/**
 * Created by chinmaykrishna on 04/08/15.
 */
public class Firstfragment extends android.support.v4.app.Fragment{


    ListView music_list_view;
    ListView listView;
    MusicAdapter musicAdapter;
    List<Music> musicList=new ArrayList<Music>();



    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_first,container,false);
        listView = (ListView) view.findViewById(R.id.first_fragment_listview);
        musicAdapter = new MusicAdapter(getActivity(), musicList);
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD,MusicService.METHOD_PLAY);
                getActivity().startService(intent);
                getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
               // startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("Music 1", "Author 1","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 2","Author 2","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 3","Author 3","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 4","Author 4","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 5","Author 5","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 6","Author 6","http://loremflickr.com/320/240"));
    }
}
