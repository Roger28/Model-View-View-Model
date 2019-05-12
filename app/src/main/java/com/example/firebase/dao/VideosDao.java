package com.example.firebase.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.firebase.ent.Videos;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface VideosDao {

    @Insert(onConflict = REPLACE)
    void insert(Videos videos);

    @Update
    void update(Videos videos);

    @Delete
    void delete(Videos videos);

    @Query("SELECT * FROM Videos")
    LiveData<List<Videos>> getAllVideos();
}
