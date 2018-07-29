package com.mobileapps.training.testfriday;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobileapps.training.testfriday.model.Coffee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback{

    private static final String TAG = "MainActivity";

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");

        listView = findViewById(R.id.lvCoffee);
        RetrofitHelper retrofitHelper = new RetrofitHelper(new Handler(this));
        retrofitHelper.getListCoffe();

    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.d(TAG, "handleMessage: ");
        final ArrayList<Coffee> list = msg.getData().getParcelableArrayList("listCoffee");
        CoffeeAdapter adapter = new CoffeeAdapter(MainActivity.this, R.layout.coffee_list_item, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
        return false;
    }
}
