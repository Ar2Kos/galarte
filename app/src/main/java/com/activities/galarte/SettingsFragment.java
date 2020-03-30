package com.activities.galarte;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import androidx.preference.SeekBarPreference;


public class SettingsFragment extends PreferenceFragment {



    private static final String PREF_DARK_MODE = "pref_dark_mode";
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private SharedPreferences prefs;
    String result = QuestionPage2.getQuestionareResult();
    String finalStyle;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String username = prefs.getString("username", "");
        String defaultLocation = prefs.getString("default_location", "");
        String region = prefs.getString("Region", "");
        int price = prefs.getInt("default_price", 10);
        String style_withq = prefs.getString("style", result);
        String style_withoutq = prefs.getString("style_select", "");

        super.onCreate(savedInstanceState);

        if (username.equals("")){
            addPreferencesFromResource(R.xml.preferences);

            ListPreference stylePreference_without = (ListPreference) findPreference("style_select");
            stylePreference_without.setSummary(style_withoutq);
            finalStyle = String.valueOf(stylePreference_without);

        } else if(result != ""){
            addPreferencesFromResource(R.xml.preferences_logged_in_with_question);

            Preference accountName = findPreference("Logout");
            accountName.setSummary(username);

            Preference stylePreference_with =  findPreference("style");
            stylePreference_with.setSummary(style_withq);

//            Preference pricePreference = findPreference("default_price");
//            pricePreference.setSummary(price);


            finalStyle = String.valueOf(stylePreference_with);

        } else {
            addPreferencesFromResource(R.xml.preferences_logged_in_without_question);

            Preference accountName = findPreference("Logout");
            accountName.setSummary(username);

            ListPreference stylePreference_without = (ListPreference) findPreference("style_select");
            stylePreference_without.setSummary(style_withoutq);

            finalStyle = String.valueOf(stylePreference_without);

//            ListPreference stylePreference = (ListPreference) findPreference("style_select");
//            stylePreference.setSummary(style);

        }

        EditTextPreference defaultLocationPreference = (EditTextPreference) findPreference("default_location");
        defaultLocationPreference.setSummary(defaultLocation);

        ListPreference regionPreference = (ListPreference) findPreference("Region");
        regionPreference.setSummary(region);


//        ListPreference stylePreference_without = (ListPreference) findPreference("style_select");
//        stylePreference_without.setSummary(style_withoutq);
////
//        Preference stylePreference_with =  findPreference("style");
//        stylePreference_with.setSummary(style_withq);


    }

    public String getFinalStyle(){
        return finalStyle;
    }

}


