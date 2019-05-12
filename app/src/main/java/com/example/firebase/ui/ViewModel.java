package com.example.firebase.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.firebase.ent.Channel;
import com.example.firebase.ent.Example;
import com.example.firebase.ent.User;
import com.example.firebase.ent.Videos;
import com.example.firebase.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Videos>> videos;
    private LiveData<List<User>> users;
    private LiveData<List<Channel>> channels;
    private LiveData<Example> data;

    public ViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.videos = this.repository.getAllVideos();
        this.users = this.repository.getAllUsers();
        this.channels = this.repository.getAllChannels();
        this.data = this.repository.getData();
    }

    public LiveData<List<User>> getAllUsers() {
        return this.users;
    }

    public LiveData<List<Videos>> getAllVideos(){
        return this.videos;
    }

    public LiveData<List<Channel>> getAllChannels(){
        return this.channels;
    }

    public LiveData<Example> getData(){
        return this.data;
    }

    public void insert(Videos videos){
        this.repository.insert(videos);
    }

    public void insert(User user){
        this.repository.insert(user);
    }

    public void insert(Channel channel){
        this.repository.insert(channel);
    }

}
