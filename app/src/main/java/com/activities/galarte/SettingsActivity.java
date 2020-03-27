package com.activities.galarte;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.activities.galarte.ui.login.LoginActivity;

public class SettingsActivity extends AppCompatActivity {


    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private PreferenceManager manager;
    boolean darkMode;
    String defaultLocation;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Retrieve preferences
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        darkMode = prefs.getBoolean("pref_dark_mode", false);
        defaultLocation = prefs.getString("default_location", "");
        username = prefs.getString("username", "");

        if (darkMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        if (username.equals("")) {
            PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        } else {
            PreferenceManager.setDefaultValues(this, R.xml.preferences_logged_in, false);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // Add preferences to layout
        if (findViewById(R.id.settings) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getFragmentManager().beginTransaction().add(R.id.settingsGrid, new SettingsFragment()).commit();
        }

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs_, String key) {
                // If a preference is changed, restart the activity to update UI
                restart();
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(listener);

    }

    public void restart() {
        // Restart activity
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
        finish();
    }

    public void backToMainMenu(View view) {
        Intent mainMenuIntent = new Intent(this, MainMenu.class);
        startActivity(mainMenuIntent);
    }

    public void goToLogin(View view) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    public void logout(View view) {
        // Edit preferences to remove the username
        editor.putString("username", "");
        editor.commit();
        restart();
    }

    public void toSettings(MenuItem item) {
        item.setChecked(true);
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    public void toTakeQuiz(MenuItem item) {
        item.setChecked(true);
        Intent takeQuizIntent = new Intent(this, QuestionPage2.class);
        startActivity(takeQuizIntent);
    }

    public void toMap(MenuItem item) {
        item.setChecked(true);
        Intent mapIntent = new Intent(this, MapsActivity.class);
        startActivity(mapIntent);
    }
}