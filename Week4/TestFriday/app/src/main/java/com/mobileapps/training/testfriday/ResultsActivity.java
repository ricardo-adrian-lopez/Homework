package com.mobileapps.training.testfriday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobileapps.training.testfriday.model.Acronym;
import com.mobileapps.training.testfriday.model.Lf;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ListView listView = findViewById(R.id.listViewAcronyms);
        ArrayList<Lf> list = getIntent().getParcelableArrayListExtra("listAcronyms");

        AcronymAdapter adapter = new AcronymAdapter(this, R.layout.acronym_list_item, list);
        listView.setAdapter(adapter);
    }
}
