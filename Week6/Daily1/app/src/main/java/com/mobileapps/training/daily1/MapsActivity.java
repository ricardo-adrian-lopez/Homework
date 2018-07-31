package com.mobileapps.training.daily1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, SearchContract.PublishToView {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    //Widget
    EditText searchText;
    private SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Log.d(TAG, "onCreate: Map initialized");

        searchText = findViewById(R.id.input_search);
        searchPresenter = new SearchPresenter(this, this);

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
        init();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    @Override
    public void searchLocation(Address address, float zoom) {
        // Set marker
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        moveCamera(latLng, zoom);
        hideKeyboard();
        mMap.addMarker(setMarker(latLng, "My search location"));

    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: Moving camera to: " + latLng.latitude + "," +latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }

    private MarkerOptions setMarker(LatLng latLng, String title){
        return new MarkerOptions().position(latLng).title(title);
    }

    private void hideKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void init(){
        Log.d(TAG, "init: Initializing");
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction: " + actionId);
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_GO
                        || event.getAction()== KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    Log.d(TAG, "onEditorAction: Searching geolocation");
                    searchPresenter.getAddressFromText(searchText.getText().toString());
                    return true;
                }
                return false;
            }
        });

        hideKeyboard();

    }
}
