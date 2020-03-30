package com.activities.galarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class GalleryPage extends AppCompatActivity implements OnMapReadyCallback {

    LinearLayout viewEventNames;
    Button eventButton1,eventButton2;


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
        setContentView(R.layout.gallery_page);

        String galleryName = getIntent().getExtras().getString("galleryName");

        InputStream is = getResources().openRawResource(R.raw.place);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if (tokens[0].equals(galleryName)) {
                    TextView galleryNameTextView = findViewById(R.id.galleryName);
                    galleryNameTextView.setText(galleryName);

                    TextView cityTextView = findViewById(R.id.city);
                    cityTextView.setText(tokens[3]);

                    TextView priceTextView = findViewById(R.id.price);
                    priceTextView.setText(tokens[1]);

                    TextView detailsTextView = findViewById(R.id.detailsText);
                    detailsTextView.setText(tokens[4]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "The Gallery was not found", Toast.LENGTH_SHORT).show();
            finish();
        }


        viewEventNames = (LinearLayout) findViewById(R.id.viewEventNames);
        buildCategoryScroll();
        addListenerOnButtons();

        //TODO: map link -> make button send you to the map page
        //TODO: solve event -> drop down?
        //TODO: add picture title city price  -> link to database
        //TODO: add scroll bar
        //TODO: back button



    }


    protected void addListenerOnButtons() {
        eventButton1 = (Button) findViewById(R.id.btn1);
        eventButton2 = (Button) findViewById(R.id.btn2);
        Button mapButton = (Button)findViewById(R.id.button_to_map_activity);

        eventButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(GalleryPage.this, MapActivity.class));   ///TODO: make button send you to the map page
//                Intent Event1 = new Intent(view.getContext(),Event1.class);
//                startActivityForResult(Event1,0);
            }
        });

//        eventButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent questionnaire = new Intent(view.getContext(), QuestionPage.class);
//                startActivityForResult(questionnaire,0);
//            }
//        });
    }


    private void buildCategoryScroll() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 30, 10);

        for (int i = 1; i <= 15; i++) {
            final Button btnEvent = new Button(GalleryPage.this);
            btnEvent.setText(String.valueOf(i));
            btnEvent.setTextSize(16f);
//            btnEvent.setAllCaps(false);
//            btnEvent.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
//            btnEvent.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            btnEvent.setLayoutParams(layoutParams);
            btnEvent.setTag(i);
            viewEventNames.addView(btnEvent);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
