package com.mobileapps.training.firebaseauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TwitterActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "TwitterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        Log.d(TAG, "onCreate: ");


    }

    @Override
    public void onClick(View v) {

    }
}
