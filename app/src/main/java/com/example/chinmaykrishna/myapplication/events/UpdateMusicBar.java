package com.example.chinmaykrishna.myapplication.events;

/**
 * Created by chinmaykrishna on 06/08/15.
 */
public class UpdateMusicBar {
    public String songname;
    public String songauthor;

    public UpdateMusicBar(String songname, String songauthor) {
        this.songname = songname;
        this.songauthor = songauthor;
    }
}
