package com.mobileapps.training.daily1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, SearchContract.PublishToView {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Log.d(TAG, "onCreate: Map initialized");

        SearchPresenter searchPresenter = new SearchPresenter(this, this);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        searchPresenter.getLocationDevice();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
    }

    @Override
    public void showLocation(double latitude, double longitude, float zoom) {
        moveCamera(new LatLng(latitude, longitude), zoom);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    @Override
    public void searchLocation(Address address) {

    }

    @Override
    public void showToastMessage(String message) {

    }

    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: Moving camera to: " + latLng.latitude + "," +latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }
}
