package com.example.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.settinglibrary.SettingsActivity;

public class MainActivity extends AppCompatActivity {
    boolean xml = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            xml = true;
            getActivity(xml);

            return true;
        } else if (id == R.id.action_settings2) {
            xml = false;
            getActivity(xml);
            //  ProgSettingsFragement progSettingsFragement =new ProgSettingsFragement();

            //  progSettingsFragement.addPref("feedback","Send feedback","Report technical issues or suggest new features");
            // progSettingsFragement.addSwitchPref("notifications","Enable message notifications","");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getActivity(boolean xml) {
        Intent k = new Intent(this, SettingsActivity.class);
        k.putExtra("xml_value", xml);
        startActivity(k);
    }

}