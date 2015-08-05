package com.example.chinmaykrishna.myapplication.models;

/**
 * Created by chinmaykrishna on 04/08/15.
 */
public class Music {
    private String songname;
    private String authorname;
    private String songimageurl;

    public Music(String songname, String authorname,String songimageurl) {
        this.songname = songname;
        this.authorname = authorname;
        this.songimageurl=songimageurl;
    }

    public String getSongimageurl() {
        return songimageurl;
    }

    public void setSongimageurl(String songimageurl) {
        this.songimageurl = songimageurl;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }
}
