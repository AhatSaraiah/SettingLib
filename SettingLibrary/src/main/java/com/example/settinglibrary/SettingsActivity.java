package com.example.settinglibrary;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        boolean j=false;
        if(b!=null)
             j =(boolean) b.get("xml_value");
        setContentView(R.layout.settings_activity);
            if (savedInstanceState == null) {
                if(j==true) {

                    getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings, new SettingsFragment(), "MY_FRAGMENT")
                        .commit();
            }
        else{
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings, new ProgSettingsFragement(), "FRAGMENT")
                        .commit();

            }

        }


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        SharedPreferences sharedPreferences =
//                PreferenceManager.getDefaultSharedPreferences(this );
//                String name = sharedPreferences.getString("signature", "");
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

////        public void setLocale(String lang) {
////            Locale myLocale = new Locale(lang);
////            Resources res = getResources();
////            DisplayMetrics dm = res.getDisplayMetrics();
////            Configuration conf = res.getConfiguration();
////            conf.locale = myLocale;
////            res.updateConfiguration(conf, dm);
////            Intent refresh = new Intent(this, SettingsActivity.class);
////            finish();
////            startActivity(refresh);
////        }
//    }
//


}