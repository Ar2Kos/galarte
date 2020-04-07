package com.activities.galarte;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class MapsActivityFiltered extends AppCompatActivity
        implements
        GoogleMap.OnInfoWindowClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;

    private boolean mPermissionDenied = false;

    private SharedPreferences prefs;


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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkMode = prefs.getBoolean("pref_dark_mode", false);
        String defaultLocation = prefs.getString("default_location", "");
        String username = prefs.getString("username", "");

        if (defaultLocation.equals("")) {
            defaultLocation = "Bath";
        }

        if (username.equals("")) {
            username = "Guest";
        }

        if (darkMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button filterButton = new Button(this);
        filterButton.setText("Unfilter");
        addContentView(filterButton, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

        filterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

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
        //mMap.addMarker(new MarkerOptions().position(bath).title("Centre of camera").icon(BitmapDescriptorFactory.fromBitmap(icon)));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bath,15f));
        mMap.setOnInfoWindowClickListener(this);

        InputStream is = getResources().openRawResource(R.raw.place);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";


        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String style = prefs.getString("style", "");
        String style_select = prefs.getString("style_select", "");
        System.out.println(style_select);
        if (style_select.equals("Undecided")) {
            style_select = "";
        }
        System.out.println(style);
        if (style.equals("") && style_select.equals("")) {
            Toast.makeText(this, "No Styles yet!", Toast.LENGTH_SHORT).show();
        } else if (style.equals("")) {
            Toast.makeText(this, "Filtering for your selected style " + style_select, Toast.LENGTH_SHORT).show();
        } else if(style_select.equals("")) {
            Toast.makeText(this, "Filtering for your Questionnaire style " + style, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Filtering for your selected style, " + style_select + ", and your Questionnaire style, " + style, Toast.LENGTH_LONG).show();
        }
        try {
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                try {

                    if (!tokens[0].equalsIgnoreCase("name") && (tokens[4].equalsIgnoreCase(style) || tokens[4].equalsIgnoreCase(style_select))) {
                        LatLng coord = new LatLng(Float.parseFloat(tokens[9]), Float.parseFloat(tokens[10]));
                        mMap.addMarker(new MarkerOptions().position(coord).title(tokens[0]).snippet(tokens[4]).icon(BitmapDescriptorFactory.fromBitmap(icon)));
                    }
                } catch (Exception e) {
                    if (tokens.length != 10) {
                        for (String token:
                             tokens) {
                            System.out.println(token);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent galleryPageIntent = new Intent(this, GalleryPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("galleryName", marker.getTitle());
        galleryPageIntent.putExtras(bundle);
        startActivity(galleryPageIntent);
    }

    public void toSettings(MenuItem item) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        item.setChecked(true);
        startActivity(settingsIntent);
    }

    public void toTakeQuiz(MenuItem item) {
        Intent takeQuizIntent = new Intent(this, QuestionPage.class);
        item.setChecked(true);
        startActivity(takeQuizIntent);
    }

    public void toMap(MenuItem item) {
        Intent mapIntent = new Intent(this, MapsActivityFiltered.class);
        item.setChecked(true);
        startActivity(mapIntent);
    }

}
