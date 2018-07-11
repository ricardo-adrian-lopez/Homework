package com.mobileapps.training.zoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobileapps.training.zoo.data.LocalDataSource;
import com.mobileapps.training.zoo.model.Animal;

import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerActivity";
    LocalDataSource localDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Log.d(TAG, "onCreate: RecyclerActivity Started");
        localDataSource = new LocalDataSource(this);
        String categoryName = getIntent().getStringExtra("category");
        List<Animal> animalList = localDataSource.getAnimalCard(categoryName);
        Log.d(TAG, "onCreate: Animal List" + animalList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(animalList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
