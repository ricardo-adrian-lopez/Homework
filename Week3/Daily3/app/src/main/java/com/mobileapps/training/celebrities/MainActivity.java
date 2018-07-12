package com.mobileapps.training.celebrities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mobileapps.training.celebrities.fragments.FragmentA;
import com.mobileapps.training.celebrities.fragments.FragmentB;
import com.mobileapps.training.celebrities.model.Celebrity;

public class MainActivity extends AppCompatActivity implements FragmentA.OnCelebritySelectedListner {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentA fragmentA = new FragmentA();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentCelebrities, fragmentA, FragmentA.TAG).commit();

    }

    @Override
    public void onCelebritySelected(Celebrity celebrity) {
        Log.d(TAG, "onCelebritySelected: MainActivity:  " + celebrity);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentB fragmentB = (FragmentB) fragmentManager.findFragmentById(R.id.fragmentDetails);

        if(fragmentB==null){
            fragmentB = FragmentB.newInstance(celebrity);
            fragmentManager.beginTransaction().add(R.id.fragmentDetails,fragmentB,FragmentB.TAG).addToBackStack(null).commit();
        }else{
            fragmentB.onUpdate(celebrity);
        }
    }
}
