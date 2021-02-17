package com.example.settinglibrary;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment(), "MY_FRAGMENT")
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this );
                String name = sharedPreferences.getString("signature", "");
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        // Instantiate the new Fragment
        final Bundle args = pref.getExtras();
        final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(
                getClassLoader(),
                pref.getFragment());
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);
        // Replace the existing Fragment with the new Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings, fragment)
                .addToBackStack(null)
                .commit();
        setTitle(pref.getTitle());
        return true;
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        Locale myLocale;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            EditTextPreference signaturePreference = findPreference("signature");
            // do something with this preference
            //its worked
            signaturePreference.setText("default");
         //   signaturePreference.setTitle("bhkh");
            ListPreference langPreference = getPreferenceScreen().findPreference(
                    "list_preference");
            langPreference.setOnPreferenceChangeListener(languageChangeListener);
            ListPreference theme = getPreferenceScreen().findPreference(
                    "theme");
            langPreference.setOnPreferenceChangeListener(themeListener);
        }

        Preference.OnPreferenceChangeListener languageChangeListener = (preference, newValue) -> {
            switch (newValue.toString()) {
                case "English":
                    setLocale("en");
                    break;
                case "Hebrew":
                    setLocale("he");
                    break;
                case "Arabic":
                    setLocale("ar");
                    break;
                case "Turkish":
                    setLocale("tr");
                    break;
            }
            return true;
        };
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

        // changing current locale/
        public void setLocale(String lang) {
            myLocale = new Locale(lang);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Fragment currentFragment = getFragmentManager().findFragmentByTag("MY_FRAGMENT");
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.detach(currentFragment);
            fragmentTransaction.attach(currentFragment);
            fragmentTransaction.commit();

        }


//        public void setLocale(String lang) {
//            Locale myLocale = new Locale(lang);
//            Resources res = getResources();
//            DisplayMetrics dm = res.getDisplayMetrics();
//            Configuration conf = res.getConfiguration();
//            conf.locale = myLocale;
//            res.updateConfiguration(conf, dm);
//            Intent refresh = new Intent(this, SettingsActivity.class);
//            finish();
//            startActivity(refresh);
//        }
    }



}