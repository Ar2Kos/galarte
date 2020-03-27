package com.activities.galarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.activities.galarte.data.model.LoggedInUser;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

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
        setContentView(R.layout.activity_main_menu);

        //Menu menu = findViewById(R.id.bottom_navigation);


        //MenuInflater menuInflater = new MenuInflater(this);
        //menuInflater.inflate(0,  menu);

        TextView textView = findViewById(R.id.testDefaultLocation);
        textView.setText("Default Location: " + defaultLocation + "\nUsername: " + username);

        ImageButton settingsButton = findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

    }

    public void openSettings() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    public void toSettings(MenuItem item) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    public void toTakeQuiz(MenuItem item) {
        Intent takeQuizIntent = new Intent(this, QuestionPage.class);
        startActivity(takeQuizIntent);
    }

    public void toMap(MenuItem item) {
        Intent mapIntent = new Intent(this, MapsActivity.class);
        startActivity(mapIntent);
    }


}
