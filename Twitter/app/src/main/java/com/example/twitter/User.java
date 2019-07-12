package com.example.twitter;


import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String tweet;
    private int photo;

    public User(String name, String tweet, int photo) {
        this.name = name;
        this.tweet = tweet;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
