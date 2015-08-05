package com.example.chinmaykrishna.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chinmaykrishna.myapplication.models.Music;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by chinmaykrishna on 04/08/15.
 */
public class MusicAdapter extends BaseAdapter{
    WeakReference<Context> contextWeakReference;
    List<Music> musicList;

    public MusicAdapter(Context context, List<Music> musicList) {
        this.contextWeakReference = new WeakReference<Context>(context);
        this.musicList = musicList;
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Music getItem(int position) {
        return musicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView artistTextView;
        TextView songTextView;
        ImageView songImageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convert view for first 9-10 rows
        View view=convertView;
        ViewHolder viewHolder = null;
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(contextWeakReference.get());
            view = layoutInflater.inflate(R.layout.item_music, parent, false);
            viewHolder = new ViewHolder();


            viewHolder.artistTextView = (TextView) view.findViewById(R.id.artist_name);
            viewHolder.songTextView = (TextView) view.findViewById(R.id.music_name);
            viewHolder.songImageView=(ImageView) view.findViewById(R.id.music_photo);
            view.setTag(viewHolder);


        }
        if (viewHolder == null) {
            viewHolder = (ViewHolder) view.getTag();
        }


        Music music = getItem(position);
        viewHolder.artistTextView.setText(music.getAuthorname());
        Picasso.with(contextWeakReference.get()).load(music.getSongimageurl()).into(viewHolder.songImageView);
        viewHolder.songTextView.setText(music.getSongname());


        return view;
    }
}
