package com.mobileapps.training.daily1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mobileapps.training.daily1.model.Animal;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String ANIMAL_LIST ="animalList";

    CustomReceiver customReceiver;
    IntentFilter intentFilter;

    AnimalListReceiver animalListReceiver;

    List<Animal> animalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,MyIntentService.class);
        startService(intent);
    }

    @Override
    protected void onStart() {
        customReceiver = new CustomReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_CAMERA_BUTTON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        this.registerReceiver(customReceiver,intentFilter);

        //Broadcast receiver for intentService
        animalListReceiver = new AnimalListReceiver();
        IntentFilter intentFilter = new IntentFilter(MainActivity.ANIMAL_LIST);
        this.registerReceiver(animalListReceiver,intentFilter);

        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        //Added the unregister here so the screen off intent will work
        this.unregisterReceiver(customReceiver);
        this.unregisterReceiver(animalListReceiver);
        super.onDestroy();
    }

    public void onClickSendStickyBroadcast(View view) {
        Log.d(TAG, "onClickSendStickyBroadcast: Sending sticky broadcast");
        Intent intent = new Intent("com.training.mobileapps.sticky");
        intent.putExtra("firstAnimal",animalList.get(0).getName());
        sendStickyBroadcast(intent);
    }

    public void onClickSendOrderedBroadcast(View view) {
        Log.d(TAG, "onClickSendOrderedBroadcast: Sending ordered broadcast");
        Intent intent = new Intent("com.training.mobileapps.ordered");
        intent.putExtra("firstAnimal",animalList.get(0).getName());
        sendOrderedBroadcast(intent,null);
    }

    public class AnimalListReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: Receiving broadcast from IntentService");
            animalList = intent.getParcelableArrayListExtra("animalList");

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(animalList,getApplicationContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
    }
}
