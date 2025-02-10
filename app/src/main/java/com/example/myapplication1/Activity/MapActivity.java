package com.example.myapplication1.Activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.example.myapplication1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map); // Reference the correct layout

        // Initialize SupportMapFragment (instead of MapFragment)
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Apply window insets for proper UI padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sriLanka = new LatLng(7.8731, 80.7718);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sriLanka, 7));
        // Add markers for different job categories
        LatLng electricianLocation = new LatLng(6.927079, 79.861244);
        mMap.addMarker(new MarkerOptions().position(electricianLocation).title("Saman Edirimuni Electrician"));

        LatLng plumberLocation = new LatLng(6.933593, 79.956094);
        mMap.addMarker(new MarkerOptions().position(plumberLocation).title("Sadun Dilanka Plumber"));

        LatLng carpenterLocation = new LatLng(6.921999, 79.977101);
        mMap.addMarker(new MarkerOptions().position(carpenterLocation).title("Ranawera Carpenter"));

        LatLng foodOrderLocation = new LatLng(6.934743, 79.979944);
        mMap.addMarker(new MarkerOptions().position(foodOrderLocation).title("Namal Food Order"));

        // Move camera to the first location and zoom in
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(electricianLocation, 12));
    }
}
