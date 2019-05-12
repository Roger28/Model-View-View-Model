
package com.example.firebase.ent;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Channel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("profileImageUrl")
    @Expose
    @ColumnInfo(name = "profileImageUrl")
    private String profileImageUrl;

    @SerializedName("numberOfSubscribers")
    @Expose
    @ColumnInfo(name = "numberOfSubscribers")
    private int numberOfSubscribers;

    /**
     * No args constructor for use in serialization
     * 
     */
    @Ignore
    public Channel() {
    }

    /**
     * 
     * @param numberOfSubscribers
     * @param name
     * @param profileImageUrl
     */
    public Channel(String name, String profileImageUrl, int numberOfSubscribers) {
        super();
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.numberOfSubscribers = numberOfSubscribers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public int getNumberOfSubscribers() {
        return numberOfSubscribers;
    }

    public void setNumberOfSubscribers(int numberOfSubscribers) {
        this.numberOfSubscribers = numberOfSubscribers;
    }

}
