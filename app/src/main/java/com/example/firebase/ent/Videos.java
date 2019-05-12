
package com.example.firebase.ent;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Videos {

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("link")
    @Expose
    @ColumnInfo(name = "link")
    private String link;

    @SerializedName("imageUrl")
    @Expose
    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @SerializedName("numberOfViews")
    @Expose
    @ColumnInfo(name = "numberOfViews")
    private int numberOfViews;

    @SerializedName("channel")
    @Expose
    @Ignore
    private Channel channel;


    /**
     * No args constructor for use in serialization
     */
    public Videos() {
    }

    /**
     * @param id
     * @param imageUrl
     * @param link
     * @param name
     * @param numberOfViews
     * @param channel
     */

    @Ignore
    public Videos(int id, String name, String link, String imageUrl, int numberOfViews, Channel channel) {
        super();
        this.id = id;
        this.name = name;
        this.link = link;
        this.imageUrl = imageUrl;
        this.numberOfViews = numberOfViews;
        this.channel = channel;
    }

    public Videos(String name, String link, String imageUrl, int numberOfViews) {
        this.name = name;
        this.link = link;
        this.imageUrl = imageUrl;
        this.numberOfViews = numberOfViews;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
