package com.activities.galarte;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends AppCompatActivity
        implements
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;

    private boolean mPermissionDenied = false;


    double[] longitude = {-2.358266, -2.358713, 2.33759, -2.358359,
            -2.350833, -2.359225, -2.365695, -2.363773, -2.3611, -2.360547};
    double[] latitude = {51.381164, 51.383048, 48.860971, 51.382807,
            51.385896, 51.38096, 51.386764, 51.381143, 51.382938, 51.383358};


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            // Permission was denied. Display an error message.
        }
    }

//TODO: work on having the map show galleries on it using info from the db



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        Permissions.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        enableMyLocation();

        //mMap.setMyLocationEnabled(true);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Drawable myDrawable = getResources().getDrawable(R.drawable.icon1);
        Bitmap icon = ((BitmapDrawable) myDrawable).getBitmap();
        // Add a marker in Sydney and move the camera
        LatLng bath = new LatLng(51.3801212, -2.3595492);
        mMap.addMarker(new MarkerOptions().position(bath).title("Centre of camera").icon(BitmapDescriptorFactory.fromBitmap(icon)));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bath,15f));

        //show icons of galleries
        for(int count = 0; count<longitude.length; count++){
            LatLng coord = new LatLng(latitude[count], longitude[count]);
            mMap.addMarker(new MarkerOptions().position(coord).title("Gallery Title").snippet("Details").icon(BitmapDescriptorFactory.fromBitmap(icon)));

        }
    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            Permissions.requestPermission(this, MY_LOCATION_REQUEST_CODE ,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

}
