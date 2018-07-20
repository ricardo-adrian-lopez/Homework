package com.mobileapps.training.testfriday;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobileapps.training.testfriday.client.OkHttpHelper;
import com.mobileapps.training.testfriday.model.Lf;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback{

    private static final String TAG = "MainActivity";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Started");

        editText = findViewById(R.id.etAcro);
    }

    public void onSearchClicked(View view) {
        OkHttpHelper okHttpHelper = new OkHttpHelper(editText.getText().toString(), new Handler(this));
        okHttpHelper.makeAsyncCall();
    }

    @Override
    public boolean handleMessage(Message msg) {
        ArrayList<Lf> list = msg.getData().getParcelableArrayList("listAcronyms");
        Log.d(TAG, "handleMessage: " + list.size());
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putParcelableArrayListExtra("listAcronyms",list);
        startActivity(intent);
        return false;
    }
}
