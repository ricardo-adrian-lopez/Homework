package com.mobileapps.training.daily1;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchPresenter implements SearchContract.ForwardInteractionToPresenter {

    private static final String TAG = "SearchPresenter";

    private Context context;
    private SearchContract.PublishToView publishToView;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private float DEFAULT_ZOOM = 15f;

    public SearchPresenter(Context context, SearchContract.PublishToView publishToView) {
        this.context = context;
        this.publishToView = publishToView;
    }


    @Override
    public void getLocationDevice() {
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
                        publishToView.showLocation(location.getLatitude(),location.getLongitude(),DEFAULT_ZOOM);
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

    @Override
    public void getAddressFromText(String text) {
        Log.d(TAG, "getAddressFromText: Geolocating");
        Geocoder geocoder = new Geocoder(context);
        List<Address> addressList = new ArrayList<>();

        try {
            addressList = geocoder.getFromLocationName(text,1);
            if(addressList.size() > 0){
                Address address = addressList.get(0);
                Log.d(TAG, "getAddressFromText: Found Location Requested: " + address.toString());
                publishToView.searchLocation(address, DEFAULT_ZOOM);
            }
        }catch (IOException e){
            Log.d(TAG, "getAddressFromText: IOException: " + e.getMessage());
            publishToView.showToastMessage("Could not find location");
        }
    }
}
