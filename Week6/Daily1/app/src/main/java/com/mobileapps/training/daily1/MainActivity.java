package com.mobileapps.training.daily1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MapsContract.PublishToView, View.OnClickListener{

    private static final String TAG = "MainActivity";

    private MapsPresenter mapsPresenter;
    public static final int REQUEST_CODE = 1;
    private boolean mLocationGranted = false;
    private Button btnGetLocation;
    private Button btnOpenMap;
    private TextView tvAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        mapsPresenter = new MapsPresenter(MainActivity.this, this);
        btnOpenMap = findViewById(R.id.btnOpenMap);
        btnGetLocation = findViewById(R.id.btnGetLocation);
        tvAddress = findViewById(R.id.tvShowAddress);

        btnOpenMap.setOnClickListener(this);
        btnGetLocation.setOnClickListener(this);
    }

    @Override
    public void showResults(String results) {
        Log.d(TAG, "showResults: Results " + results);
        tvAddress.setText(results);

    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenMap:
            {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
            case R.id.btnGetLocation:
            {
                getLocationPermissions();
            }
        }
    }

    public void getLocationPermissions(){
        String [] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationGranted = true;
                mapsPresenter.getLocationAsAddress();
            }else{
                ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called");
        switch (requestCode){
            case REQUEST_CODE:{
                if (grantResults.length > 0){
                    for (int i = 0; i < grantResults.length; i++) {
                        if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                            mLocationGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: Permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: Permission granted");
                    mLocationGranted = true;
                    mapsPresenter.getLocationAsAddress();
                    //initialize
                }
            }
        }

    }
}
