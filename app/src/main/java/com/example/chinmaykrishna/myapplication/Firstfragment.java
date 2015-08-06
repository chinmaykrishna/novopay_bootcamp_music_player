package com.example.chinmaykrishna.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chinmaykrishna.myapplication.Network.MusicApi;
import com.example.chinmaykrishna.myapplication.Provider.MusicDatabase;
import com.example.chinmaykrishna.myapplication.Services.MusicService;
import com.example.chinmaykrishna.myapplication.events.UpdateMusicBar;
import com.example.chinmaykrishna.myapplication.models.Collection1;
import com.example.chinmaykrishna.myapplication.models.Music;
import com.example.chinmaykrishna.myapplication.models.MusicApiResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by chinmaykrishna on 04/08/15.
 */
public class Firstfragment extends android.support.v4.app.Fragment{

    Activity musicListActivity;
    ListView music_list_view;
    ListView listView;
    MusicAdapter musicAdapter;
    List<Collection1> musicList=new ArrayList<Collection1>();

    MusicCursorAdaptor musicCursorAdaptor;
    MusicDatabase musicDbHelper;
    SQLiteDatabase musicDb;



    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_first,container,false);
        listView = (ListView) view.findViewById(R.id.first_fragment_listview);
        musicDbHelper=new MusicDatabase(getActivity());
        musicDb=musicDbHelper.getReadableDatabase();
        Cursor cursor=musicDb.query(MusicDatabase.Tables.MUSIC,null,null,null,null,null,null);
        musicCursorAdaptor=new MusicCursorAdaptor(getActivity(),cursor);
        listView.setAdapter(musicCursorAdaptor);

       /* MusicApi.getApi().getMusicList(new Callback<MusicApiResponse>() {
            @Override
            public void success(MusicApiResponse musicApiResponse, Response response) {
                musicList.addAll(musicApiResponse.getResults().getCollection1());
                musicAdapter = new MusicAdapter(getActivity(), musicList);
                listView.setAdapter(musicAdapter);
                Toast.makeText(getActivity(),"number of entries "+musicApiResponse.getResults().getCollection1().size(),Toast.LENGTH_SHORT).show();

            }


            @Override
            public void failure(RetrofitError error) {

            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String songName=musicList.get(position).getSongName().getText();
                String songAuthor=musicList.get(position).getAuthorName().getText();
                String songImageUrl=musicList.get(position).getSongPhoto().getSrc();
                EventBus.getDefault().post(new UpdateMusicBar(songName,songName,songImageUrl));
               // getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
               // startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*musicList.add(new Music("Music 1", "Author 1","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 2","Author 2","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 3","Author 3","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 4","Author 4","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 5","Author 5","http://loremflickr.com/320/240"));
        musicList.add(new Music("Music 6","Author 6","http://loremflickr.com/320/240"));*/
    }
}
