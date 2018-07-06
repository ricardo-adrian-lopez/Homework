package com.mobileapps.training.week2daily4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mobileapps.training.week2daily4.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ListPerson extends BaseActivity {

    private static final String TAG = "ListPerson";
    ListView listViewPerson;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_person);
        Log.d(TAG, "onCreate: Started ListPerson");
        listViewPerson = findViewById(R.id.listViewPerson);
        btnBack = findViewById(R.id.btnBackToAddPerson);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Person> personList =  bundle.getParcelableArrayList("personList");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,personList);
        listViewPerson.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPerson.class);
                startActivity(intent);
            }
        });
    }
}
