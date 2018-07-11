package com.mobileapps.training.zoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobileapps.training.zoo.data.LocalDataSource;

import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private static final String TAG = "CategoriesActivity";

    ListView lvCategories;
    LocalDataSource localDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Log.d(TAG, "onCreate: CategoriesActivity started");

        lvCategories = findViewById(R.id.lvCategories);
        localDataSource = new LocalDataSource(this);
        List<String> listCategories = localDataSource.getAllCategories();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listCategories);
        lvCategories.setAdapter(adapter);

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
