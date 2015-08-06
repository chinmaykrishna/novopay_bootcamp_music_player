
package com.example.chinmaykrishna.myapplication.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//@Generated("org.jsonschema2pojo")
public class Collection1 {

    @SerializedName("song_photo")
    @Expose
    private SongPhoto songPhoto;
    @SerializedName("song_name")
    @Expose
    private SongName songName;
    @SerializedName("author_name")
    @Expose
    private AuthorName authorName;
    @Expose
    private Integer index;
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The songPhoto
     */
    public SongPhoto getSongPhoto() {
        return songPhoto;
    }

    /**
     * 
     * @param songPhoto
     *     The song_photo
     */
    public void setSongPhoto(SongPhoto songPhoto) {
        this.songPhoto = songPhoto;
    }

    /**
     * 
     * @return
     *     The songName
     */
    public SongName getSongName() {
        return songName;
    }

    /**
     * 
     * @param songName
     *     The song_name
     */
    public void setSongName(SongName songName) {
        this.songName = songName;
    }

    /**
     * 
     * @return
     *     The authorName
     */
    public AuthorName getAuthorName() {
        return authorName;
    }

    /**
     * 
     * @param authorName
     *     The author_name
     */
    public void setAuthorName(AuthorName authorName) {
        this.authorName = authorName;
    }

    /**
     * 
     * @return
     *     The index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 
     * @param index
     *     The index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
