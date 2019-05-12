package com.example.firebase.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.firebase.dao.ChannelDao;
import com.example.firebase.dao.UserDao;
import com.example.firebase.dao.VideosDao;
import com.example.firebase.ent.Channel;
import com.example.firebase.ent.User;
import com.example.firebase.ent.Videos;

@Database(entities = {Channel.class, Videos.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    private static final String DATABASE_NAME = "database";

    public abstract UserDao pessoaDao();

    public abstract VideosDao videosDao();

    public abstract ChannelDao channelDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;
        private VideosDao videosDao;
        private ChannelDao channelDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            this.userDao = db.pessoaDao();
            this.videosDao = db.videosDao();
            this.channelDao = db.channelDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
