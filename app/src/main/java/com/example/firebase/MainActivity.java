package com.example.firebase;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.firebase.adapter.Adapter;
import com.example.firebase.adapter.Adapter2;
import com.example.firebase.ent.Example;
import com.example.firebase.ent.Videos;
import com.example.firebase.ui.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);

        final Adapter mAdaper = new Adapter();
        final Adapter2 mAdaper2 = new Adapter2();

        mRecyclerView.setAdapter(mAdaper2);

        ViewModel mViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        mRecyclerView.setItemViewCacheSize(5);

        mViewModel.getData().observe(this, new Observer<Example>() {
            @Override
            public void onChanged(@Nullable Example example) {
                List<Example> list = new ArrayList<>();
                list.add(example);
                mAdaper2.submitList(example.getVideos());
            }
        });

//        mViewModel.getAllVideos().observe(this, new Observer<List<Videos>>() {
//            @Override
//            public void onChanged(@Nullable List<Videos> videos) {
//                mAdaper2.submitList(videos);
//            }
//        });
    }
}
