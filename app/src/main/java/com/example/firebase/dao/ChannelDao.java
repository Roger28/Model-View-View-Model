package com.example.firebase.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.firebase.ent.Channel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ChannelDao {

    @Insert(onConflict = REPLACE)
    void insert(Channel channel);

    @Update
    void update(Channel channel);

    @Delete
    void delete(Channel channel);

    @Query("SELECT * FROM channel")
    LiveData<List<Channel>> getAllChannels();

}
