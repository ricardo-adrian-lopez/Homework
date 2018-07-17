package com.mobileapps.training.daily1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String TAG = "CustomReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                Log.d(TAG, "onReceive: Airplane changed");
                Toast.makeText(context, "Airplane status changed", Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_CAMERA_BUTTON:
                Log.d(TAG, "onReceive: Camera initiated");
                Toast.makeText(context, "Camera initiated", Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_HEADSET_PLUG:
                Log.d(TAG, "onReceive: Headset plugged/unplugged");
                Toast.makeText(context, "Headset changed", Toast.LENGTH_SHORT).show();
                
            case Intent.ACTION_SCREEN_OFF:
                Log.d(TAG, "onReceive: Screen off");
                Toast.makeText(context, "Screen off", Toast.LENGTH_SHORT).show();
            case ConnectivityManager.CONNECTIVITY_ACTION:
                Log.d(TAG, "onReceive: Connectivity changed");
                Toast.makeText(context, "Wifi changed", Toast.LENGTH_SHORT).show();



        }
    }
}
