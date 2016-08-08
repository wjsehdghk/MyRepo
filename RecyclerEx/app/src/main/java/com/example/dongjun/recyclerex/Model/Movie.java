package com.example.dongjun.recyclerex.Model;

/**
 * Created by dongjun on 2016-08-08.
 */
public class Movie  {
    String title;
    String genre;
    String yaer;
    public Movie(){
    }
    public Movie(String title, String genre, String yaer) {
        this.title = title;
        this.genre = genre;
        this.yaer = yaer;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYaer() {
        return yaer;
    }

    public void setYaer(String yaer) {
        this.yaer = yaer;
    }
}
