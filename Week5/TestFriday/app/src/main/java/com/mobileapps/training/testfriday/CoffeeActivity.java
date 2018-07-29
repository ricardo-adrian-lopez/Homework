package com.mobileapps.training.testfriday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CoffeeActivity extends AppCompatActivity {

    private static final String TAG = "CoffeeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        Log.d(TAG, "onCreate: ");

        TextView tvDetailsName = findViewById(R.id.tvDetailsName);
        TextView tvDetailsDesc = findViewById(R.id.tvDetailsDesc);
        ImageView ivDetailsPicture = findViewById(R.id.ivDetailsPicture);
        TextView tvDetailsDate = findViewById(R.id.tvDetailsDate);


    }
}
