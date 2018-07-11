package com.mobileapps.training.zoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ImageView ivMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Activity Main Started");
        int imageRes =  getResources().getIdentifier("@drawable/atlanta_zoo",null,this.getPackageName());
        ivMain = findViewById(R.id.ivMain);
        ivMain.setImageResource(imageRes);
    }
}
