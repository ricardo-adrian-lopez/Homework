package com.mobileapps.training.zoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mobileapps.training.zoo.data.LocalDataContract;
import com.mobileapps.training.zoo.data.LocalDataSource;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ImageView ivMain;
    Button btnCategories;
    LocalDataSource localDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Activity Main Started");
        this.deleteDatabase(LocalDataContract.DATABASE_NAME);
        Log.d(TAG, "onCreate: Database deleted");
        localDataSource = new LocalDataSource(this);
        localDataSource.init();
        Log.d(TAG, "onCreate: Database created");
        int imageRes =  getResources().getIdentifier("@drawable/atlanta_zoo",null,this.getPackageName());
        ivMain = findViewById(R.id.ivMain);
        ivMain.setImageResource(imageRes);

        btnCategories = findViewById(R.id.btnCategories);

        btnCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CategoriesActivity.class);
                startActivity(intent);
            }
        });
    }
}
