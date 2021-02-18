package com.example.settinglibrary;

import android.content.Context;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.DropDownPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SeekBarPreference;
import androidx.preference.SwitchPreference;

import java.util.Locale;
//creating the Preferences programmatically
public class ProgSettingsFragement extends PreferenceFragmentCompat {
    Locale myLocale;
    ///setPreferencesFromResource(R.xml.preferences, rootKey);
     Context context;
     PreferenceScreen screen;

    public ProgSettingsFragement() {

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        context = getPreferenceManager().getContext();
        screen = getPreferenceManager().createPreferenceScreen(context);

        addPref("feedback","Send feedback","Report technical issues or suggest new features");
        addSwitchPref("notifications","Enable message notifications","");
       addCheckBoxPref("checkbox","check box","on");
      // addDropDownPref();
       addEditTextPref("edit1","write");
      // addListPref();
      setPreferenceScreen(screen);

    }

    public void addPref(String key,String title ,String summery) {
        Preference newPreference = new Preference(context);
        setKeyTitle(key, title, newPreference);
        newPreference.setSummary(summery);
        screen.addPreference(newPreference);
    }

    public void addEditTextPref(String key,String title) {
        EditTextPreference newPreference = new EditTextPreference(context);
        setKeyTitle(key, title, newPreference);
        screen.addPreference(newPreference);
    }
    public void addSwitchPref(String key,String title ,String summery) {
        SwitchPreference newPreference = new SwitchPreference(context);
        setKeyTitle(key, title, newPreference);
        newPreference.setSummary(summery);
        screen.addPreference(newPreference);
    }

    public void addCheckBoxPref(String key,String title ,String summery) {
        CheckBoxPreference newPreference = new CheckBoxPreference(context);
        setKeyTitle(key, title, newPreference);
        newPreference.setSummary(summery);
        screen.addPreference(newPreference);
    }
    public void addDropDownPref(String key, String title , String[] entries,String[] entryValues) {
        DropDownPreference newPreference = new DropDownPreference(context);
        setKeyTitle(key, title, newPreference);
        setEntryAddPref(entries, entryValues, newPreference);
    }
    public void addMultiSelectPref(String key, String title ,String summery , String[] entries,String[] entryValues) {
        MultiSelectListPreference newPreference = new MultiSelectListPreference(context);
        setKeyTitle(key, title, newPreference);
        newPreference.setSummary(summery);
        newPreference.setEntries(entries);
        newPreference.setEntryValues(entryValues);
        screen.addPreference(newPreference);
    }
    public void addListPref(String key, String title , String[] entries,String[] entryValues) {
        ListPreference newPreference = new ListPreference (context);
        setKeyTitle(key, title, newPreference);
        setEntryAddPref(entries, entryValues, newPreference);
    }

    private void setEntryAddPref(String[] entries, String[] entryValues, ListPreference newPreference) {
        newPreference.setEntries(entries);
        newPreference.setEntryValues(entryValues);
        screen.addPreference(newPreference);
    }

    public void addSeekBarPref(String key, String title , int max,int defaultV) {
        SeekBarPreference newPreference = new SeekBarPreference(context);
        setKeyTitle(key, title, newPreference);
        newPreference.setMax(max);
        newPreference.setDefaultValue(defaultV);
        screen.addPreference(newPreference);
    }

    private void setKeyTitle(String key, String title, Preference newPreference) {
        newPreference.setKey(key);
        newPreference.setTitle(title);
    }



}


