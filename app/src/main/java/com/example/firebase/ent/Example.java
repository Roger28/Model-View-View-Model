
package com.example.firebase.ent;

import android.arch.persistence.room.Ignore;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("videos")
    @Expose
    private List<Videos> videos = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    @Ignore
    public Example() {
    }

    /**
     * 
     * @param videos
     * @param user
     */
    public Example(User user, List<Videos> videos) {
        super();
        this.user = user;
        this.videos = videos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }

}
