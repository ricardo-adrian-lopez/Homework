package com.mobileapps.training.daily4usage;

import android.database.Cursor;
import android.net.Uri;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mobileapps.training.daily4usage.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements PermissionManager.Callback {
    private static final String TAG = "MainActivity";

    public static final String CONTENT_URI = "com.mobileapps.training.AnimalsProvider";
    private PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                    Log.d(TAG, "onCreate: Cursor loading");
                    Cursor cursor = this.getContentResolver().query(Uri.parse("content://com.mobileapps.training/animal"),null,null,null,null);
                    List<Animal> list = new ArrayList<>();
                    if(cursor.moveToFirst()){
                        do{
                            Animal animal = new Animal();
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            String category = cursor.getString(cursor.getColumnIndex("category"));
                            animal.setName(name);
                            animal.setCategory(category);

                            list.add(animal);
                        }while (cursor.moveToNext());
                    }
                    Log.d(TAG, "List animal: " + list);
                }else {
                    Log.d(TAG, "onPermissionResults: Denied");
                }
                break;
            }

        }
    }
}
