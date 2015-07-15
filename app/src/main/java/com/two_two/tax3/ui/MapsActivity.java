package com.two_two.tax3.ui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.two_two.tax3.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available. Or Map is not yet ready.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void chooseCity(){
        LatLng position = new LatLng(getIntent().getDoubleExtra("latitude",0),getIntent().getDoubleExtra("longitude",0));
        String CityName = getIntent().getStringExtra("cityName");
        if (CityName == null) throw new RuntimeException("not correct intent for MapActivity"); //there should be fix for not correct intent

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        mMap.addMarker(new MarkerOptions()
                        .position(position)
                        .title(CityName)
                        .snippet(position.toString()) //description - coordinated of city
        );

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });
        animateCamera(position);

    }

    private void animateCamera(LatLng targetPosition){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(targetPosition)
                .zoom(6)
                .bearing(0)
                .tilt(30)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        chooseCity();
    }
}
