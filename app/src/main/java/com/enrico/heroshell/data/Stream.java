package com.enrico.heroshell.data;

/**
 * Created by enrico on 10/21/17.
 */

public class Stream {
    String albumArt;
    String title;
    String artist;

    public Stream() {
        albumArt = "https://is2-ssl.mzstatic.com/image/thumb/Music/v4/a7/50/80/a7508004-5f2c-33b8-81c0-3662b27d528b/UMG_cvrart_00050087287771_01_RGB72_1500x1500_12DMGIM03560.jpg/1200x630bb.jpg";
        title = "Be Our Guest";
        artist = "The Circle Session Players";
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}
