package com.mobileapps.training.daily3;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobileapps.training.daily3.client.ProfileHelper;
import com.mobileapps.training.daily3.model.Profile;

public class MainActivity extends AppCompatActivity implements Handler.Callback {
    private static final String TAG = "MainActivity";
    public static final String OAUTH_TOKEN = "ea46d8de5696bda9e6d4d06f3fe8497a0191abe8";

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
        profileHelper.makeAsyncCall(this,OAUTH_TOKEN);
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
        return true;
    }
}
