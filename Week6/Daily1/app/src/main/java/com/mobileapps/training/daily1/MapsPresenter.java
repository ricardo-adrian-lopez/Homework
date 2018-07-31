package com.mobileapps.training.daily1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsPresenter implements MapsContract.ForwardInteractionToPresenter {

    private static final String TAG = "MapsPresenter";
    private MapsContract.PublishToView publishToView;
    private Context context;

    private FusedLocationProviderClient fusedLocationProviderClient;

    public MapsPresenter(Context context, MapsContract.PublishToView publishToView) {
        this.context = context;
        this.publishToView = publishToView;
    }

    @Override
    public void getLocationAsAddress() {
        Log.d(TAG, "getLocationAsAddress: Getting location");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        try {
            Task task = fusedLocationProviderClient.getLastLocation();
            task.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "onComplete: Found location");
                        Location location = (Location) task.getResult();
                        try {
                            List<Address> addressList = new Geocoder(context, Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            String myAddress = addressList.get(0).getAddressLine(0);
                            publishToView.showResults(myAddress);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Log.d(TAG, "onComplete: Location is null");
                        Toast.makeText(context, "Unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return;
        } catch (SecurityException e) {
            Log.d(TAG, "getLocationAsAddress: Security exception " + e.getMessage());
            publishToView.showToastMessage("Security exception");
        }
    }
}
