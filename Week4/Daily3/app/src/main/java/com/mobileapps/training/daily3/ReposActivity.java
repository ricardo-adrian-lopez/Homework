package com.mobileapps.training.daily3;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobileapps.training.daily3.client.ProfileHelper;
import com.mobileapps.training.daily3.model.Repo;

import java.util.ArrayList;

public class ReposActivity extends AppCompatActivity implements Handler.Callback {
    private static final String TAG = "ReposActivity";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        Log.d(TAG, "onCreate: Started");
        recyclerView = findViewById(R.id.recyclerView);

        ProfileHelper profileHelper = new ProfileHelper(new Handler(this));
        profileHelper.makeAsyncCallRepos(MainActivity.OAUTH_TOKEN_1+MainActivity.OAUTH_TOKEN_2);
        
    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.d(TAG, "handleMessage: ");
        ArrayList<Repo> repos = msg.getData().getParcelableArrayList("repos");
        Log.d(TAG, "handleMessage: Repos " + repos );
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(repos,getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        return false;
    }
}
