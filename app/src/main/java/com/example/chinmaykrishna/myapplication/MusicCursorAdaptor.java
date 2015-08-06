package com.example.chinmaykrishna.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chinmaykrishna.myapplication.Provider.MusicDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by chinmaykrishna on 06/08/15.
 */
public class MusicCursorAdaptor extends CursorAdapter {
    public MusicCursorAdaptor(Context context, Cursor c) {
        super(context, c, false);
    }

    private class ViewHolder{
        TextView artistTextView;
        TextView songTextView;
        ImageView songImageView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_music, parent, false);
        ViewHolder viewHolder = new ViewHolder();


        viewHolder.artistTextView = (TextView) view.findViewById(R.id.artist_name);
        viewHolder.songTextView = (TextView) view.findViewById(R.id.music_name);
        viewHolder.songImageView=(ImageView) view.findViewById(R.id.music_photo);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder= (ViewHolder) view.getTag();

        String imageUrl=cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_IMAGE_URL));
        String songName=cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_NAME));
        String SongAuthor=cursor.getString(cursor.getColumnIndex(MusicDatabase.TableMusic.MUSIC_AUTHOR));
        viewHolder.artistTextView.setText(SongAuthor);
        Picasso.with(context).load(imageUrl).into(viewHolder.songImageView);
        viewHolder.songTextView.setText(songName);



    }
}
