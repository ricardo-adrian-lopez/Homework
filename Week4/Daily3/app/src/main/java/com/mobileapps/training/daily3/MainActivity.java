package com.mobileapps.training.daily3;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobileapps.training.daily3.client.ProfileHelper;
import com.mobileapps.training.daily3.model.Profile;

public class MainActivity extends AppCompatActivity implements Handler.Callback {
    private static final String TAG = "MainActivity";
    public static final String OAUTH_TOKEN_1 = "b68fe2601b5e88b7f0bc";
    public static final String OAUTH_TOKEN_2 = "94a6baac7056a7a99298";


    ImageView ivProfile;
    TextView tvProfileName;
    TextView tvProfileURL;
    TextView tvProfileCompany;
    TextView tvProfileRepos;
    Button btnRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Started");

        ivProfile = findViewById(R.id.ivProfile);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileURL = findViewById(R.id.tvProfileURL);
        tvProfileCompany = findViewById(R.id.tvProfileCompany);
        tvProfileRepos = findViewById(R.id.tvProfileRepos);
        btnRepos = findViewById(R.id.btnRepos);

        ProfileHelper profileHelper = new ProfileHelper(new Handler(this));
        profileHelper.makeAsyncCall(this,OAUTH_TOKEN_1 + OAUTH_TOKEN_2);
    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.d(TAG, "handleMessage: ");
        Profile profile = msg.getData().getParcelable("user");
        Log.d(TAG, "handleMessage: Profile: " + profile.getHtmlUrl());
        Glide.with(this).load(profile.getAvatarUrl()).into(ivProfile);
        tvProfileName.setText("Name: " +profile.getName());
        tvProfileURL.setText("URL: " +profile.getHtmlUrl());
        tvProfileCompany.setText("Company: " + profile.getCompany());
        tvProfileRepos.setText("Public repos: " + profile.getPublicRepos());

        btnRepos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReposActivity.class);
                startActivity(intent);
            }
        });
        return true;
    }
}
