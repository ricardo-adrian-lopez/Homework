package com.mobileapps.training.daily4usage;

import android.database.Cursor;
import android.net.Uri;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobileapps.training.daily4usage.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements PermissionManager.Callback {
    private static final String TAG = "MainActivity";

    public static final String CONTENT_URI = "com.mobileapps.training.AnimalsProvider";
    private PermissionManager permissionManager;
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = findViewById(R.id.rvList);

        Log.d(TAG, "onCreate: Check permission");
        permissionManager = new PermissionManager(this);
        permissionManager.checkPermission();
    }

    @Override
    public void onPermissionResults(int requestCode, boolean isGranted) {
        switch (requestCode){
            case PermissionManager.MY_PERMISSIONS_REQUEST_READ_CONTACTS:{
                if(isGranted){
                    Log.d(TAG, "onPermissionResults: Granted");
                    List<Animal> contentProviderList = getAnimals();

                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,contentProviderList);
                    rvList.setAdapter(adapter);
                    rvList.setLayoutManager(new LinearLayoutManager(this));
                }else {
                    Log.d(TAG, "onPermissionResults: Denied");
                }
                break;
            }

        }
    }

    private List<Animal> getAnimals(){
        Log.d(TAG, "onCreate: Cursor loading");
        Cursor cursor = this.getContentResolver().query(Uri.parse("content://com.mobileapps.training/animal"),null,null,null,null);
        List<Animal> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Animal animal = new Animal();
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                String weight = cursor.getString(cursor.getColumnIndex("weight"));
                String age = cursor.getString(cursor.getColumnIndex("age"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                animal.setName(name);
                animal.setCategory(category);
                animal.setWeight(weight);
                animal.setAge(age);
                animal.setGender(gender);
                animal.setImage(image);
                list.add(animal);
            }while (cursor.moveToNext());
        }
        Log.d(TAG, "List animal: " + list);
        return list;
    }
}
