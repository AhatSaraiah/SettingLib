package com.example.settinglibrary;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

//defined  Preferences within XML
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String KEY_PREF_LANGUAGE = "languages_list";
    //public static final String KEY_PREF_THEME= "theme";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        EditTextPreference signaturePreference = findPreference("signature");
        EditTextPreference editTextPreference = findPreference("edit_text_preference");
        SwitchPreferenceCompat attachment = findPreference("attachment");
        SwitchPreferenceCompat sync = findPreference("sync");
        ListPreference languages = findPreference("languages_list");
        ListPreference themes = findPreference("theme");
        signaturePreference.setText("default");

        Preference.OnPreferenceChangeListener themeListener = (preference, newValue) -> {
            switch (newValue.toString()) {
                case "Dark":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case "Light":
                case "Default":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;

            }
            return true;
        };
        themes.setOnPreferenceChangeListener(themeListener);


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case KEY_PREF_LANGUAGE:
                LocaleHelper.setLocale(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()).getString(key, ""));
                getActivity().recreate();
                break;
            //case KEY_PREF_THEME:

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

}



