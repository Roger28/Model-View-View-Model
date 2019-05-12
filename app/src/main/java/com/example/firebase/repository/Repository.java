package com.example.firebase.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.firebase.api.ApiService;
import com.example.firebase.api.ServiceGenerator;
import com.example.firebase.dao.ChannelDao;
import com.example.firebase.dao.VideosDao;
import com.example.firebase.dao.UserDao;
import com.example.firebase.database.AppDatabase;
import com.example.firebase.ent.Channel;
import com.example.firebase.ent.Example;
import com.example.firebase.ent.User;
import com.example.firebase.ent.Videos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private VideosDao videosDao;
    private UserDao userDao;
    private ChannelDao channelDao;
    private LiveData<List<Videos>> videos;
    private LiveData<List<User>> users;
    private LiveData<List<Channel>> channels;

    public Repository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        this.videosDao = appDatabase.videosDao();
        this.userDao = appDatabase.pessoaDao();
        this.channelDao = appDatabase.channelDao();
        this.videos = this.videosDao.getAllVideos();
        this.users = this.userDao.getAllUsers();
        this.channels = this.channelDao.getAllChannels();
        insertDataFromApi();
    }

    public LiveData<List<User>> getAllUsers() {
        return this.users;
    }

    public LiveData<List<Videos>> getAllVideos() {
        return this.videos;
    }

    public LiveData<List<Channel>> getAllChannels() {
        return this.channels;
    }

    public void insert(Videos videos) {
        new InsertVideosAsyncTask(this.videosDao).execute(videos);
    }

    private static class InsertVideosAsyncTask extends AsyncTask<Videos, Void, Void> {

        private VideosDao videosDao;

        private InsertVideosAsyncTask(VideosDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(Videos... videos) {
            this.videosDao.insert(videos[0]);
            return null;
        }
    }

    public void insert(User user) {
        new InsertUsersAsyncTask(this.userDao).execute(user);
    }

    private static class InsertUsersAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private InsertUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            this.userDao.insert(users[0]);
            return null;
        }
    }

    public void insert(Channel channel) {
        new InsertChannelAsyncTask(this.channelDao).execute(channel);
    }

    private static class InsertChannelAsyncTask extends AsyncTask<Channel, Void, Void> {

        private ChannelDao channelDao;

        private InsertChannelAsyncTask(ChannelDao channelDao) {
            this.channelDao = channelDao;
        }

        @Override
        protected Void doInBackground(Channel... channels) {
            this.channelDao.insert(channels[0]);
            return null;
        }
    }

    private void insertDataFromApi() {
        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<Example> call = apiService.getAllData();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example data = response.body();
                insert(data.getUser());

                for (Videos v : data.getVideos()) {
                    insert(v);
                    insert(v.getChannel());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    public LiveData<Example> getData() {
        final MutableLiveData<Example> data = new MutableLiveData<>();
        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<Example> call = apiService.getAllData();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
        return data;
    }

}
