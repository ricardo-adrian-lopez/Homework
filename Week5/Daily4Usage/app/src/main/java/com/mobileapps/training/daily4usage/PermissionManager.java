package com.mobileapps.training.daily4usage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionManager {
    private static final String TAG = "PermissionManager";

    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;
    public static final String myPermission = "com.mobileapps.training.permission.READ_PROVIDER";
    Context context;
    Callback callback;

    public PermissionManager(Context context) {
        this.context = context;
        this.callback = (Callback) context;
    }

    public void checkPermission(){

        Log.d(TAG, "checkPermission: ");


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context,
                myPermission)
                != PackageManager.PERMISSION_GRANTED) {

            Log.d(TAG, "checkPermission: Permission not granted");
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    myPermission)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.d(TAG, "checkPermission: Show explanation to grant permission");
            } else {
                Log.d(TAG, "checkPermission: Request Permission");
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{myPermission},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            Log.d(TAG, "checkPermission: Permission already granted");
            callback.onPermissionResults(MY_PERMISSIONS_REQUEST_READ_CONTACTS,true);
        }
    }

    public void checkResults(int requestCode,
                             String permissions[], int[] grantResults){
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.d(TAG, "checkResults: Permission was granted");
                    callback.onPermissionResults(requestCode,true);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d(TAG, "checkResults: Permission was denied");
                    callback.onPermissionResults(requestCode,false);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public interface Callback{
        void onPermissionResults(int requestCode, boolean isGranted);
    }
}
